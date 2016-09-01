package com.miteno.controller.queue;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.miteno.entity.OrderData;
import com.miteno.service.MobService;
import com.miteno.util.ConstUtils;
import com.miteno.util.HttpResult;
import com.miteno.util.HttpUtil;
import com.miteno.util.MD5;
import com.miteno.util.SpringContextHelper;

public class QueueMsgController {
	private static Logger logger = Logger.getLogger(QueueMsgController.class);
	// 加载配置文件
	/*
	 * private final static Configuration sysConf = new Configuration(
	 * "upmp.properties");
	 */

	// 设置分发线程数
	private final int THREAD_NUM_SUPPLY = 2;
	private final int THREAD_NUM_CALLBACK = 2;
	// 设置队列大小
	private static int queueSizeSupply = 10000;
	private static int queueSizeCallback = 1000;

	MobService mobService = (MobService) SpringContextHelper
			.getBean("mobService");
	// 定义队列
	private static BlockingQueue<OrderCommand> queue;
	private static BlockingQueue<OrderData> queueCallBack;
	// 初始化队列
	static {
		queue = new ArrayBlockingQueue<OrderCommand>(queueSizeSupply, true);
		logger.info("初始化接受微信回调队列,大小" + queueSizeSupply);

		queueCallBack = new ArrayBlockingQueue<OrderData>(queueSizeCallback,
				true);
		logger.info("初始化下游回调队列,大小" + queueSizeCallback);

	}

	// 构造函数起线程

	public QueueMsgController() {
		ExecutorService newFixedThreadPool = Executors
				.newFixedThreadPool(THREAD_NUM_SUPPLY + THREAD_NUM_CALLBACK);
		for (int i = 1; i <= THREAD_NUM_SUPPLY; i++) {
			newFixedThreadPool.execute(new HandleOrderQueue());
			logger.debug("初始化-接受微信回调线程-" + i);

		}
		for (int k = 1; k <= THREAD_NUM_CALLBACK; k++) {
			newFixedThreadPool.execute(new HandleQueueCallback());
			logger.debug("初始化-主动查询线程-" + k);
		}

		newFixedThreadPool.shutdown();
	}

	// 推入队列
	public static void pushQueue(OrderCommand command) {
		try {
			queue.put(command);
			logger.debug("订单已经推入充值处理队列, 当前队列中订单数量 = " + queue.size());
			/*
			 * synchronized (queue) { queue.notifyAll(); }
			 */
		} catch (Exception e) {
			logger.error("将订单推入上游队列发生错误：" + e.getMessage());
			e.printStackTrace();
			return;
		}
	}

	public static void pushQueue2(OrderData callBackReq) {
		try {
			queueCallBack.put(callBackReq);
			logger.debug("回调请求已经推入回调队列, 当前队列中回调数量 = " + queueCallBack.size());

			/* * synchronized (queueCallBack) { queueCallBack.notifyAll(); } */

		} catch (Exception e) {
			logger.error("将回调请求推入回调队列发生错误：" + e.getMessage());
			e.printStackTrace();
			return;
		}
	}

	class HandleOrderQueue implements Runnable {

		@Override
		public void run() {
			OrderCommand command = null;
			while (true) {
				try {
					// synchronized (queue) {
					command = queue.poll();
					if (command == null) {
						Thread.sleep(4000);
						//logger.debug("充值处理队列无数据，等待4秒……");
					} else {

						logger.info("充值处理执行分发,队列数量为" + queue.size()+1);

						command.execute();
						logger.info("充值处理队列执行分发，处理完成");
					}

				} catch (Exception e) {
					logger.error("从上游处理队列读取数据时发生错误：" + e.getMessage());
					e.printStackTrace();
				}
			}
		}

	}

	class HandleQueueCallback implements Runnable {
		@Override
		public void run() {
			OrderData callBackRequest = null;
			while (true) {
				try {

					callBackRequest = queueCallBack.poll();
					if (callBackRequest == null) {
						//logger.debug("回调队列无数据，等待4秒....");
						Thread.sleep(4000);
					} else {
						// 根据第一次放入时间和当前时间差，如果大于4分钟，则查询当前订单状态，如果是已付款，状态，则主动查询上游接口

						// 接口返回状态
						// 订单状态 1,处理中；2，已提交充值；3，已发货；4，成功；5，失败；

						Date firstTime = callBackRequest.getFirstTime();
						if (firstTime != null) {
							long diff = (new Date()).getTime()
									- firstTime.getTime();
							long mins = diff / (1000 * 60); // 上一次发送和当前时间的时间差(分钟)
							if (mins < ConstUtils.query_interval) {
								// 上一次发送时间距现在不足X分钟，则重新推进队列
							/*	logger.debug("距上一次查询时间不足"
										+ ConstUtils.query_interval
										+ "分钟，重新推入队列");*/
								QueueMsgController.pushQueue2(callBackRequest);
								continue;
							}
						}
						Map<String, String> map = new HashMap<String, String>();
						map.put("agentid", ConstUtils.agentid);// 代理商编号，梅泰诺提供

						map.put("orderId",
								callBackRequest.getSend_order_number());// 合作商户订单号(发给上游的订单号，非内部订单号)

						String signData = ConstUtils.agentid
								+ callBackRequest.getSend_order_number()
								+ ConstUtils.key;

						map.put("sign", MD5.MD5Encode(signData).toLowerCase());// MD5后字符串
						HttpResult httpResult = null;
						try {
							httpResult = HttpUtil.getInstance().post(
									ConstUtils.queryOrder_url, "UTF-8", map);
						} catch (Exception e) {
							// 因为是查询，如果报异常。我就在此放入队列，用不用更新一下时间呢？
							e.printStackTrace();
							logger.info(callBackRequest.getInner_order_number()+ "查询报异常了,状态改为6需要人工处理" + e.getMessage());
							HashMap<String, String> _map1 = new HashMap<String, String>();
							_map1.put("orderStatus",ConstUtils.STATE_SENDED);
							_map1.put("describeMes","查询报异常了,状态改为6需要人工处理。状态改为："+ ConstUtils.STATE_DESC_SENDED);
							mobService.updateOrder(_map1);
							return;
						}
						String substring = httpResult.getContent();
						logger.info(callBackRequest.getInner_order_number()
								+ "查询上游返回结果" + substring);
						String[] split = substring.split("\\|");
						/*System.out.println(split[0]);
						System.out.println(split[1]);
						System.out.println(split[2]);
						System.out.println(split[3]);
						System.out.println("xxxxxxxxxxxxx=" + split[4]);
						System.out.println(split[5]);
						System.out.println(split[6]);*/
						if ("000000".equals(split[0])) {// 000000表示查询成功
							HashMap<String, String> _map1 = new HashMap<String, String>();
							_map1.put("innerOrderNumber",
									callBackRequest.getInner_order_number());

							if ("4".equals(split[5])) {
								// if this is success,you can change the order
								// status
								_map1.put("orderStatus",
										ConstUtils.STATE_SUCCESS);
								_map1.put("describeMes", "主动查询订单状态为成功。状态改为："
										+ ConstUtils.STATE_DESC_SUCCESS);

							} else if ("5".equals(split[5])) {
								// 失败状态，除了更改订单状态外，要微信退款
								_map1.put("orderStatus", ConstUtils.STATE_FAIL);
								_map1.put("describeMes", "主动查询订单状态为失败,需要人工退款。状态改为："
										+ ConstUtils.STATE_DESC_FAIL);
								// liuyifeng 退款，人工退款
							} else {// 其他状态
								if (callBackRequest.getCount() < 3) {// 如果查询次数小于3次，那么再次放入队列查询
									callBackRequest.setCount(callBackRequest
											.getCount() + 1);
									QueueMsgController
											.pushQueue2(callBackRequest);
								} else {// 如果超过了三次，并且状态不是成功或者失败，那么就设置为人工处理
									_map1.put("orderStatus",
											ConstUtils.STATE_SENDED);
									_map1.put(
											"describeMes",
											"查询了三次，每次间隔4分钟，状态都不是成功或者失败。状态改为："
													+ ConstUtils.STATE_DESC_SENDED);
								}
							}
							mobService.updateOrder(_map1);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	
	public static void main(String[] args) {
		String str="000000|100856|201505181115038772|18625170707|10|5|9969c19b98ca7f391d5f490b41f6a6f2";
		String[] split = str.split("\\|");
		System.out.println(split[0]);
		System.out.println(split[1]);
		System.out.println(split[2]);
		System.out.println(split[3]);
		System.out.println("xxxxxxxxxxxxx=" + split[4]);
		System.out.println(split[5]);
		System.out.println(split[6]);
		
	}  
}

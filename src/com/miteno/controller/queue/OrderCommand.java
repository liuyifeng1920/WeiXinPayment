package com.miteno.controller.queue;

import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.log4j.Logger;

import sun.java2d.pipe.SolidTextRenderer;

import com.miteno.entity.OrderData;
import com.miteno.entity.weixinPay.WxPayResult;
import com.miteno.service.MobService;
import com.miteno.util.AmountUtils;
import com.miteno.util.ConstUtils;
import com.miteno.util.HttpResult;
import com.miteno.util.HttpUtil;
import com.miteno.util.MD5;
import com.miteno.util.SpringContextHelper;

public class OrderCommand implements Command {
	MobService mobService = (MobService) SpringContextHelper
			.getBean("mobService");
	WxPayResult result; // 订单
	

	private static Logger logger = Logger.getLogger(OrderCommand.class);

	public WxPayResult getResult() {
		return result;
	}

	public void setResult(WxPayResult result) {
		this.result = result;
	}

	@Override
	public void execute() {
		// 先根据内部订单号查询当前订单状态是否是已经付款，如果是继续走逻辑，不是的不处理
		OrderData orderData = mobService.searchpayDataList(result
				.getOutTradeNo());
		if (orderData != null
				&& ConstUtils.STATE_PAY_FINISH.equals(orderData
						.getOrder_status())) {
			String innerOrderNo = orderData.getInner_order_number();
			logger.info("【内部订单号】" + innerOrderNo + "状态是已付款，可以继续向上游发送");
			logger.info("innerOrderNo = " + innerOrderNo
					+ ", 准备向话费充值平台上游发送请求, 开始封装参数");
			HttpResult httpResult = null;

			Map<String, String> map = new HashMap<String, String>();
			map.put("agentid", ConstUtils.agentid);// 代理商编号，梅泰诺提供
			logger.info("innerOrderNo = " + innerOrderNo
					+ ", agentid="+ConstUtils.agentid);
			map.put("orderId", orderData.getSend_order_number());// 合作商户订单号(发给上游的订单号，非内部订单号)
			logger.info("innerOrderNo = " + innerOrderNo+ ", （发给上游单号）orderId="+orderData.getSend_order_number());
			String _amount = "0";
			try {
				_amount = AmountUtils.changeF2Y(result.getTotalFee());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			map.put("amount", _amount);// 订单金额 分转元
			logger.info("innerOrderNo = " + innerOrderNo+ ", （金额：元）amount="+_amount);
			map.put("userToken", orderData.getPhone_number());// 充值手机号码
			logger.info("innerOrderNo = " + innerOrderNo+ ", userToken="+orderData.getPhone_number());
			map.put("callbackurl", ConstUtils.callbackurl);// 回调地址
			logger.info("innerOrderNo = " + innerOrderNo+ ", callbackurl="+ConstUtils.callbackurl);
			map.put("extendinfo", "");// 扩展信息，通知时将原样返回。中文注意转码

			String signData = ConstUtils.agentid
					+ orderData.getSend_order_number() + _amount
					+ orderData.getPhone_number() + ConstUtils.key;

			map.put("sign", MD5.MD5Encode(signData).toLowerCase());// MD5后字符串

			logger.info("innerOrderNo = " + innerOrderNo + ", 请求IP及地址 = "
					+ ConstUtils.submit_url);
			logger.info("innerOrderNo = " + innerOrderNo + ", 请求参数 = "
					+ map.toString());

			try {
				httpResult = HttpUtil.getInstance().post(ConstUtils.submit_url,
						"UTF-8", map);
			} catch (SocketTimeoutException e) {
				logger.error("innerOrderNo = " + innerOrderNo
						+ ", http post报出异常: " + e.getMessage());
				logger.error("innerOrderNo = " + innerOrderNo
						+ ", http post报出异常:堆栈信息 " + e.toString());
				e.printStackTrace();
				// 交由人工处理，改为已发货
				HashMap<String, String> _map = new HashMap<String, String>();
				_map.put("innerOrderNumber", orderData.getInner_order_number());
				_map.put("orderStatus", ConstUtils.STATE_SENDED);
				_map.put("describeMes",
						"SocketTimeoutException异常，需要人工处理。状态改为："+ConstUtils.STATE_DESC_SENDED);
				mobService.updateOrder(_map);
			} catch (Exception e) {
				// ConnectException ConnectTimeoutException 连接不上
				// UnknownHostException域名解析
				// SocketTimeoutException 连接上无响应
				logger.error("innerOrderNo = " + innerOrderNo
						+ ", http post报出异常: " + e.getMessage());
				logger.error("innerOrderNo = " + innerOrderNo
						+ ", http post报出异常:堆栈信息 " + e.toString());
				e.printStackTrace();
				
				// 交由人工处理，改为已发货
				HashMap<String, String> _map = new HashMap<String, String>();
				_map.put("innerOrderNumber", orderData.getInner_order_number());
				_map.put("orderStatus", ConstUtils.STATE_SENDED);
				_map.put("describeMes",
						"SocketTimeoutException异常，需要人工处理。状态改为："+ConstUtils.STATE_DESC_SENDED);
				mobService.updateOrder(_map);
			}
			logger.info("innerOrderNo = " + innerOrderNo + ", httpResult = "
					+ httpResult.getContent());
			// 000000表示话费充值平台，接受到请求，只是通信标示，不代表订单标示
			if ("000000".equals(httpResult.getContent().substring(0, 6))) {
				// 封装参数向话费充值平台提交请求，订单状态改为处理中
				HashMap<String, String> _map1 = new HashMap<String, String>();
				_map1.put("innerOrderNumber", orderData.getInner_order_number());
				_map1.put("orderStatus", ConstUtils.STATE_PAYINGF);
				_map1.put("describeMes",
						"已经将充值请求发送给充值平台，上游返回成功通信标示 000000 状态改为："+ConstUtils.STATE_DESC_PAYING);
				mobService.updateOrder(_map1);
				// 并把订单请求放入查询队列中，以备查询使用
				orderData.setFirstTime(new Date());
				QueueMsgController.pushQueue2(orderData);
			}else{
				// 交由人工处理，改为已发货,ip校验不通过等问题，会走这里
				HashMap<String, String> _map = new HashMap<String, String>();
				_map.put("innerOrderNumber", orderData.getInner_order_number());
				_map.put("orderStatus", ConstUtils.STATE_SENDED);
				_map.put("describeMes",
						httpResult.getContent()+"状态改为："+ConstUtils.STATE_DESC_SENDED);
				mobService.updateOrder(_map);
			}

		} else {
			logger.info("【内部订单号】" + orderData.getInner_order_number()+ "状态非已付款，不继续处理");
		}
	}

	public static void main(String[] args) throws Exception {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("agentid", ConstUtils.agentid);// 代理商编号，梅泰诺提供

		map.put("orderId", "222222");// 合作商户订单号(发给上游的订单号，非内部订单号)
		
		String signData = ConstUtils.agentid + "222222" + ConstUtils.key;

		map.put("sign", MD5.MD5Encode(signData).toLowerCase());// MD5后字符串
		HttpResult httpResult = null;
		try {
			httpResult = HttpUtil.getInstance().post(ConstUtils.queryOrder_url,
					"UTF-8", map);
		} catch (Exception e) {
			//因为是查询，如果报异常。我就在此放入队列，用不用更新一下时间呢？
			e.printStackTrace();
		}
		String substring = httpResult.getContent();
		System.out.println(substring);
		String str ="300007|100856|222222||0||bb5daa5764e1c7f9fb5dd8c93aeef498";
		String[] split = str.split("\\|");
		System.out.println(split[0]);
		System.out.println(split[1]);
		System.out.println(split[2]);
		System.out.println(split[3]);
		System.out.println("xxxxxxxxxxxxx="+split[4]);
		System.out.println(split[5]);
		System.out.println(split[6]);
		/*

		String innerOrderNo = "11111111111111111111";
		logger.info("【内部订单号】" + innerOrderNo + "状态是已付款，可以继续向上游发送");
		logger.info("innerOrderNo = " + innerOrderNo
				+ ", 准备向话费充值平台上游发送请求, 开始封装参数");

		Map<String, String> map = new HashMap<String, String>();
		map.put("agentid", ConstUtils.agentid);// 代理商编号，梅泰诺提供

		map.put("orderId", "22222222222222222");// 合作商户订单号(发给上游的订单号，非内部订单号)
		String _amount = AmountUtils.changeF2Y("100");
		map.put("amount", _amount);// 订单金额 分转元

		map.put("userToken", "18514235007");// 充值手机号码

		map.put("callbackurl", ConstUtils.callbackurl);// 回调地址

		map.put("extendinfo", "");// 扩展信息，通知时将原样返回。中文注意转码

		String signData = ConstUtils.agentid + "22222222222222222" + _amount
				+ "18514235007" + ConstUtils.key;

		map.put("sign", MD5.MD5Encode(signData).toLowerCase());// MD5后字符串
		HttpResult httpResult = null;

		logger.info("innerOrderNo = " + innerOrderNo + ", 请求IP及地址 = "
				+ ConstUtils.submit_url);
		logger.info("innerOrderNo = " + innerOrderNo + ", 请求参数 = "
				+ map.toString());

		try {
			httpResult = HttpUtil.getInstance().post(ConstUtils.submit_url,
					"UTF-8", map);
		} catch (ConnectTimeoutException e) {
			// ConnectException ConnectTimeoutException 连接不上
			// UnknownHostException域名解析
			// SocketTimeoutException 连接上无响应
			logger.error("innerOrderNo = " + innerOrderNo + ", http post报出异常: "
					+ e.getMessage());
			logger.error("innerOrderNo = " + innerOrderNo
					+ ", http post报出异常:堆栈信息 " + e.toString());
			e.printStackTrace();
			// liuyifeng 连接不上，做退款处理

		} catch (SocketTimeoutException e) {
			// liuyifeng
			// 交由人工处理，改为已发货
		} catch (Exception e) {
			// TODO: handle exception
		}
		logger.info("innerOrderNo = " + innerOrderNo + ", httpResult = "
				+ httpResult.getContent());
		String substring = httpResult.getContent().substring(0, 6);
		System.out.println("结果：" + substring);
	*/}
}

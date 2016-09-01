package com.miteno.controller.weixin;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xml.sax.InputSource;

import com.miteno.controller.queue.OrderCommand;
import com.miteno.controller.queue.QueueMsgController;
import com.miteno.entity.OrderData;
import com.miteno.entity.weixinPay.WxPayResult;
import com.miteno.service.MobService;
import com.miteno.util.ConstUtils;

/**
 * 微信支付成功后回调接口 Title: NotifyController.java Description: Company: miteno
 * 
 * @author yifeng
 * @date 2015-5-5
 * @version 1.0
 */

@Controller
@RequestMapping("/background/notify/")
public class NotifyController {
	@Autowired
	private MobService mobService;
	private Logger logger = Logger.getLogger(NotifyController.class);

	/**
	 * 接受微信支付成功回调信息
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("recv")
	public void recv(Model model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		logger.info("微信支付回调数据开始");
		// 示例报文
		// String xml = "<xml><appid><![CDATA[wxb4dc385f953b356e]]></appid><bank_type><![CDATA[CCB_CREDIT]]></bank_type><cash_fee><![CDATA[1]]></cash_fee><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[Y]]></is_subscribe><mch_id><![CDATA[1228442802]]></mch_id><nonce_str><![CDATA[1002477130]]></nonce_str><openid><![CDATA[o-HREuJzRr3moMvv990VdfnQ8x4k]]></openid><out_trade_no><![CDATA[20150504090923090923]]></out_trade_no><result_code><![CDATA[SUCCESS]]></result_code><return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[1269E03E43F2B8C388A414EDAE185CEE]]></sign><time_end><![CDATA[20150324100405]]></time_end><total_fee>1</total_fee><trade_type><![CDATA[JSAPI]]></trade_type><transaction_id><![CDATA[1009530574201503240036299496]]></transaction_id></xml>";
		//String xml ="<xml><appid><![CDATA[wxce16ebd3104459cd]]></appid><attach><![CDATA[支付测试]]></attach><bank_type><![CDATA[CMB_CREDIT]]></bank_type><cash_fee><![CDATA[10]]></cash_fee><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[Y]]></is_subscribe><mch_id><![CDATA[1239825902]]></mch_id><nonce_str><![CDATA[1115051168]]></nonce_str><openid><![CDATA[o6DhFuHVxFsB8G9OzydnE-ye9C3Y]]></openid><out_trade_no><![CDATA[WX201505181115031503]]></out_trade_no><result_code><![CDATA[SUCCESS]]></result_code><return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[79B7AADD9CACBF78B61BD843D37CDDC2]]></sign><time_end><![CDATA[20150518111519]]></time_end><total_fee>1000</total_fee><trade_type><![CDATA[JSAPI]]></trade_type><transaction_id><![CDATA[1004180814201505180136987152]]></transaction_id></xml>";
		String inputLine;
		String notityXml = ""; // 接受到的报文
		String resXml = ""; // 返回给微信的响应

		try {
			while ((inputLine = request.getReader().readLine()) != null) {
				notityXml += inputLine;
			}
			request.getReader().close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("接收到的报文：" + notityXml);

		Map m = parseXmlToList2(notityXml);
		WxPayResult wpr = new WxPayResult();
		wpr.setAppid(m.get("appid").toString());     		// 公众账号 ID
		logger.info("公众账号 ID：" + m.get("appid").toString());
		wpr.setBankType(m.get("bank_type").toString());     // 银行类型，采用字符串类型的银行标识
		logger.info("银行类型：" + m.get("bank_type").toString());
		wpr.setCashFee(m.get("cash_fee").toString());       // 现金支付金额
		logger.info("现金支付金额" + m.get("cash_fee").toString());
		wpr.setFeeType(m.get("fee_type").toString());       // 默认人民币：CNY
		logger.info("默认人民币：CNY" + m.get("fee_type").toString());
		wpr.setIsSubscribe(m.get("is_subscribe").toString());// 用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
		logger.info("用户是否关注公众账号，Y-关注，N-未关注" + m.get("is_subscribe").toString());
		wpr.setMchId(m.get("mch_id").toString());           // 微信支付分配的商户号
		logger.info("微信支付分配的商户号：" + m.get("mch_id").toString());
		wpr.setNonceStr(m.get("nonce_str").toString());     // 随机字符串，不长于32位
		logger.info("随机字符串：" + m.get("nonce_str").toString());
		wpr.setOpenid(m.get("openid").toString());          // 用户在商户appid下的唯一标识
		logger.info("用户在商户appid下的唯一标识openid：" + m.get("openid").toString());
		wpr.setOutTradeNo(m.get("out_trade_no").toString());// 商户系统的订单号，与请求一致。
		logger.info("商户系统的订单号：" + m.get("out_trade_no").toString());
		wpr.setResultCode(m.get("result_code").toString()); // 业务结果 SUCCESS/FAIL
		logger.info("业务结果：" +m.get("result_code").toString());
		wpr.setReturnCode(m.get("return_code").toString()); // 返回状态码
		logger.info("此字段是通信标识.返回状态码：" + m.get("return_code").toString());
														    // 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
		wpr.setSign(m.get("sign").toString());				// 签名
		logger.info("签名：" + m.get("sign").toString());
		wpr.setTimeEnd(m.get("time_end").toString());		// 支付完成时间，格式为yyyyMMddHHmmss
		logger.info("支付完成时间：" + m.get("time_end").toString());
		wpr.setTotalFee(m.get("total_fee").toString());		// 订单总金额，单位为分
		logger.info("订单总金额，单位为分：" + m.get("total_fee").toString());
		wpr.setTradeType(m.get("trade_type").toString());	// 交易类型
		logger.info("交易类型JSAPI、NATIVE、APP：" + m.get("trade_type").toString());
															// JSAPI、NATIVE、APP
		wpr.setTransactionId(m.get("transaction_id").toString());// 微信支付订单号
		logger.info("微信支付订单号：" + m.get("transaction_id").toString());

		if ("SUCCESS".equals(wpr.getResultCode())) {
			// 查询当前订单状态是否是未付款，如果是更改订单状态为你已付款，并放入队列

			if (!StringUtils.isEmpty(wpr.getOutTradeNo())) {
				// 使用了方法锁，保证重复提交并发问题
				this.selectAndUpdateOrder(wpr.getOutTradeNo(), wpr);
			}
			resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
					+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
			logger.info("【内部订单号】" + wpr.getOutTradeNo() + "返回给微信收到响应" + resXml);
		} else {
			resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
					+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
		}

		logger.info("微信支付回调数据结束");

		BufferedOutputStream out = new BufferedOutputStream(
				response.getOutputStream());
		out.write(resXml.getBytes());
		out.flush();
		out.close();
	}

	/**
	 * 该方法加了同步方法锁，为的就是不出现微信回调重复提交问题
	 * 
	 * @param innerOrderNumber
	 * @param wpr
	 * @return
	 */
	public synchronized boolean selectAndUpdateOrder(String innerOrderNumber,
			WxPayResult wpr) {
		// 查询当前订单状态是否是未付款，如果是更改订单状态为你已付款，并放入队列
		OrderData orderData = mobService.searchpayDataList(innerOrderNumber);
		if(orderData==null){
			return false;
		}
		logger.info("【内部订单号】" + innerOrderNumber + "查询当前订单状态是否为未付款（1为未付款）："
				+ orderData.getOrder_status());
		if (ConstUtils.STATE_NON_PAYMENT.equals(orderData
				.getOrder_status())) {
			logger.info("【内部订单号】" + innerOrderNumber + "更改订单状态为已付款");
			// 更改订单状态
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("innerOrderNumber", orderData.getInner_order_number());
			map.put("orderStatus", ConstUtils.STATE_PAY_FINISH);
			map.put("describeMes", "收到微信回调，状态改为：已付款");
			mobService.updateOrder(map);
			// 封装命令对象 放入队列
			OrderCommand command = new OrderCommand();
			command.setResult(wpr);
			QueueMsgController.pushQueue(command);
			logger.info("【内部订单号】" + innerOrderNumber + "放入充值处理队列");
			return true;
		}
		logger.info("【内部订单号】" + innerOrderNumber + "该笔订单状态不是未付款，说明已经操作过了状态为："
				+ orderData.getOrder_status());
		return false;

	}

	/**
	 * （废弃） 话费充值平台回调接受接口 废弃原因：虽然写了这个接受回调的接口，但是后来考虑到并发问题，不打算使用
	 * 因为，本系统会主动查询，这个接口也会去接受，于是针对同一笔订单就会有并发问题，还不如主动查询，废弃接受回调
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("calbackrecv")
	public void calbackrecv(Model model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 接受参数

		String agentid = request.getParameter("agentid");// 商户在梅泰诺缴费接口中的唯一身份标识
		String userToken = request.getParameter("userToken");// 用户的手机号码
		String orderId = request.getParameter("orderId");// 商家的订单编号 也就是发送给上游的订单号
		String amount = request.getParameter("amount");// 订单金额
		String trxamount = request.getParameter("trxamount");// 实际金额
		String Sid = request.getParameter("Sid");// 梅泰诺系统订单
		String returncode = request.getParameter("returncode");// 订单状态
																// 1,处理中；2，已提交充值；3，已发货；4，成功；5，失败；
		String sign = request.getParameter("sign");// MD5签名校验
		// 判断状态，更新订单状态
		if (ConstUtils.agentid.equals(agentid) && returncode != null) {
			// 更改订单状态(根据发送给上游的订单号更改状态，而非内部单号)
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("sendOrderNumber", orderId);
			switch (returncode) {
			case "4":
				map.put("orderStatus", ConstUtils.STATE_SUCCESS);
				map.put("describeMes", "收到上游回调，状态改为："
						+ ConstUtils.STATE_DESC_SUCCESS);
				break;
			case "5":
				map.put("orderStatus", ConstUtils.STATE_FAIL);
				map.put("describeMes", "收到上游回调，状态改为："
						+ ConstUtils.STATE_DESC_FAIL);
				// liuyifeng
				// 失败了要退款
			default:
				break;
			}
			mobService.updateOrderBySendOrderNumber(map);
		}
		// 返回给上游success
		response.getWriter().print("success");
	}

	/**
	 * description: 解析微信通知xml
	 * 
	 * @param xml
	 * @return
	 * @author ex_yangxiaoyi
	 * @see
	 */
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	private static Map parseXmlToList2(String xml) {
		Map retMap = new HashMap();
		try {
			StringReader read = new StringReader(xml);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			// 创建一个新的SAXBuilder
			SAXBuilder sb = new SAXBuilder();
			// 通过输入源构造一个Document
			Document doc = (Document) sb.build(source);
			Element root = doc.getRootElement();// 指向根节点
			List<Element> es = root.getChildren();
			if (es != null && es.size() != 0) {
				for (Element element : es) {
					retMap.put(element.getName(), element.getValue());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retMap;
	}

/*	public static void main(String[] args) {
		String xml = "<xml><appid><![CDATA[wxb4dc385f953b356e]]></appid><bank_type><![CDATA[CCB_CREDIT]]></bank_type><cash_fee><![CDATA[1]]></cash_fee><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[Y]]></is_subscribe><mch_id><![CDATA[1228442802]]></mch_id><nonce_str><![CDATA[1002477130]]></nonce_str><openid><![CDATA[o-HREuJzRr3moMvv990VdfnQ8x4k]]></openid><out_trade_no><![CDATA[1000000000051249]]></out_trade_no><result_code><![CDATA[SUCCESS]]></result_code><return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[1269E03E43F2B8C388A414EDAE185CEE]]></sign><time_end><![CDATA[20150324100405]]></time_end><total_fee>1</total_fee><trade_type><![CDATA[JSAPI]]></trade_type><transaction_id><![CDATA[1009530574201503240036299496]]></transaction_id></xml>";
		Map m = parseXmlToList2(xml);
		WxPayResult wpr = new WxPayResult();
		wpr.setAppid(m.get("appid").toString());
		wpr.setBankType(m.get("bank_type").toString());
		wpr.setCashFee(m.get("cash_fee").toString());
		wpr.setFeeType(m.get("fee_type").toString());
		wpr.setIsSubscribe(m.get("is_subscribe").toString());
		wpr.setMchId(m.get("mch_id").toString());
		wpr.setNonceStr(m.get("nonce_str").toString());
		wpr.setOpenid(m.get("openid").toString());
		wpr.setOutTradeNo(m.get("out_trade_no").toString());
		wpr.setResultCode(m.get("result_code").toString());
		wpr.setReturnCode(m.get("return_code").toString());
		wpr.setSign(m.get("sign").toString());
		wpr.setTimeEnd(m.get("time_end").toString());
		wpr.setTotalFee(m.get("total_fee").toString());
		wpr.setTradeType(m.get("trade_type").toString());
		wpr.setTransactionId(m.get("transaction_id").toString());
		System.out.println(wpr.toString());
	}*/
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

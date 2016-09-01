package com.miteno.util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.wo.sdk.message.AuthorizationHeader;
import com.wo.sdk.openapi.payment.domain.LittlePaymentRequest;
import com.wo.sdk.openapi.payment.domain.LittlePaymentResponse;
import com.wo.sdk.openapi.payment.domain.PaymentCodeSmsRequest;
import com.wo.sdk.openapi.payment.domain.PaymentCodeSmsResponse;
import com.wo.sdk.openapi.payment.impl.PaymentAPIimpl;

public class PaymentUtils {
	public Logger logger = Logger.getLogger(PaymentUtils.class);

	/**
	 * 3.话费计费
	 * 
	 * @param request
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public LittlePaymentResponse LittlePayment(LittlePaymentRequest request,
			String appKey, String appSecret) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		LittlePaymentResponse response = new LittlePaymentResponse();
		// 鉴权消息头
		AuthorizationHeader header = new AuthorizationHeader();
   
		header.setAppKey(appKey);
		String token = GetTokenUtil.getToken(appKey, appSecret);
		if (!StringUtils.isBlank(token)) {
			header.setToken(token);
			request.setAuthHeader(header);
			// 正文内容其实跟get的URL中'?'后的参数字符串一致
			TreeMap<String, Object> cnts = new TreeMap<String, Object>();
			// 联通手机号
			cnts.put("paymentUser", request.getPaymentUser());
			// 操作类型：0 按次扣费 1 周期性订购
//			cnts.put("paymentType", 0);
			cnts.put("paymentAcount", request.getPaymentAcount());
			// 外部订单号
			cnts.put("outTradeNo", request.getOutTradeNo());
			// 商品名称
			cnts.put("subject", request.getSubject());
			// 描述
			cnts.put("description", request.getDescription());
			// 商品单价
			cnts.put("price", request.getPrice());
			// 购买数量
			cnts.put("quantity", request.getQuantity());
			// 交易金额
			cnts.put("totalFee", request.getTotalFee());
			logger.debug("url="+request.getShowUrl());
			cnts.put("showUrl", request.getShowUrl());
			// 短信验证码
			cnts.put("paymentcodesms", request.getPaymentcodesms());
			// 时间戳
			cnts.put("timeStamp", request.getTimeStamp());
			logger.debug(cnts);
			String secretKey = appKey + "&" + appSecret;
			logger.debug(secretKey+":secretKey");
			String signature = Encrypt.encryptHmacSha1(cnts, secretKey);
			logger.debug(signature+":signature");
		
			
			// 因为是对请求对象进行签名，所以必须放在签名字段赋值结束之后进行签名
			request.setSignature(signature);
			request.setSignType("HMAC-SHA1");

			// 调用服务地址
			request.setUri("http://open.wo.com.cn/openapi/rpc/apppayment/v2.0");

			PaymentAPIimpl pay = new PaymentAPIimpl();

			response = pay.littlePayment(request);
			System.out.println(response.getStatusCode() + " "
					+ response.getReasonPhrase());
			System.out.println(response.getResponseData());
		}
		return response;

	}

	/**
	 * 获取 支付计费短信验证码
	 * 
	 * @param request
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public PaymentCodeSmsResponse PaymentSmsCode(PaymentCodeSmsRequest request,
			String appKey, String appSecret) {
		PaymentCodeSmsResponse response = new PaymentCodeSmsResponse();
		// 鉴权消息头
		AuthorizationHeader header = new AuthorizationHeader();
		
		header.setAppKey(appKey);
		String token = GetTokenUtil.getToken(appKey, appSecret);
		if (!StringUtils.isBlank(token)) {
			header.setToken(token);
			request.setAuthHeader(header);

			// 调用服务地址
			request.setUri("http://open.wo.com.cn/openapi/rpc/paymentcodesms/v2.0");

			PaymentAPIimpl pay = new PaymentAPIimpl();

			response = pay.paymentCodeSms(request);

		}
		return response;

	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	

}

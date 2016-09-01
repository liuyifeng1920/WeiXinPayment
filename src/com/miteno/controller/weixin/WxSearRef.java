package com.miteno.controller.weixin;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.miteno.entity.OrderData;
import com.miteno.entity.weixin.WxRefundQueryPoJo;
import com.miteno.util.ConstUtils;
import com.miteno.util.GetWxOrderno;
import com.miteno.util.RequestHandler;
import com.miteno.util.TenpayUtil;

/**
 * 查询退款接口
 */
public class WxSearRef {
	public static void main(String[] args) {
		OrderData orderdata = new OrderData();
		orderdata.setInner_order_number("201505041736121234");
		WxSearRefund(orderdata);
		System.out.println("11111"+WxSearRefund(orderdata));
	}
	
	public static WxRefundQueryPoJo WxSearRefund(OrderData orderdata){

		//申请退款需要的请求参数
		//必选参数
	String  appid = ConstUtils.APPID2;		//公众账号ID
	String mch_id = ConstUtils.MCH_ID;		//商户号
	String nonce_str = WxPay.getNonceStr();		// 随机字符串

	String transaction_id =orderdata.getInner_order_number();   //微信订单号
	String out_trade_no =orderdata.getInner_order_number();     //商户订单号

	String currTime = TenpayUtil.getCurrTime();       // 16位日期格式为(yyyyMMddHHmmss)
	String strRandom = TenpayUtil.buildRandom(4) + "";// 四位随机数
	String out_refund_no =currTime+strRandom;    //商户退款单号	
	String refund_id =out_trade_no; //微信退款单号
	
	SortedMap<String, String> packageParams = new TreeMap<String, String>();
	
	packageParams.put("appid", appid);
	packageParams.put("mch_id",mch_id);
	packageParams.put("nonce_str", nonce_str);
	packageParams.put("transaction_id",transaction_id );
	packageParams.put("out_trade_no", out_trade_no);
	packageParams.put("out_refund_no",out_refund_no );
	packageParams.put("refund_id",refund_id );
	
	/***/
	RequestHandler reqHandler = new RequestHandler(null, null);
	//reqHandler.init(appid, appsecret, partnerkey);
	//String sign = reqHandler.createSign(packageParams);
	String sign  = WxPubMeth.createSign(packageParams);
	Map<String, String> map = new HashMap<String, String>();
	String xml = "<xml>"
			+"<appid>"+appid+"</appid>"
			+"<mch_id>"+mch_id+"</mch_id>"
			+"<nonce_str>"+nonce_str+"</nonce_str>"
			+"<transaction_id>"+transaction_id+"</transaction_id>"
			+"<out_trade_no>"+out_trade_no+"</out_trade_no>"
			+"<out_refund_no>"+out_refund_no+"</out_refund_no>"
			+"<refund_id>"+refund_id+"</refund_id>"
			+"<sign>"+sign+"</sign>"
				+"</xml>";
	String wxtkUrl ="https://api.mch.weixin.qq.com/pay/refundquery";
	 try {
		map = WxPubMeth.httpclientDataMap(wxtkUrl,xml);
	} catch (Exception e) {
		e.printStackTrace();
	}
	 WxRefundQueryPoJo pojo = new WxRefundQueryPoJo();
	 pojo.setReturn_code(map.get("return_code"));
		pojo.setReturn_msg(map.get("return_msg"));    //返回信息
		pojo.setErr_code_des(map.get("err_code_des"));   //错误描述
		pojo.setTransaction_id(map.get("transaction_id"));   //微信订单号
		pojo.setOut_trade_no(map.get("out_trade_no"));   //商户订单号
		pojo.setTotal_fee(map.get("total_fee"));   //订单总金额
		pojo.setFee_type(map.get("fee_type"));   //订单金额货币种类
		pojo.setCash_fee(map.get("cash_fee"));   //现金支付金额
		pojo.setRefund_fee(map.get("refund_fee"));   //退款金额
		pojo.setCoupon_refund_fee(map.get("coupon_refund_fee"));  //代金券或立减优惠退款金额
		pojo.setRefund_count(map.get("refund_count"));   //退款笔数
		pojo.setOut_refund_no_$n(map.get("out_refund_no_$n"));   //商户退款单号
		pojo.setRefund_id_$n(map.get("refund_id_$n"));   //微信退款单号
		pojo.setRefund_channel_$n(map.get("refund_channel_$n"));  //退款渠道
		pojo.setRefund_status_$n(map.get("refund_status_$n"));  //退款状态
	return pojo;
	
	}
}

package com.miteno.controller.weixin;

import java.util.SortedMap;
import java.util.TreeMap;

import com.miteno.entity.OrderData;
import com.miteno.util.ConstUtils;
import com.miteno.util.GetWxOrderno;
import com.miteno.util.RequestHandler;
import com.miteno.util.TenpayUtil;
/**
 * 申请退款接口
 */
public class WxRefund {
	
	public static String getReturn( OrderData orderdata){
		//申请退款需要的请求参数
		//必选参数
	String  appid = ConstUtils.APPID2;		//公众账号ID
	String mch_id = ConstUtils.MCH_ID;		//商户号
	String nonce_str = WxPay.getNonceStr();		// 随机字符串
	/**
	 * api中介绍
	 * 商户系统内部的订单号,
	 * transaction_id、out_trade_no二选一，
	 * 如果同时存在优先级：transaction_id> out_trade_no
	 * 个人做法是两个参数用一个值
	 */
	String transaction_id =orderdata.getInner_order_number();   //微信订单号
	String out_trade_no =orderdata.getInner_order_number();     //商户订单号
	/**
	 * out_refund_no
	 * 商户系统内部的退款单号，商户系统内部唯一，同一退款单号多次请求只退一笔
	 * 做法 商户退款订单号自动生成
	 */
	String currTime = TenpayUtil.getCurrTime();       // 16位日期格式为(yyyyMMddHHmmss)
	String strRandom = TenpayUtil.buildRandom(4) + "";// 四位随机数
	
	String out_refund_no =currTime+strRandom;    //商户退款单号	
	String  paramFee = TenpayUtil.getMoney(orderdata.getMoney());  //单位为元 转换成分
	String total_fee =paramFee; //总金额
	String refund_fee =orderdata.getPay_money(); //退款金额 (等于支付金额)
	String op_user_id =ConstUtils.OP_USER_ID; //操作员帐号, 默认为商户号
	
	SortedMap<String, String> packageParams = new TreeMap<String, String>();
	
	packageParams.put("appid", appid);
	packageParams.put("mch_id",mch_id);
	packageParams.put("nonce_str", nonce_str);
	packageParams.put("transaction_id",transaction_id );
	packageParams.put("out_trade_no", out_trade_no);
	packageParams.put("out_refund_no",out_refund_no );
	packageParams.put("total_fee",total_fee );
	packageParams.put("refund_fee",refund_fee );
	packageParams.put("op_user_id",op_user_id );
	
	/***/
	RequestHandler reqHandler = new RequestHandler(null, null);
	//reqHandler.init(appid, appsecret, partnerkey);
	String sign = reqHandler.createSign(packageParams);
	
	String xml = "<xml>"
			+"<appid>"+appid+"</appid>"
			+"<mch_id>"+mch_id+"</mch_id>"
			+"<nonce_str>"+nonce_str+"</nonce_str>"
			+"<transaction_id>"+transaction_id+"</transaction_id>"
			+"<out_trade_no>"+out_trade_no+"</out_trade_no>"
			+"<out_refund_no>"+out_refund_no+"</out_refund_no>"
			+"<total_fee>"+total_fee+"</total_fee>"
			+"<refund_fee>"+refund_fee+"</refund_fee>"
			+"<op_user_id>"+op_user_id+"</op_user_id>"
				+"</xml>";
	String wxtkUrl ="https://api.mch.weixin.qq.com/secapi/pay/refund";
	try {
		ClientCustomSSL.ClientSSl();
	} catch (Exception e) {
		e.printStackTrace();
	}
	String success = GetWxOrderno.getRefund(wxtkUrl, xml);
	return success;
	}
}

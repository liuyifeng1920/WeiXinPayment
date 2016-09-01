package com.miteno.controller.weixin;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.http.ParseException;
import com.miteno.entity.OrderData;
import com.miteno.entity.weixin.WxOrderQueryPoJo;
import com.miteno.util.ConstUtils;

/**
 * 查询订单接口
 * 场景简介
 * 当商户后台、网络、服务器等出现异常，商户系统最终未接收到支付通知；
 * 调用支付接口后，返回系统错误或未知交易状态情况；
 * 调用被扫支付API，返回USERPAYING的状态；
 * 调用关单或撤销接口API之前，需确认支付状态；
 */
public class WxOrderQuery {
	
	public static void main(String[] args) throws ParseException, Exception {
		String transaction_id ="WX201505181218541854";
		OrderData orderdata = new OrderData();
		 orderdata.setInner_order_number(transaction_id);
		 WxOrderQueryPoJo pojo = WxOrderQuery.orderquery(orderdata);
		 System.out.println("pojo"+pojo);
	}
	
	public static WxOrderQueryPoJo orderquery(OrderData orderdata){
		
		String url ="https://api.mch.weixin.qq.com/pay/orderquery";
		String  appid = ConstUtils.APPID2;		//公众账号ID
		String mch_id = ConstUtils.MCH_ID;		//商户号
		String nonce_str = WxPay.getNonceStr();		// 随机字符串
		String transaction_id =orderdata.getInner_order_number();   //微信订单号
		
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str",nonce_str );
		packageParams.put("transaction_id",transaction_id);
		System.out.println(packageParams);
		String sign  = WxPubMeth.createSign(packageParams);
		String xml ="<xml>"
				+"<appid>"+appid+"</appid>"
				+"<mch_id>"+mch_id+"</mch_id>"
				+"<nonce_str>"+nonce_str+"</nonce_str>"
				+"<transaction_id>"+transaction_id+"</transaction_id>"
				+"<sign>"+sign+"</sign>"
				+"</xml>";
		Map<String, String> map = new HashMap<String, String>();
		WxOrderQueryPoJo wor = new WxOrderQueryPoJo();
		try {
			 map = WxPubMeth.httpclientDataMap(url,xml);
			 	
		} catch (Exception e) {
			e.printStackTrace();
		}
		wor.setReturn_code(map.get("return_code"));
	 	wor.setReturn_msg(map.get("return_msg"));
	 	wor.setDevice_info(map.get("device_info")); ;    //设备号
		 wor.setOpenid(map.get("openid")); ;    //用户标识
		 wor.setIs_subscribe(map.get("is_subscribe"));;    // 是否关注公众账号
		 wor.setTrade_type(map.get("trade_type"));;    //交易类型
		 wor.setTrade_state(map.get("trade_state"));;    //交易状态
		 wor.setBank_type(map.get("bank_type"));;    //付款银行
		 wor.setTotal_fee(map.get("total_fee"));;    //总金额
		 wor.setFee_type(map.get("fee_type"));;    //货币种类
		 wor.setCash_fee(map.get("cash_fee"));;    //现金支付金额
		 wor.setCash_fee_type(map.get("cash_fee_type"));;    //现金支付货币类型
		 wor.setCoupon_fee(map.get("coupon_fee"));;    //代金券或立减优惠金额
		 wor.setCoupon_count(map.get("coupon_count"));;    //代金券或立减优惠使用数量
		 wor.setCoupon_batch_id_$n(map.get("coupon_batch_id_$n"));;    //代金券或立减优惠批次ID
		 wor.setCoupon_id_$n(map.get("coupon_id_$n"));;    //代金券或立减优惠ID
		 wor.setCoupon_fee_$n(map.get("coupon_fee_$n"));;    //单个代金券或立减优惠支付金额
		 wor.setTransaction_id(map.get("transaction_id"));;    //微信支付订单号
		 wor.setOut_trade_no(map.get("out_trade_no"));;    //商户订单号
		 wor.setAttach(map.get("attach"));;    //商家数据包
		 wor.setTime_end(map.get("time_end"));;    //支付完成时间
		 wor.setTrade_state_desc(map.get("trade_state_desc"));;    //交易状态描述
		return  wor;
	}
}

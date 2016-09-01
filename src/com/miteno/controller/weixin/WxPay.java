package com.miteno.controller.weixin;

import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.miteno.entity.OrderData;
import com.miteno.entity.weixin.AccessToken;
import com.miteno.util.ConstUtils;
import com.miteno.util.GetWxOrderno;
import com.miteno.util.RequestHandler;
import com.miteno.util.Sha1Util;
import com.miteno.util.TenpayUtil;
import com.miteno.util.WeiXinUtil;

public class WxPay {
	public static void main(String[] args) {
		String openid ="o6DhFuHVxFsB8G9OzydnE-ye9C3Y";
		//微信支付jsApi
		OrderData ord = new OrderData();
		 ord.setInner_order_number("WX201505151313441344");
		 ord.setMoney("50");
		 ord.setDescribe_mes("测试商品");
	    getPackage(ord,openid);
		
		

	}
@SuppressWarnings("static-access")
public static String getPackage( OrderData orderdata, String openId) {
		// 1 参数
		// 订单号微信平台的订单号
		String wxorderId = orderdata.getInner_order_number();
		String partnerkey =  ConstUtils.PARTNERKEY;
		String appsecret = ConstUtils.APPSECRET;
		// ---必须参数
		String  appid = ConstUtils.APPID2;		//公众账号ID
		String mch_id = ConstUtils.MCH_ID;		//商户号
		String nonce_str = getNonceStr();		// 随机字符串
		String body = orderdata.getDescribe_mes();	// 商品描述根据情况修改
		String out_trade_no = wxorderId;		// 商户订单号
		String totalFee = getMoney(orderdata.getPay_money());	// 总金额以分为单位，不带小数点
		// 订单生成的机器 IP 獲取用戶信息是獲取
		String spbill_create_ip = "127.0.0.1";
		// 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
		String notify_url = ConstUtils.NOTIFY_URL;	//通知地址
		String trade_type = "JSAPI";				//交易类型
		String attach = "";							// 附加数据 原样返回
		AccessToken at = WeiXinUtil.getAccessToken(appid, appsecret);
		System.out.println("获取token:"+at.getToken() +"     "   +"expiresIn:"+at.getExpiresIn());
		if (null != at) {
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id",mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", "话费充值");//商品描述
		packageParams.put("out_trade_no", out_trade_no); //商户订单号
		packageParams.put("total_fee", totalFee); 		 //总金额	 (这里写的金额为1 分到时修改需注意)
		packageParams.put("spbill_create_ip", spbill_create_ip); //终端IP
		packageParams.put("attach", "支付测试");
		packageParams.put("notify_url", notify_url); //通知地址
		packageParams.put("trade_type", trade_type); //交易类型
		//packageParams.put("attach", attach); 		//附加数据
		packageParams.put("openid", openId); //不是必須參數
		//packageParams.put("partnerkey", partnerkey); 
		RequestHandler reqHandler = new RequestHandler(null, null);
		reqHandler.init(appid, appsecret, partnerkey);
		System.out.println("packageParams"+packageParams);
		
		String sign = reqHandler.createSign(packageParams);
		System.out.println("sign"+sign);
		String xml = "<xml>" 
						+ "<appid>"+appid+"</appid>" 
						+ "<mch_id>"+mch_id+"</mch_id>" 
						+ "<nonce_str>"+nonce_str+"</nonce_str>" 
						+ "<sign>"+sign+"</sign>"
						+ "<body><![CDATA[话费充值]]></body>" 
						+ "<out_trade_no>"+out_trade_no+"</out_trade_no>" 
						+ "<attach>支付测试</attach>"
						+ "<total_fee>"+totalFee+"</total_fee>"
						+ "<spbill_create_ip>"+spbill_create_ip+"</spbill_create_ip>" 
						+ "<notify_url>"+notify_url+"</notify_url>" 
						+ "<trade_type>"+trade_type+"</trade_type>" 
						+ "<openid>"+openId+"</openid>"
					+ "</xml>";
		System.out.println("xml"+xml);
		String prepay_id = "";
		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		//返回预支付ID
		prepay_id = GetWxOrderno.getPayNo(createOrderURL, xml);
		System.out.println("prepay_id"+prepay_id);
			//if(prepay_id !=null &&  !"".equals(prepay_id)){
				System.out.println("返回预支付ID:"+prepay_id);
				//获取prepay_id后，拼接最后请求支付所需要的package
				SortedMap<String, String> finalpackage = new TreeMap<String, String>();
				String timestamp = Sha1Util.getTimeStamp();
				String packages = "prepay_id="+prepay_id;
				finalpackage.put("appId", appid);  
				finalpackage.put("timeStamp", timestamp);  
				finalpackage.put("nonceStr", nonce_str);  
				finalpackage.put("package", packages);  
				finalpackage.put("signType", "MD5");
				//要签名
				String finalsign = reqHandler.createSign(finalpackage);
				String finaPackage = "{\"appId\":\"" + appid + "\",\"timeStamp\":\"" + timestamp
				+ "\",\"nonceStr\":\"" + nonce_str + "\",\"package\":\""
				+ packages + "\",\"signType\" : \"MD5" + "\",\"paySign\":\""
				+ finalsign + "\"}";
				//String result = "{" + appid + " , " + timestamp + " ,"+nonce_str+" ,"+packages+","+finalsign+"}";
				System.out.println("V3 jsApi package:"+finaPackage);
				//System.out.println("V3 jsApi result:"+result);
				return finaPackage;
			//}else{
				//System.out.println("预支付ID返回失败"+prepay_id);
			//}
		}else{
			System.out.println("token獲取失敗"+at);
		}
		return  null;
	}


/**
 * 获取随机字符串
 * @return
 */
public static String getNonceStr() {
	// 随机数
	String currTime = TenpayUtil.getCurrTime();
	// 8位日期
	String strTime = currTime.substring(8, currTime.length());
	// 四位随机数
	String strRandom = TenpayUtil.buildRandom(4) + "";
	// 10位序列号,可以自行调整。
	return strTime + strRandom;
}

/**
 * 元转换成分
 * @param money
 * @return
 */
public static String getMoney(String amount) {
	if(amount==null){
		return "";
	}
	// 金额转化为分为单位
	String currency =  amount.replaceAll("\\$|\\￥|\\,", "");  //处理包含, ￥ 或者$的金额  
    int index = currency.indexOf(".");  
    int length = currency.length();  
    Long amLong = 0l;  
    if(index == -1){  
        amLong = Long.valueOf(currency+"00");  
    }else if(length - index >= 3){  
        amLong = Long.valueOf((currency.substring(0, index+3)).replace(".", ""));  
    }else if(length - index == 2){  
        amLong = Long.valueOf((currency.substring(0, index+2)).replace(".", "")+0);  
    }else{  
        amLong = Long.valueOf((currency.substring(0, index+1)).replace(".", "")+"00");  
    }  
    return amLong.toString(); 
}

}

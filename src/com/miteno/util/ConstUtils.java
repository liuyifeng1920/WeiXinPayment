package com.miteno.util;



import java.net.URLEncoder;

import com.miteno.controller.weixin.WxGetOpenId;
 //定義常量
public class ConstUtils {
	
	/***************************订单状态定义********************************/
	//order_status; //充值状态（1未支付，2已付款，3处理中，4成功，5失败）
	
	/** 1未支付*/
	public  static final String STATE_NON_PAYMENT="1"; 
	public  static final String STATE_DESC_NON_PAYMENT="1未支付";
	/**2已付款*/
	public  static final String STATE_PAY_FINISH="2";    
	public  static final String STATE_DESC_PAY_FINISH="2已付款";   
	
	/**3处理中*/
	public  static final String STATE_PAYINGF="3";    
	public  static final String STATE_DESC_PAYING="3处理中";
	
	/**4成功*/
	public  static final String STATE_SUCCESS="4";   
	public  static final String STATE_DESC_SUCCESS="4成功";
	/**5失败*/
	public  static final String STATE_FAIL="5";      
	public  static final String STATE_DESC_FAIL="5-失败";
	
	/**6-需人工处理*/
	public  static final String STATE_SENDED="6";      
	public  static final String STATE_DESC_SENDED="6-需人工处理";
	/**7已退款*/
	public  static final String STATE_MONEY_BACK="7";      
	public  static final String STATE_DESC_MONEY_BACK="7-已退款";
	
	/***************************话费平台相关定义定义********************************/
	public  static final String agentid="100856";//梅泰诺提供的代理商编号
	public  static final String callbackurl="";  //我们自己设置的url，梅泰诺的话费充值平台会回调这个url（这里放弃回调，主动查询，避免并发）
	public  static final String key="En6wkp1oGRGxUYZMLZOG";//梅泰诺提供
	public static final String  submit_url="http://118.144.88.30:8081/Receiver";				//梅泰诺提供的话费充值接口地址
	public static final String  queryOrder_url="http://118.144.88.30:8081/QueryOrder";				//梅泰诺提供查询接口
	public static final int query_interval=4;	 //查询上游时间间隔
	
	
	
	
	
	/***************************微信相关定义********************************/
	
	//String appId = "wx184bb69065e9bdef";//测试环境
	//String appId = "wxce16ebd3104459cd";//正式环境
	//String appSecret = "54f8a9a383fe2541889a39a1311bff96";//测试环境
	//String appSecret = "62b9eddb8a5b933aed08f7571d1a93e6";//正式环境
	/**公众账号ID*/
	//public static final String APPID = "wx184bb69065e9bdef";
	public static final String APPID2 = "wxce16ebd3104459cd";//正式环境
	/**应用密钥*/
	//public static final String APPSECRET = "54f8a9a383fe2541889a39a1311bff96";
	public static final String APPSECRET = "62b9eddb8a5b933aed08f7571d1a93e6";//正式环境
	/**常量固定值*/ 
	public static final String GRANT_TYPE = "client_credential";
	/**微信支付成功后通知地址 必须要求80端口并且地址不能带参数*/
	public static final String NOTIFY_URL = "http://www.sopaylife.com/WeiXinPayment/background/notify/recv.action";
	/**商戶號*/
	public static final String MCH_ID="1239825902";
	/**操作员账号*/
	public static final String OP_USER_ID="sopaylife";
	/**这个参数partnerkey是在商户后台配置的一个32位的key,微信商户平台-账户设置-安全设置-api安全*/
	public static final String PARTNERKEY = "eaFCx5HiXMceJiXORgHnyevJehHqkaOy";
	
	/**获取用户授权的scope*/
	public static final String SNSAPI_BASE = "snsapi_base";
	public static final String SNSAPI_USERINFO  = "snsapi_userinfo";
	

	public static final String REDIRECT_URI  = "http://www.sopaylife.com/WeiXinPayment/background2/backCode.action" ;  


	
}

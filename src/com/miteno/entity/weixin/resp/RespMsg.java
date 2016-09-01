package com.miteno.entity.weixin.resp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import com.miteno.util.MessageUtil;
import com.miteno.util.WeiXinUtil;

/**
 * 消息回复
 * @author ChenPeng
 *
 */
public class RespMsg {
	
	/**
	 * 默认回复（初始化回复）
	 * @param fromUserName
	 * @param toUserName
	 * @return
	 */
	public static NewsMessage default_Reply(String fromUserName, String toUserName){
		// 默认回复此文本消息
		// 创建图文消息
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		
		List<Article> articleList = new ArrayList<Article>();
		Article article = new Article();
		article.setTitle("请你猛戳！");
		article.setDescription("苏付平台是梅泰诺（北京）移动信息技术有限公司与江苏联通的战略合作，成功的为商户和用户之间搭建了一座沟通桥梁");
		article.setPicUrl("https://mmbiz.qlogo.cn/mmbiz/AGsFF9f10WoDQFgLIR3GECoZn0MzdoIbz9PLptssia0tu10nBsic8FyZKRVIgFKNIictiaawlvV5R0e3ibvMpkXM7bw/0");
		article.setUrl("http://58.240.96.236/WeiXinPayment/background/microui/article2.action");
		
		Article article1 = new Article();
		article1.setTitle("我为什么要关注你");
		article1.setDescription("");
		article1.setPicUrl("https://mmbiz.qlogo.cn/mmbiz/AGsFF9f10WrPHDibYuInTZXSctSS1XqoXGK0htiaC25ic8qsX62oI5ZhkC4pa5jhkczmROU0oUMq2W5iaqI1lIpksQ/0?wx_fmt=png");
		article1.setUrl("http://58.240.96.236/WeiXinPayment/background/microui/article1.action");
/*
		Article article2 = new Article();
		article2.setTitle("苏付标题3");
		article2.setDescription("");
		article2.setPicUrl("http://www.sopaylife.com/images/cannel/C_2.png");
		article2.setUrl("http://www.sopaylife.com");

		Article article3 = new Article();
		article3.setTitle("苏付标题4");
		article3.setDescription("");
		article3.setPicUrl("http://www.sopaylife.com/images/cannel/C_2.png");
		article3.setUrl("http://www.sopaylife.com");*/

		articleList.add(article);
		articleList.add(article1);
	/*	articleList.add(article2);
		articleList.add(article3);*/
		
		newsMessage.setArticleCount(articleList.size());
		newsMessage.setArticles(articleList);
		
		return newsMessage;
	}
	
	/**
	 * 公司简介
	 * @param fromUserName
	 * @param toUserName
	 */
	public static NewsMessage intro(String fromUserName, String toUserName){
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		
		List<Article> articleList = new ArrayList<Article>();
		
		Article article = new Article();  
        article.setTitle("限公司");  
        article.setDescription("是一家专业从事卫星通信和远程医疗产品开发与服务的高新技术企业，成立于1998年6月。");
        article.setPicUrl("http://1.weixin1230.sinaapp.com/image/00.png");  
        article.setUrl("http://1.weixin1230.sinaapp.com/HTML/CompanyProfile.html");  
        articleList.add(article);  
        // 设置图文消息个数  
        newsMessage.setArticleCount(articleList.size()); 
        // 设置图文消息包含的图文集合  
        newsMessage.setArticles(articleList); 
        
        return newsMessage;
	}
	
	/**
	 * 医疗产品
	 * @param fromUserName
	 * @param toUserName
	 * @return
	 */
	public static TextMessage medical_Product(String fromUserName, String toUserName){
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		
		String content="您好！这里是北京蓝卫通远程医疗产品中心。 \n请回复数字查看不同类型的产品：\n"
						+"211    远程医疗系统  \n"
						+"212    远程医疗软件  \n"
						+"213    健康管理系统  \n"
						+"214    医学影像系统  \n";
		
		textMessage.setContent(content);
		
		return textMessage;
		
	}

	/*
	 * 处理地理位置
	 */
	public static NewsMessage getMap(String fromUserName, String toUserName, String location_X, String location_Y){
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		
		List<Article> articleList = new ArrayList<Article>();
		
		Article article = new Article();  
        article.setTitle("附近医院");  
        article.setDescription("");
        article.setPicUrl("http://api.map.baidu.com/staticimage?center="+location_Y+","+location_X+"&width=360&height=220&zoom=12");  
        article.setUrl("http://1.weixin1230.sinaapp.com/HTML/Map.html?location_X="+location_X+"&location_Y="+location_Y+"");  
        articleList.add(article);  
        // 设置图文消息个数  
        newsMessage.setArticleCount(articleList.size()); 
        // 设置图文消息包含的图文集合  
        newsMessage.setArticles(articleList); 
		
        return newsMessage;
	}
	
	/*
	 * 常用处理
	 */
	public static TextMessage getText(String fromUserName, String toUserName){
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		
		textMessage.setContent("感谢您关注苏付生活，我们为您提供内容丰富的优惠信息，让您足不出户省钱到家\ue056 ");
		
		return textMessage;
	}
	
	public static TextMessage getMedical_Site(String fromUserName, String toUserName){
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		
		textMessage.setContent("你好，请点击文字输入旁边的\"+\",把您的位置发给蓝卫通，即可查看附近医院哦 \ue056 ");
		
		return textMessage;
	}

	private static String person_message_url="https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	/**
	 * 获取用户信息
	 * @param fromUserName
	 * @param toUserName
	 * @return
	 */
	public static TextMessage getPersonMessage(String fromUserName,
			String toUserName) {
		
		JSONObject jsonObject = getMessage(fromUserName);
		
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		
//		textMessage.setContent("--------------");
		textMessage.setContent(jsonObject.getString("nickname"));
		return textMessage;
	}
	
	/**
	 * 获取用户信息数据包
	 * @param fromUserName 用户的openID
	 * @return
	 */
	public static JSONObject getMessage(String fromUserName){
		// 第三方用户唯一凭证  
        String appId = "wx1b3aaabc6d54920d";  
        // 第三方用户唯一凭证密钥  
        String appSecret = "6e1bc3089764065e3534b4496f45b616";
        
        // 调用接口获取access_token  
        String access_token = WeiXinUtil.getAccessToken(appId, appSecret).getToken();
        
        person_message_url=person_message_url.replace("ACCESS_TOKEN", access_token).replace("OPENID", fromUserName);
        
        JSONObject jsonObject = WeiXinUtil.httpRequest(person_message_url, "GET", null);
        
        return jsonObject;
	}
	
}

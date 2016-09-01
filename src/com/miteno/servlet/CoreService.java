package com.miteno.servlet;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.miteno.entity.weixin.resp.NewsMessage;
import com.miteno.entity.weixin.resp.RespMsg;
import com.miteno.entity.weixin.resp.TextMessage;
import com.miteno.util.MessageUtil;

/**
 * 核心服务类
 */
public class CoreService {
	
	public static String location_X = null;	//纬度
	public static String location_Y = null;	//经度
	
	/**
	 * 处理微信发来的请求
	 * @param request
	 * @return
	 */
	@SuppressWarnings("all")
	public static String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";

			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				TextMessage textMessage = RespMsg.getText(fromUserName, toUserName);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				TextMessage textMessage = RespMsg.getText(fromUserName, toUserName);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				location_X = requestMap.get("Location_X");
				location_Y = requestMap.get("Location_Y");
				NewsMessage newsMessage = RespMsg.getMap(fromUserName, toUserName, location_X, location_Y);
				respMessage = MessageUtil.newsMessageToXml(newsMessage);
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				TextMessage textMessage = RespMsg.getText(fromUserName, toUserName);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}
			// 音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				TextMessage textMessage = RespMsg.getText(fromUserName, toUserName);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					NewsMessage newsMessage = RespMsg.default_Reply(fromUserName, toUserName);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}
				// 用户地理位置
				else if(eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)){
					
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建自定义菜单时指定的KEY值对应
					String eventKey = requestMap.get("EventKey");

					if (eventKey.equals("11")) {		//公司简介
						NewsMessage newsMessage = RespMsg.intro(fromUserName, toUserName);
						respMessage = MessageUtil.newsMessageToXml(newsMessage);
					} /*else if (eventKey.equals("12")) {
						
					} else if (eventKey.equals("13")) {	//附近医院
						TextMessage textMessage = RespMsg.getMedical_Site(fromUserName, toUserName);
						respMessage = MessageUtil.textMessageToXml(textMessage);
					} else if (eventKey.equals("14")) {	//用户信息
						TextMessage textMessage = RespMsg.getPersonMessage(fromUserName, toUserName);
						respMessage = MessageUtil.textMessageToXml(textMessage);
					} else if (eventKey.equals("21")) {
						TextMessage textMessage = RespMsg.getText(fromUserName, toUserName);
						respMessage = MessageUtil.textMessageToXml(textMessage);
					} else if (eventKey.equals("22")) {	//医疗产品
						// 回复文本消息
						TextMessage textMessage = RespMsg.medical_Product(fromUserName, toUserName);
						respMessage = MessageUtil.textMessageToXml(textMessage);
					
					} else if (eventKey.equals("23")) {
						TextMessage textMessage = RespMsg.getText(fromUserName, toUserName);
						respMessage = MessageUtil.textMessageToXml(textMessage);
					} else if (eventKey.equals("24")) {
						TextMessage textMessage = RespMsg.getText(fromUserName, toUserName);
						respMessage = MessageUtil.textMessageToXml(textMessage);
					} else if (eventKey.equals("25")) {
						TextMessage textMessage = RespMsg.getText(fromUserName, toUserName);
						respMessage = MessageUtil.textMessageToXml(textMessage);
					} else if (eventKey.equals("31")) {
						TextMessage textMessage = RespMsg.getText(fromUserName, toUserName);
						respMessage = MessageUtil.textMessageToXml(textMessage);
					} else if (eventKey.equals("32")) {
						TextMessage textMessage = RespMsg.getText(fromUserName, toUserName);
						respMessage = MessageUtil.textMessageToXml(textMessage);
					} else if (eventKey.equals("33")) {
						TextMessage textMessage = RespMsg.getText(fromUserName, toUserName);
						respMessage = MessageUtil.textMessageToXml(textMessage);
					}*/
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;
	}
}
package com.miteno.entity.weixin.req;

/**
 * @annotate 这是文本消息类
 * @author Stapler
 *
 */
public class TextMessage extends BaseMessage{
	
	//文本类容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
	
	
}

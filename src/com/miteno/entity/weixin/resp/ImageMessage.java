package com.miteno.entity.weixin.resp;

/**
 * 返回图片消息
 * 
 * @author Stapler
 * @date 2013年12月27日
 * 
 */
public class ImageMessage extends BaseMessage {

	private String MediaId;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

}

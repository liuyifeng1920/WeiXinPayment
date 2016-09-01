package org.stapler.weixin.message.req;

/**
 * 
 * @annotate 语音消息
 * @author Stapler
 *
 */
public class VoiceMessage extends BaseMessage{
	
	//语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
	private String MediaId;
	
	//语音格式
	private String Format;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getFormat() {
		return Format;
	}

	public void setFormat(String format) {
		Format = format;
	}
	
}

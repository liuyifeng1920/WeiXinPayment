package com.miteno.entity.weixin.resp;

/**
 * 
 * @annotate 这是音乐消息
 * @author Stapler
 * 
 */
public class MusicMessage extends BaseMessage {

	private Music music;

	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
	}

}

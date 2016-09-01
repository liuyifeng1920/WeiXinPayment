package com.miteno.entity.weixin;

/**
 * 二级菜单
 * 
 * @author ChenPeng
 * 
 */
public class CommonUrlButton extends Button {
	private String type;
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


}

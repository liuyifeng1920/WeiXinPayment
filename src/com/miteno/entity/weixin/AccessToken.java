package com.miteno.entity.weixin;

/**
 * 全局唯一票据
 * @author ChenPeng
 *
 */
public class AccessToken {

	private String token;

	private int expiresIn;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

}

package com.miteno.entity;

/**
 * 计费渠道表
 */

public class BillingChannel {

	/**
	 * 计费渠道id
	 */

	private String appid;

	/**
	 * 计费渠道名称
	 */
	private String appname;

	/**
	 * app标识
	 */
	private String appkey;

	/**
	 * app秘钥
	 */
	private String appsecret;

	/**
	 * 开发者用户ID
	 */
	private String platformID;

	/**
	 * 开发者调用密码
	 */
	private String password;

	public String getPlatformID() {
		return platformID;
	}

	public void setPlatformID(String platformID) {
		this.platformID = platformID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	public BillingChannel(String appid, String appname, String appkey,
			String appsecret, String platformID, String password) {
		super();
		this.appid = appid;
		this.appname = appname;
		this.appkey = appkey;
		this.appsecret = appsecret;
		this.platformID = platformID;
		this.password = password;
	}

	public BillingChannel() {
		super();
		// TODO Auto-generated constructor stub
	}
}

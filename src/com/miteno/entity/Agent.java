package com.miteno.entity;


public class Agent implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  String businessid;			//商家id   pk
	private  String appid;				//计费渠道id  fk billing_channel
	private  String businessname;		//商家名称。
	private  String businesstel;		//商家电话
	private  String businessadd;		//商家地址 
	private  Integer daylimit;			//日限额
	private  Integer mouthlimit;			//月限额
	
	private String appkey;				//非代理商表属性，是渠道表属性
	private String appsecret;			//非代理商表属性，是渠道表属性
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
	public String getBusinessid() {
		return businessid;
	}
	public void setBusinessid(String businessid) {
		this.businessid = businessid;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getBusinessname() {
		return businessname;
	}
	public void setBusinessname(String businessname) {
		this.businessname = businessname;
	}
	public String getBusinesstel() {
		return businesstel;
	}
	public void setBusinesstel(String businesstel) {
		this.businesstel = businesstel;
	}
	public String getBusinessadd() {
		return businessadd;
	}
	public void setBusinessadd(String businessadd) {
		this.businessadd = businessadd;
	}
	public Integer getDaylimit() {
		return daylimit;
	}
	public void setDaylimit(Integer daylimit) {
		this.daylimit = daylimit;
	}
	public Integer getMouthlimit() {
		return mouthlimit;
	}
	public void setMouthlimit(Integer mouthlimit) {
		this.mouthlimit = mouthlimit;
	}

	
	
}

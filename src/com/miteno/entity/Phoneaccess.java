package com.miteno.entity;


public class Phoneaccess implements java.io.Serializable{
	/**
	 * 号段对应表
	 */
	private static final long serialVersionUID = 1L;
	private  int id;			//id   
	private  String numberno;				//号码段
	private  String province;		//省
	private  String city;		//市
	private  String code;		//区号
	private  String postnum;			//邮政编码
	private  String operator;			//运营商 0移动1电信2联通
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumberno() {
		return numberno;
	}
	public void setNumberno(String numberno) {
		this.numberno = numberno;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPostnum() {
		return postnum;
	}
	public void setPostnum(String postnum) {
		this.postnum = postnum;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}

	
	
}

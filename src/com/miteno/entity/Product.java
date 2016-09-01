package com.miteno.entity;

public class Product implements java.io.Serializable{
	/**
	 * 货架表
	 */
	private static final long serialVersionUID = 1L;
	private String product_id; //产品id，主键id
	private String operator; //运营商
	private String province; //省份
	private String parvalue ; //面值	
	private String showable;  //是否显示 1显示 0不显示
	private String price_scope; //价格区间
	private String price; //售价
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getParvalue() {
		return parvalue;
	}
	public void setParvalue(String parvalue) {
		this.parvalue = parvalue;
	}
	public String getShowable() {
		return showable;
	}
	public void setShowable(String showable) {
		this.showable = showable;
	}
	public String getPrice_scope() {
		return price_scope;
	}
	public void setPrice_scope(String price_scope) {
		this.price_scope = price_scope;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	
	
}

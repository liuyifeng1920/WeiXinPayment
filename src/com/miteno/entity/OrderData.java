package com.miteno.entity;

import java.util.Date;


public class OrderData implements java.io.Serializable{
	/**
	 * 交易历史
	 */
	private static final long serialVersionUID = 1L;
	private Integer order_id;      		//主键自增
	private String phone_number;        	// 手机号11位
	private String recharge_date;        		//充值日期（第一次入库时间，微信用户提交时间）
	private String inner_order_number;       	//内部订单号，微信平台的订单号
	private String send_order_number;      		//提交给花费充值平台的订单号
	private String money;      			//充值金额
	private String order_status;      			//充值状态（1未支付，2已付款，3处理中，4成功，5失败）
	private String describe_mes;      			//描述信息
	private String pay_money; //支付金额
	private String wxpt_order_number;        //微信平台订单号
	
	private String startDate;    //日期查询开始时间 传参数用的 与数据库中表无关
	private String endDate;       //日期查询结束时间 传参数用的 与数据库中表无关
	
	private Date firstTime;//存入队列的时间 (非数据库字段)
	private int count=0;		//放入队列的查询次数
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Date getFirstTime() {
		return firstTime;
	}
	public void setFirstTime(Date firstTime) {
		this.firstTime = firstTime;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getRecharge_date() {
		return recharge_date;
	}
	public void setRecharge_date(String recharge_date) {
		this.recharge_date = recharge_date;
	}
	public String getInner_order_number() {
		return inner_order_number;
	}
	public void setInner_order_number(String inner_order_number) {
		this.inner_order_number = inner_order_number;
	}
	public String getSend_order_number() {
		return send_order_number;
	}
	public void setSend_order_number(String send_order_number) {
		this.send_order_number = send_order_number;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	public String getDescribe_mes() {
		return describe_mes;
	}
	public void setDescribe_mes(String describe_mes) {
		this.describe_mes = describe_mes;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getPay_money() {
		return pay_money;
	}
	public void setPay_money(String pay_money) {
		this.pay_money = pay_money;
	}

	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getWxpt_order_number() {
		return wxpt_order_number;
	}
	public void setWxpt_order_number(String wxpt_order_number) {
		this.wxpt_order_number = wxpt_order_number;
	}
	
}

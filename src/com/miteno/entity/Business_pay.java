package com.miteno.entity;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 话费计费表
 */

public class Business_pay {

	/**
	 * 记录ID
	 */
	
	private String recordId;

	/**
	 * 商家ID
	 */
	private String bussinessId;

	/**
	 * 计费渠道id
	 */
	private String appid;

	/**
	 * 加密码
	 */
	private String access_token;
	/**
	 * 联通手机号
	 */
	private String paymentUser;

	/**
	 * 合作伙伴商户的唯一订单号
	 */
	private String outTradeNo;

	/**
	 * 支付账户类型，默认001话费账户
	 */
	private String paymentAcount;

	/**
	 * 商品名称
	 */
	private String subject;

	/**
	 * 商品描述
	 */
	private String description;

	/**
	 * 商品单价
	 */
	private String price;

	/**
	 * 购买数量
	 */
	private String quantity;

	/**
	 * 交易金额 :单位：元
	 */
	private String totalFee;

	/**
	 * 商品展示网址
	 */
	private String showUrl;

	/**
	 * 时间戳
	 */
	private Date timeStamp;
	
	/**
	 * 默认按次支付
	 */
	private String productID;
	
	/**
	 * 结果码:0:成功；1：错误
	 */
	private String resultCode;

	/**
	 * 结果描述
	 */
	private String resultDescription;

	/**
	 * 交易流水号
	 */
	private String transactionId;
	/**
	 * 查询时间用到 
	 * startDate 开始时间
	 * endDate 结束时间
	 */
	private Date startDate;
	private Date endDate;
	public Business_pay() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Business_pay( String bussinessId, String appid,
			String access_token, String paymentUser, String outTradeNo,
			String paymentAcount, String subject, String description,
			String price, String quantity, String totalFee, String showUrl,
			Date timeStamp, String productID, String status, String recordtime,
			String resultCode, String resultDescription, String transactionId) {
		super();
		
		this.bussinessId = bussinessId;
		this.appid = appid;
		this.access_token = access_token;
		this.paymentUser = paymentUser;
		this.outTradeNo = outTradeNo;
		this.paymentAcount = paymentAcount;
		this.subject = subject;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.totalFee = totalFee;
		this.showUrl = showUrl;
		this.timeStamp = timeStamp;
		this.productID = productID;
		this.resultCode = resultCode;
		this.resultDescription = resultDescription;
		this.transactionId = transactionId;
	}



	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getBussinessId() {
		return bussinessId;
	}

	public void setBussinessId(String bussinessId) {
		this.bussinessId = bussinessId;
	}


	public String getPaymentUser() {
		return paymentUser;
	}

	public void setPaymentUser(String paymentUser) {
		this.paymentUser = paymentUser;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getPaymentAcount() {
		return paymentAcount;
	}

	public void setPaymentAcount(String paymentAcount) {
		this.paymentAcount = paymentAcount;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShowUrl() {
		return showUrl;
	}

	public void setShowUrl(String showUrl) {
		this.showUrl = showUrl;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp=timeStamp;
	}
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	

	public String getResultDescription() {
		return resultDescription;
	}

	public void setResultDescription(String resultDescription) {
		this.resultDescription = resultDescription;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
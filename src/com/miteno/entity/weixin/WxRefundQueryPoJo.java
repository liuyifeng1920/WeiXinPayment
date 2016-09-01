package com.miteno.entity.weixin;
/**
 * 查询退款实体 只是部分参数
 */
public class WxRefundQueryPoJo {
	private String  return_code  ;  //返回状态码
	private String  return_msg  ;  //返回信息
	private String   err_code_des ;  //错误描述
	private String   transaction_id ;  //微信订单号
	private String   out_trade_no ;  //商户订单号
	private String   total_fee ;  //订单总金额
	private String   fee_type ;  //订单金额货币种类
	private String   cash_fee ;  //现金支付金额
	private String   refund_fee ;  //退款金额
	private String    coupon_refund_fee;  //代金券或立减优惠退款金额
	private String   refund_count ;  //退款笔数
	private String   out_refund_no_$n ;  //商户退款单号
	private String   refund_id_$n ;  //微信退款单号
	private String    refund_channel_$n;  //退款渠道
	private String    refund_status_$n;  //退款状态
	public String getReturn_code() {
		return return_code;
	}
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
	public String getReturn_msg() {
		return return_msg;
	}
	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
	public String getErr_code_des() {
		return err_code_des;
	}
	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	public String getCash_fee() {
		return cash_fee;
	}
	public void setCash_fee(String cash_fee) {
		this.cash_fee = cash_fee;
	}
	public String getRefund_fee() {
		return refund_fee;
	}
	public void setRefund_fee(String refund_fee) {
		this.refund_fee = refund_fee;
	}
	public String getCoupon_refund_fee() {
		return coupon_refund_fee;
	}
	public void setCoupon_refund_fee(String coupon_refund_fee) {
		this.coupon_refund_fee = coupon_refund_fee;
	}
	public String getRefund_count() {
		return refund_count;
	}
	public void setRefund_count(String refund_count) {
		this.refund_count = refund_count;
	}
	public String getOut_refund_no_$n() {
		return out_refund_no_$n;
	}
	public void setOut_refund_no_$n(String out_refund_no_$n) {
		this.out_refund_no_$n = out_refund_no_$n;
	}
	public String getRefund_id_$n() {
		return refund_id_$n;
	}
	public void setRefund_id_$n(String refund_id_$n) {
		this.refund_id_$n = refund_id_$n;
	}
	public String getRefund_channel_$n() {
		return refund_channel_$n;
	}
	public void setRefund_channel_$n(String refund_channel_$n) {
		this.refund_channel_$n = refund_channel_$n;
	}
	public String getRefund_status_$n() {
		return refund_status_$n;
	}
	public void setRefund_status_$n(String refund_status_$n) {
		this.refund_status_$n = refund_status_$n;
	}
	
}

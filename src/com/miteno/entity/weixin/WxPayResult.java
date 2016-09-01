
package com.miteno.entity.weixin;

/**
 * description: 微信支付回调
 * @author 
 * @since 
 * @see
 */
public class WxPayResult {
	
	private String appid;
	private String mch_id;
	private String device_info;
	private String nonce_str;
	private String sign;
	private String result_code;
	private String err_code;
	private String err_code_des;
	//错误代码，对应心在api中查找
	private String noauth;
	private String notenough;
	private String orderpaid;
	private String orderclosed;
	private String systemerror;
	private String appid_not_exist;
	private String mchid_not_exist;
	private String appid_mchid_not_match;
	private String lack_params;
	private String out_trade_no_used;
	private String signerror;
	private String xml_format_error;
	private String require_post_method;
	private String post_data_empty;
	private String not_utf8;
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getDevice_info() {
		return device_info;
	}
	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getResult_code() {
		return result_code;
	}
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	public String getErr_code() {
		return err_code;
	}
	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}
	public String getErr_code_des() {
		return err_code_des;
	}
	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}
	public String getNoauth() {
		return noauth;
	}
	public void setNoauth(String noauth) {
		this.noauth = noauth;
	}
	public String getNotenough() {
		return notenough;
	}
	public void setNotenough(String notenough) {
		this.notenough = notenough;
	}
	public String getOrderpaid() {
		return orderpaid;
	}
	public void setOrderpaid(String orderpaid) {
		this.orderpaid = orderpaid;
	}
	public String getOrderclosed() {
		return orderclosed;
	}
	public void setOrderclosed(String orderclosed) {
		this.orderclosed = orderclosed;
	}
	public String getSystemerror() {
		return systemerror;
	}
	public void setSystemerror(String systemerror) {
		this.systemerror = systemerror;
	}
	public String getAppid_not_exist() {
		return appid_not_exist;
	}
	public void setAppid_not_exist(String appid_not_exist) {
		this.appid_not_exist = appid_not_exist;
	}
	public String getMchid_not_exist() {
		return mchid_not_exist;
	}
	public void setMchid_not_exist(String mchid_not_exist) {
		this.mchid_not_exist = mchid_not_exist;
	}
	public String getAppid_mchid_not_match() {
		return appid_mchid_not_match;
	}
	public void setAppid_mchid_not_match(String appid_mchid_not_match) {
		this.appid_mchid_not_match = appid_mchid_not_match;
	}
	public String getLack_params() {
		return lack_params;
	}
	public void setLack_params(String lack_params) {
		this.lack_params = lack_params;
	}
	public String getOut_trade_no_used() {
		return out_trade_no_used;
	}
	public void setOut_trade_no_used(String out_trade_no_used) {
		this.out_trade_no_used = out_trade_no_used;
	}
	public String getSignerror() {
		return signerror;
	}
	public void setSignerror(String signerror) {
		this.signerror = signerror;
	}
	public String getXml_format_error() {
		return xml_format_error;
	}
	public void setXml_format_error(String xml_format_error) {
		this.xml_format_error = xml_format_error;
	}
	public String getRequire_post_method() {
		return require_post_method;
	}
	public void setRequire_post_method(String require_post_method) {
		this.require_post_method = require_post_method;
	}
	public String getPost_data_empty() {
		return post_data_empty;
	}
	public void setPost_data_empty(String post_data_empty) {
		this.post_data_empty = post_data_empty;
	}
	public String getNot_utf8() {
		return not_utf8;
	}
	public void setNot_utf8(String not_utf8) {
		this.not_utf8 = not_utf8;
	}
   

}
package com.miteno.util;

import org.apache.commons.httpclient.HttpStatus;



/**
 * http返回结果
 * 
 */
public class HttpResult {
	private int stateCode;

	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getStateCode() {
		return stateCode;
	}

	/**
	 * 获取请求状态
	 * @return：
	 */
	public boolean getOK() {
		if (this.stateCode == HttpStatus.SC_OK || this.stateCode == HttpStatus.SC_MOVED_PERMANENTLY || this.stateCode == HttpStatus.SC_MOVED_TEMPORARILY) {
			return true;
		} else {
			return false;
		}
	}

	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}
}

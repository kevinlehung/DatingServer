package com.ds.web.webservice.bean;

/**
 * 
 * @author hunglevn@outlook.com
 * 
 */
public class BaseWsBean {
	public static final int DEFAULT_SUCCESS_CODE = 1;
	public static final int DEFAULT_FAILED_CODE = 1;
	
	private int resultCode = DEFAULT_SUCCESS_CODE;

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
}

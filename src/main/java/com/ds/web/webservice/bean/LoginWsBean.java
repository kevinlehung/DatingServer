package com.ds.web.webservice.bean;

/**
 *
 * @author hunglevn@outlook.com
 *
 */
public class LoginWsBean extends BaseWsBean {
	public static final int LOGIN_SUCCESSED_CODE = 1;
	public static final int LOGIN_FAILED_CODE = -1;
	
	private String jSessionId;

	public String getjSessionId() {
		return jSessionId;
	}

	public void setjSessionId(String jSessionId) {
		this.jSessionId = jSessionId;
	}
}

package com.ds.web.webservice.bean;

/**
 *
 * @author hunglevn@outlook.com
 *
 */
public class LoginWsBean extends BaseWsBean {
	public interface LoginResult {
		public static final String LOGIN_SUCCESSED = "SUCCESSED";
		public static final String LOGIN_FAILED = "FAILED";
	}
	
	private String jSessionId;

	public String getjSessionId() {
		return jSessionId;
	}

	public void setjSessionId(String jSessionId) {
		this.jSessionId = jSessionId;
	}
}

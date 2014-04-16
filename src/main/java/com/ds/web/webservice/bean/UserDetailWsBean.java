package com.ds.web.webservice.bean;

/**
 * 
 * @author hunglevn@outlook.com
 * 
 */
public class UserDetailWsBean extends BaseWsBean {
	private String userEmail;
	private String password;

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

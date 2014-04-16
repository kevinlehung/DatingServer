package com.ds.web.config.security;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/**
 * Handler processing for Authentication Failure
 * 
 * @author hunglevn@outlook.com
 *
 */
public class DsAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	private static final String FAILURE_URL = "/sec/sign_in.ds?error=1";
	public DsAuthenticationFailureHandler() {
		/**
		 * for keeping ModelAttribute in target model;
		 */
		this.setUseForward(true);
		this.setDefaultFailureUrl(FAILURE_URL);
	}
}

package com.ds.web.util;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author hunglevn@outlook.com
 *
 */
public class RequestUtil {
	public static String getApplicationUrl(HttpServletRequest request) {
		return String.format("%s://%s:%d%s", 
				request.getScheme(),  
				request.getServerName(), 
				request.getServerPort(), 
				request.getContextPath());
	}
}

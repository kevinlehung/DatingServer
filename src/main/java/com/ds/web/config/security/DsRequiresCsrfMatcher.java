package com.ds.web.config.security;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 *
 * @author hunglevn@outlook.com
 *
 */
public class DsRequiresCsrfMatcher implements RequestMatcher {
	private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");

    /* (non-Javadoc)
     * @see org.springframework.security.web.util.matcher.RequestMatcher#matches(javax.servlet.http.HttpServletRequest)
     */
    public boolean matches(HttpServletRequest request) {
        boolean shouldCheckCsrf = !allowedMethods.matcher(request.getMethod()).matches();
        
        /**
         * Ignore Webservice call
         */
        if (shouldCheckCsrf) {
        	shouldCheckCsrf = request.getRequestURI().indexOf("/ws/") == -1;
        }
        
        return shouldCheckCsrf;
        
    }
}

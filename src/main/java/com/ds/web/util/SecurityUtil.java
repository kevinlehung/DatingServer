package com.ds.web.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.ds.persist.domain.User;
import com.ds.security.bean.DsUserDetails;

/**
 *
 * @author hunglevn@outlook.com
 *
 */
public class SecurityUtil {
	public static DsUserDetails getUserDetail() {
		DsUserDetails userDetails = (DsUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetails;
	}
	
	public static void authenticateUserAndSetSession(String userEmail, String password,
			HttpServletRequest request, AuthenticationManager authenticationManager) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				userEmail, password);

		// generate session if one doesn't exist
		request.getSession();

		token.setDetails(new WebAuthenticationDetails(request));
		Authentication authenticatedUser = authenticationManager
				.authenticate(token);

		SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
	}
	
	public static void authenticateUserAndSetSession(User user,
			HttpServletRequest request, AuthenticationManager authenticationManager) {
		authenticateUserAndSetSession(user.getUserEmail(), user.getPassword(), request, authenticationManager);
	}
}

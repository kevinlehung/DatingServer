package com.ds.web.webservice.util;

import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.codec.Base64;

/**
 * 
 * @author hunglevn@outlook.com
 * 
 */
public class WsTesterHelper {
	public static HttpHeaders buildBasicHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		return headers;
	}

	public static void addAuthHeaders(HttpHeaders headers, String userName,
			String password) {
		String auth = userName + ":" + password;
		byte[] encodedAuthorisation = Base64.encode(auth.getBytes());
		headers.add("Authorization", "Basic "
				+ new String(encodedAuthorisation));
	}
}

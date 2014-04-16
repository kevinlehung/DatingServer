package com.ds.web.webservice;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.ds.BaseTester;
import com.ds.web.webservice.bean.UserDetailWsBean;
import com.ds.web.webservice.util.WsTesterHelper;
/**
 *
 * @author hunglevn@outlook.com
 *
 */
public class SignUpWsTester extends BaseTester {
	@Test
	public void testSignUp() {
		HttpHeaders headers = WsTesterHelper.buildBasicHeaders();
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.set("email", "uther@ymail.com");
		params.set("password", "helloworld");
		params.set("acceptAgreement", "true");
		
		HttpEntity<MultiValueMap<String,String>> requestEntity = new HttpEntity<MultiValueMap<String,String>>(
				params, headers);
		RestTemplate template = new RestTemplate();

		HttpMessageConverter<?> formHttpMessageConverter = new FormHttpMessageConverter();
		HttpMessageConverter<?> stringHttpMessageConverter = new StringHttpMessageConverter();
		template.getMessageConverters().add(formHttpMessageConverter);
		template.getMessageConverters().add(stringHttpMessageConverter);
		
		UserDetailWsBean dsUserDetails = template.postForObject(
				"http://localhost:8080/ds/ws/sign_up.ds", requestEntity,
				UserDetailWsBean.class, new Object[]{});
		System.out.println(dsUserDetails.getStatusMessage() + ":" + dsUserDetails.getUserEmail());
	}

}
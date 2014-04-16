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
import com.ds.web.webservice.bean.LoginWsBean;
import com.ds.web.webservice.util.WsTesterHelper;

/**
 * 
 * @author hunglevn@outlook.com
 * 
 */
public class SignInWsTester extends BaseTester {
	@Test
	public void testSignUp() {
		HttpHeaders headers = WsTesterHelper.buildBasicHeaders();
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		/*params.set("userEmail", "uther@ymail.com");
		params.set("password", "helloworld");*/
		
		HttpEntity<MultiValueMap<String,String>> requestEntity = new HttpEntity<MultiValueMap<String,String>>(
				params, headers);
		RestTemplate template = new RestTemplate();

		HttpMessageConverter<?> formHttpMessageConverter = new FormHttpMessageConverter();
		HttpMessageConverter<?> stringHttpMessageConverter = new StringHttpMessageConverter();
		template.getMessageConverters().add(formHttpMessageConverter);
		template.getMessageConverters().add(stringHttpMessageConverter);
		
		LoginWsBean loginWsBean = template.postForObject(
				"http://localhost:8080/ds/ws/sign_in.ds", requestEntity,
				LoginWsBean.class, new Object[]{});
		System.out.println(loginWsBean.getjSessionId());
	}

}

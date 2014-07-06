package com.ds.web.webservice;

import java.io.File;

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
public class PhotoWsTester  extends BaseTester {
	@Test
	public void testRetrievePhoto() {
		HttpHeaders headers = WsTesterHelper.buildBasicHeaders();
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		
		HttpEntity<MultiValueMap<String,String>> requestEntity = new HttpEntity<MultiValueMap<String,String>>(
				params, headers);
		RestTemplate template = new RestTemplate();

		HttpMessageConverter<?> formHttpMessageConverter = new FormHttpMessageConverter();
		HttpMessageConverter<?> stringHttpMessageConverter = new StringHttpMessageConverter();
		template.getMessageConverters().add(formHttpMessageConverter);
		template.getMessageConverters().add(stringHttpMessageConverter);
		
		File resourceFile = template.postForObject(
				"http://localhost:8080/ds/ws/retrieve_photo.ds?resourceId=1", requestEntity,
				File.class, new Object[]{});
		System.out.println(resourceFile.length());
	}
}
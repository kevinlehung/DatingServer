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
import com.ds.web.webservice.bean.UserProfileWsBean;
import com.ds.web.webservice.util.WsTesterHelper;

/**
 *
 * @author hunglevn@outlook.com
 *
 */
public class SearchUserProfileWsTester  extends BaseTester {
	@Test
	public void testSearchUserProfileWs() {
		HttpHeaders headers = WsTesterHelper.buildBasicHeaders();
		WsTesterHelper.addAuthHeaders(headers, "uther@ymail.com", "helloworld");
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.set("gender", "FEMALE");
		params.set("purpose", "TO_TI_TE");
		
		HttpEntity<MultiValueMap<String,String>> requestEntity = new HttpEntity<MultiValueMap<String,String>>(
				params, headers);
		RestTemplate template = new RestTemplate();

		HttpMessageConverter<?> formHttpMessageConverter = new FormHttpMessageConverter();
		HttpMessageConverter<?> stringHttpMessageConverter = new StringHttpMessageConverter();
		template.getMessageConverters().add(formHttpMessageConverter);
		template.getMessageConverters().add(stringHttpMessageConverter);
		
		UserProfileWsBean userProfileWsBean = template.postForObject(
				"http://localhost:8080/ds/ws/search_user_profile.ds", requestEntity,
				UserProfileWsBean.class, new Object[]{});
		System.out.println(userProfileWsBean.getAboutMe());
	}

}

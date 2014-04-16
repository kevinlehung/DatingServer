package com.ds.web.webservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ds.service.IUserService;
import com.ds.web.webservice.bean.UserProfileWsBean;

/**
 *
 * @author hunglevn@outlook.com
 *
 */
@RestController
public class SearchUserProfileWs {
	@Autowired@Qualifier("dsAuthenticationManager")
    protected AuthenticationManager authenticationManager;
    
	@Autowired
	IUserService userService;
	
	@RequestMapping(value="/ws/search_user_profile", method=RequestMethod.POST, 
			produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
	public UserProfileWsBean doSearchUserProfile(HttpServletRequest request, HttpServletResponse response,
			@QueryParam("gender") String gender, @QueryParam("purpose") String purpose) {
		UserProfileWsBean userProfileWsBean = new UserProfileWsBean();
		userProfileWsBean.setAboutMe("I am single");
		userProfileWsBean.setFullName("Kevin");
		return userProfileWsBean;
	}
}

package com.ds.web.webservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ds.persist.domain.User;
import com.ds.service.IUserService;
import com.ds.web.form.UserSignUpForm;
import com.ds.web.util.SecurityUtil;
import com.ds.web.webservice.bean.UserDetailWsBean;

/**
 *
 * @author hunglevn@outlook.com
 *
 */
@RestController
public class SignUpWs {
    @Autowired@Qualifier("dsAuthenticationManager")
    protected AuthenticationManager authenticationManager;
    
	@Autowired
	IUserService userService;
	
	@RequestMapping(value="/ws/sign_up", method=RequestMethod.POST, 
			produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
	public UserDetailWsBean doSignup(HttpServletRequest request, HttpServletResponse response,
    		@Valid@RequestBody UserSignUpForm userSignUpForm, BindingResult result) {
		boolean hasError = result.hasErrors();
		UserDetailWsBean userDetailWsBean = new UserDetailWsBean();
		
		if (!hasError) {
			User user = userService.createUser(userSignUpForm);
			SecurityUtil.authenticateUserAndSetSession(user, request, authenticationManager);
			userDetailWsBean.setUserEmail(user.getUserEmail());
		} else {
			userDetailWsBean.setResultCode(UserDetailWsBean.DEFAULT_FAILED_CODE);
		}
		return userDetailWsBean;
	}
}

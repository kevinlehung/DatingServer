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
import com.ds.web.util.SecurityUtil;
import com.ds.web.webservice.bean.LoginWsBean;

/**
 *
 * @author hunglevn@outlook.com
 *
 */
@RestController
public class SignInWs {
    @Autowired@Qualifier("dsAuthenticationManager")
    protected AuthenticationManager authenticationManager;
    
	@Autowired
	IUserService userService;
	
	@RequestMapping(value="/ws/sign_in", method=RequestMethod.POST, 
			produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
	public LoginWsBean doSignin(HttpServletRequest request, HttpServletResponse response,
			@QueryParam("userEmail") String userEmail, @QueryParam("password") String password) {
		LoginWsBean loginWsBean = new LoginWsBean();
		try {
			SecurityUtil.authenticateUserAndSetSession(userEmail, password, request, authenticationManager);
			loginWsBean.setResult(LoginWsBean.LoginResult.LOGIN_SUCCESSED);
			loginWsBean.setjSessionId(request.getSession().getId());
		} catch (Exception e) {
			loginWsBean.setResult(LoginWsBean.LoginResult.LOGIN_FAILED);
		}
		return loginWsBean;
		
	}
}

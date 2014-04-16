package com.ds.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ds.BaseTester;

/**
 *
 * @author hunglevn@outlook.com
 *
 */
public class UserServiceTester extends BaseTester {
	@Autowired
	IUserService userService;
	
	@Test
	public void testCreateAccount () {
		userService.createUser("kevin@yahoo.com", "password");
	}
}

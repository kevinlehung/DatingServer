package com.ds.repo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ds.BaseTester;
import com.ds.persist.domain.User;
import com.ds.persist.repositories.UserRepo;

/**
 *
 * @author hunglevn@outlook.com
 *
 */
public class UserRepoTester extends BaseTester {

	@Autowired@Qualifier("userRepo")
	UserRepo userRepo;
	
	@Test
	public void test() {
		userRepo.save(new User());
	}
	
}
package com.ds.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.persist.domain.User;
import com.ds.persist.repositories.UserRepo;

/**
 * Contain operations relate to User
 * 
 * @author hunglevn@outlook.com
 *
 */
@Service
public class UserService extends BaseService implements IUserService {
	@Autowired
	private UserRepo userRepo;
	
	/**
	 * Create user with minimum fields and set default properties:
	 * 	+ accountLocked: false
	 *  + failedLoginAttempts: 0
	 *  + passwordExpired: false
	 *  + userActive: true
	 * @param email
	 * @param password
	 * @param purpose
	 * @return
	 */
	public User createUser(String email, String password) {
		User user = new User();
		user.setUserEmail(email);
		user.setPassword(password);
		user.setStatus(User.Status.ACTIVE);
		user.setDateCreated(new Date(System.currentTimeMillis()));
		return userRepo.save(user);
	}
	
	public boolean emailIsRegisteredBefore(String email) {
		User userByEmail = userRepo.findByUserEmail(email);
		return userByEmail != null;
	}
}

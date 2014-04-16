package com.ds.service;

import com.ds.persist.domain.User;

public interface IUserService extends IBaseService {
	/**
	 * Create user with minimum fields and set default properties:
	 * 	+ accountLocked: false
	 *  + failedLoginAttempts: 0
	 *  + passwordExpired: false
	 *  + userActive: true
	 * @param email
	 * @param password
	 * @return
	 */
	public User createUser(String email, String password);
	
	/**
	 * Check if email has been registered before.
	 * 
	 * @param email
	 * @return
	 */
	public boolean emailIsRegisteredBefore(String email);
}

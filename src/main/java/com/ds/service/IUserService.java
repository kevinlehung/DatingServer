package com.ds.service;

import java.util.List;
import java.util.Map;

import com.ds.persist.domain.User;
import com.ds.web.form.UserSignUpForm;

public interface IUserService extends IBaseService {
	public interface SEARCH_CRITERIA {
		public static final String GENDER = "GENDER";
		public static final String PURPOSE = "PURPOSE";
		public static final String FROM_ITEM = "FROM_ITEM";
		public static final String ITEM_COUNT = "ITEM_COUNT";
	}
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
	public User createUser(UserSignUpForm userSignUpForm);
	
	/**
	 * Check if email has been registered before.
	 * 
	 * @param email
	 * @return
	 */
	public boolean emailIsRegisteredBefore(String email);

	public List<Object[]> searchUser(Map<String, String> searchCriteria);
}

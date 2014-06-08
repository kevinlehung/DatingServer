package com.ds.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.persist.domain.Profile;
import com.ds.persist.domain.User;
import com.ds.persist.repositories.ProfileRepo;
import com.ds.persist.repositories.UserRepo;
import com.ds.web.form.UserSignUpForm;

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
	
	@Autowired
	private ProfileRepo profileRepo;
	
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
	public User createUser(UserSignUpForm userSignUpForm) {
		User user = new User(userSignUpForm.getEmail(), userSignUpForm.getEmail(), userSignUpForm.getPassword());
		user.setStatus(User.Status.ACTIVE);
		user.setDateCreated(new Date(System.currentTimeMillis()));
		userRepo.save(user);
		
		Profile profile = new Profile(user.getUserId(), 
				userSignUpForm.getFullName(), 
				userSignUpForm.getGender(), 
				userSignUpForm.getAboutMe());
		
		profileRepo.save(profile);
		
		return user;
	}
	
	public boolean emailIsRegisteredBefore(String email) {
		User userByEmail = userRepo.findByUserEmail(email);
		return userByEmail != null;
	}
}

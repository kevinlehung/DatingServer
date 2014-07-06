package com.ds.repo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ds.BaseTester;
import com.ds.persist.domain.Resource;
import com.ds.persist.domain.User;
import com.ds.persist.domain.UserPhoto;
import com.ds.persist.repositories.UserPhotoRepo;

/**
 *
 * @author hunglevn@outlook.com
 *
 */
public class UserPhotoRepoTester extends BaseTester {

	@Autowired@Qualifier("userPhotoRepo")
	UserPhotoRepo userPhotoRepo;
	
	@Test
	public void test() {
		/*UserPhoto userPhoto = new UserPhoto();
		userPhoto.setUser(new User(35));
		userPhoto.setResource(new Resource(1));
		userPhotoRepo.save(userPhoto);*/
		userPhotoRepo.addUserPhoto(new User(35), new Resource(1));
	}
	
}
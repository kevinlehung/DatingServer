package com.ds.persist.repositories;

import java.util.Date;

import com.ds.persist.BaseRepoImpl;
import com.ds.persist.domain.Resource;
import com.ds.persist.domain.User;
import com.ds.persist.domain.UserPhoto;

/**
 *
 * @author hunglevn@outlook.com
 *
 */
public class UserPhotoRepoImpl extends BaseRepoImpl implements UserPhotoCustomRepo {

	public UserPhoto addUserPhoto(User user, Resource resource) {
		UserPhoto userPhoto = new UserPhoto();
		userPhoto.setUser(user);
		userPhoto.setResource(resource);
		userPhoto.setCreatedDate(new Date());
		
		this.em.persist(userPhoto);
		
		return userPhoto;
	}

}

package com.ds.persist.repositories;

import com.ds.persist.domain.Resource;
import com.ds.persist.domain.User;
import com.ds.persist.domain.UserPhoto;

/**
 *
 * @author hunglevn@outlook.com
 *
 */
public interface UserPhotoCustomRepo {
	public UserPhoto addUserPhoto(User user, Resource resource);
}

package com.ds.persist.repositories;

import com.ds.persist.BaseRepo;
import com.ds.persist.domain.User;

/**
 * Do operations on [user] table
 * 
 * @author hunglevn@outlook.com
 *
 */
public interface UserRepo extends BaseRepo<User, Integer>, UserCustomRepo {
	public User findByUserEmail(String userEmail);
}

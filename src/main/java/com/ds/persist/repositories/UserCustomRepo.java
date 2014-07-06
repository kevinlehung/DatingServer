package com.ds.persist.repositories;

import java.util.List;

public interface UserCustomRepo {
	/**
	 * Find user by properties.
	 * 
	 * @param gender
	 * @param purpose
	 * @param fromItem
	 * @param itemCount
	 * @return list of elements. One element is an array contains these properties:
	 * 	Element[0]: user
	 * 	Element[1]: resource
	 * 	Element[2]: profile
	 */
	public List<Object[]> findUserProperties(String gender, String purpose,
			int fromItem, int itemCount);

}

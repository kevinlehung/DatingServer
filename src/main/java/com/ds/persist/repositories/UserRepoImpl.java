package com.ds.persist.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;

import com.ds.persist.BaseRepoImpl;

/**
 * 
 * @author hunglevn@outlook.com
 *
 */
public class UserRepoImpl extends BaseRepoImpl implements UserCustomRepo {
	public List<Object[]> findUserProperties(String gender, String purpose,
			int fromItem, int itemCount) {
		int defaultItemCount = 20;
		Object[] queryAndParamMap = buildFindUserPropertiesQueryAndParamMap(gender, purpose, fromItem, itemCount);
		if (fromItem >= 0) {
			if (itemCount < 0) {
				itemCount = defaultItemCount;
			}
			return this.findByQuery((String)queryAndParamMap[0], (Map)queryAndParamMap[1], fromItem, itemCount);
		}
		return this.findByQuery((String)queryAndParamMap[0], (Map)queryAndParamMap[1]);
	}

	private Object[] buildFindUserPropertiesQueryAndParamMap(String gender, String purpose,
			int fromItem, int itemCount) {
		String query = "SELECT u, r, p FROM User u LEFT JOIN u.userPhotos up LEFT JOIN up.resource r LEFT JOIN u.profile p "
				+ "WHERE (1 = 1) ";
		Map params = new HashMap();
		
		if (!StringUtils.isEmpty(gender)) {
			query = query + "AND (p.GENDER = :gender) ";
			params.put("gender", gender);
		}
		
		if (!StringUtils.isEmpty(purpose)) {
			query = query + "AND (PURPOSE = :purpose) ";
			params.put("p.purpose", purpose);
		}
		
		return new Object[] {query, params};
	}

}

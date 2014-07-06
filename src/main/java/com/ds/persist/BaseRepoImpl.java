package com.ds.persist;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author hunglevn@outlook.com
 *
 */
public class BaseRepoImpl<E, K extends Serializable> {
	@PersistenceContext
    protected EntityManager em;
	
	/**
	 * This method is used to execute Hibernate or JPA query like
	 * "FROM user u WHERE u.USER_NAME = :USER_NAME"
	 * 
	 * @param query
	 * @param params
	 * @return
	 */
	public List<E> findByQuery(final String query, final Map params) {
		Query queryObj = em.createQuery(query);
		if (params != null) {
			Iterator<String> paramNames = params.keySet().iterator();
			while (paramNames.hasNext()) {
				String paramName = paramNames.next();
				Object paramValue = params.get(paramName);
				queryObj.setParameter(paramName, paramValue);
			}
		}
		Object result = queryObj.getResultList();
		return (List<E>) result;
	}
	
	/**
	 * This method is used to execute Hibernate or JPA query like
	 * "FROM user u WHERE u.USER_NAME = :USER_NAME"
	 * 
	 * @param query
	 * @param params
	 * @return
	 */
	public List<E> findByQuery(final String query, final Map params, int startPosition, int maxResult) {
		Query queryObj = em.createQuery(query);
		if (params != null) {
			Iterator<String> paramNames = params.keySet().iterator();
			while (paramNames.hasNext()) {
				String paramName = paramNames.next();
				Object paramValue = params.get(paramName);
				queryObj.setParameter(paramName, paramValue);
			}
		}
		queryObj.setFirstResult(startPosition);
		queryObj.setMaxResults(maxResult);
		Object result = queryObj.getResultList();
		return (List<E>) result;
	}
}

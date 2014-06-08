package com.ds.persist;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hunglevn@outlook.com
 *
 */
public class BaseRepoImpl {
	@PersistenceContext
    protected EntityManager em;
}

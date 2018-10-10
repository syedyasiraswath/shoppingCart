/**
 * 
 */
package com.alt.shop.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.exception.ConstraintViolationException;

import com.alt.shop.entity.UserInfo;
import com.alt.shop.exception.UserInfoException;

/**
 * @author Syedyasiraswath
 *
 */
@Transactional
public class UserInfoRepositoryImpl implements UserInfoRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.alt.shop.repository.UserInfoRepositoryCustom#addUser(com.alt.shop.entity.
	 * UserInfo)
	 */
	@Override
	public UserInfo addUser(UserInfo userInfo) {
		// TODO Auto-generated method stub
		em.persist(userInfo);
		em.flush();
		em.close();
		return userInfo;
	}

}

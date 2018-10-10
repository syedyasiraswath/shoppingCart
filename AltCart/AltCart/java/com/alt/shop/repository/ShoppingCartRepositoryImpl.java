/**
 * 
 */
package com.alt.shop.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.alt.shop.entity.ShoppingCart;

/**
 * @author Syedyasiraswath
 *
 */
@Transactional
public class ShoppingCartRepositoryImpl implements ShoppingCartRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	
	/* (non-Javadoc)
	 * @see com.alt.shop.repository.ShoppingCartRepositoryCustom#addToCart(com.alt.shop.entity.ShoppingCart)
	 */
	@Override
	public ShoppingCart addToCart(ShoppingCart shoppingCart) {
		// TODO Auto-generated method stub
		em.merge(shoppingCart);
		em.flush();
		em.close();
		return shoppingCart;
	}

}

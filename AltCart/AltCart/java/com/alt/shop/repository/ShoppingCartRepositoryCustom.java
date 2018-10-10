package com.alt.shop.repository;

import com.alt.shop.entity.ShoppingCart;

/**
 * @author Syedyasiraswath
 *
 */
public interface ShoppingCartRepositoryCustom {

	/**
	 * @param shoppingCart
	 * @return
	 */
	public ShoppingCart addToCart(ShoppingCart shoppingCart);
}

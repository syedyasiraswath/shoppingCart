/**
 * 
 */
package com.alt.shop.service;

import java.util.List;

import com.alt.shop.entity.ProductInfo;
import com.alt.shop.entity.ShoppingCart;

/**
 * @author Syedyasiraswath Askar Basha
 *
 */
public interface ShoppingCartService {

	/**
	 * @param shoppingCart
	 * @return
	 */
	public String addToCart(ShoppingCart shoppingCart);
	
	public List<ProductInfo> fetchShopCartDet(ShoppingCart shoppingCart);

}

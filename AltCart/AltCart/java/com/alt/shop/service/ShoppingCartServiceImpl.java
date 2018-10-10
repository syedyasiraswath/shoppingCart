/**
 * 
 */
package com.alt.shop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.alt.shop.entity.ProductInfo;
import com.alt.shop.entity.ShoppingCart;
import com.alt.shop.exception.UserInfoException;
import com.alt.shop.repository.ProductInfoRepository;
import com.alt.shop.repository.ShoppingCartRepository;

/**
 * @author Syedyasiraswath
 *
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Autowired
	private Environment environment;

	@Autowired
	private ShoppingCartRepository shoppingCartRepository;

	@Autowired
	private ProductInfoRepository productInfoRepository;

	@Override
	public String addToCart(ShoppingCart shoppingCart) {
		// TODO Auto-generated method stub
		Optional<ProductInfo> productInfo = null;
		try {
			productInfo = productInfoRepository.findById(shoppingCart.getProductInfoId());
			if(!productInfo.isPresent()) {
				return environment.getProperty("ShoppingCartServiceImpl.PRODUCT_NOT_FOUND");
			}
			ShoppingCart shoppingCartVerify = shoppingCartRepository
					.findByUserIdAndProductInfoId(shoppingCart.getUserId(), shoppingCart.getProductInfoId());
			if (shoppingCartVerify != null) {
				if (shoppingCartVerify.getCartId() != null) {
					if (productInfo != null) {
						if (productInfo.get().getProductAvailQty() > shoppingCartVerify.getQuantity()) {
							shoppingCart.setCartId(shoppingCartVerify.getCartId());
							shoppingCart.setQuantity(shoppingCart.getQuantity() + shoppingCartVerify.getQuantity());
						} else if (productInfo.get().getProductAvailQty() == shoppingCartVerify.getQuantity()) {
							shoppingCart.setCartId(shoppingCartVerify.getCartId());
							shoppingCart.setQuantity(shoppingCart.getQuantity() + shoppingCartVerify.getQuantity());
						} else if (productInfo.get().getProductAvailQty() < shoppingCart.getQuantity()) {
							return environment.getProperty("ShoppingCartServiceImpl.ALREADY_ADDED");
						}
					}
				}
			}

			shoppingCart = shoppingCartRepository.addToCart(shoppingCart);
			if (shoppingCart != null) {
				if (productInfo != null) {
					if (productInfo.get().getProductAvailQty() > shoppingCart.getQuantity()) {
						productInfo.get().setProductAvailQty(productInfo.get().getProductAvailQty() - shoppingCart.getQuantity());
						productInfoRepository.updateproductInfo(productInfo.get());
					} else if (productInfo.get().getProductAvailQty() < shoppingCart.getQuantity()
							|| productInfo.get().getProductAvailQty() == shoppingCart.getQuantity()) {
						productInfo.get().setProductAvailQty(0);
						productInfoRepository.updateproductInfo(productInfo.get());
					}
				}
			}

		} catch (DataIntegrityViolationException e) {
			return environment.getProperty("ShoppingCartServiceImpl.CART_DUP");
		} catch (UserInfoException e) {
			return environment.getProperty("ShoppingCartServiceImpl.CART_FAILURE") + ":" + e.getMessage();
		}
		return environment.getProperty("ShoppingCartServiceImpl.CART_SUCCESS");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.alt.shop.service.ShoppingCartService#fetchShopCartDet(com.alt.shop.entity
	 * .ShoppingCart)
	 */
	@Override
	public List<ProductInfo> fetchShopCartDet(ShoppingCart shoppingCart) {
		// TODO Auto-generated method stub
		List<ProductInfo> productInfos = new ArrayList<>();
		List<ShoppingCart> shopCartList = shoppingCartRepository.findByUserId(shoppingCart.getUserId());
		if (shopCartList.isEmpty()) {
			return productInfos;
		} else {
			for (ShoppingCart shoppingCart2 : shopCartList) {
				Optional<ProductInfo> productInfo = productInfoRepository.findById(shoppingCart2.getProductInfoId());
				if (productInfo.isPresent()) {
					if(productInfo.get().getProductAvailQty() != null) {
						productInfo.get().setProductAvailQty(shoppingCart2.getQuantity());
						productInfos.add(productInfo.get());
					}
				}
			}
		}
		return productInfos;
	}

}

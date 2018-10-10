/**
 * 
 */
package com.alt.shop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alt.shop.entity.ProductInfo;
import com.alt.shop.entity.ShoppingCart;
import com.alt.shop.entity.UserInfo;
import com.alt.shop.exception.UserInfoException;
import com.alt.shop.repository.UserInfoRepository;
import com.alt.shop.service.ProductInfoService;
import com.alt.shop.service.ShoppingCartService;

/**
 * @author Syedyasiraswath Askar Basha
 *
 */
@RestController
@RequestMapping(value = "/shoppingCart")
public class ShoppingCartController {

	@Autowired
	private Environment environment;

	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Autowired
	private ProductInfoService productInfoService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addToCart(ModelAndView modelAndView,
			ShoppingCart shoppingCart, HttpServletRequest request) throws UserInfoException {
		Optional<UserInfo> userInfo = userInfoRepository.findById(shoppingCart.getUserId());
		modelAndView.addObject("userName", userInfo.get().getUserName());
		modelAndView.addObject("userId", userInfo.get().getUserId());
		modelAndView.addObject("email", userInfo.get().getEmail());
		String message = shoppingCartService.addToCart(shoppingCart);
		if (message.equalsIgnoreCase(environment.getProperty("ShoppingCartServiceImpl.ALREADY_ADDED")) || 
				message.equalsIgnoreCase(environment.getProperty("ShoppingCartServiceImpl.CART_DUP")) ||
				message.contains(environment.getProperty("ShoppingCartServiceImpl.CART_FAILURE")) ||
				message.contains(environment.getProperty("ShoppingCartServiceImpl.PRODUCT_NOT_FOUND"))) {
			modelAndView.addObject("cartErrorMessage",message);
			List<ProductInfo> infoList = productInfoService.getProducts();
			if(infoList.isEmpty()) {
				modelAndView.addObject("errorMessage", environment.getProperty("UserInfoController.CATELOGUE_EMPTY"));
			}else {
				modelAndView.addObject("productList", infoList);
				modelAndView.addObject("productInfoList", infoList);
			}
			modelAndView.setViewName("dashboard");
			return modelAndView;
		}
		modelAndView.addObject("confirmationMessage", shoppingCart.getProductInfoId() + " has been added into your cart!");
		
		List<ProductInfo> infoList = shoppingCartService.fetchShopCartDet(shoppingCart);
		modelAndView.addObject("productList", infoList);
		modelAndView.setViewName("shoppingCart");
		return modelAndView;
	}
}

/**
 * 
 */
package com.alt.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alt.shop.entity.ProductInfo;
import com.alt.shop.entity.ShoppingCart;
import com.alt.shop.entity.UserInfo;
import com.alt.shop.exception.UserInfoException;
import com.alt.shop.service.MailService;
import com.alt.shop.service.ProductInfoService;
import com.alt.shop.service.UserInfoService;

/**
 * @author Syedyasiraswath
 *
 */
@RestController
@RequestMapping(value = "/userInfo")
public class UserInfoController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private ProductInfoService productInfoService;
	
	/**
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/welcome", method = RequestMethod.GET)
	public ModelAndView getWelcomePage(ModelAndView modelAndView) {
		modelAndView.setViewName("/welcome");
		return modelAndView;
	}
	
	/**
	 * @param modelAndView
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public ModelAndView showRegistrationPage(ModelAndView modelAndView, UserInfo user){
		modelAndView.addObject("userInfo", user);
		modelAndView.setViewName("register");
		return modelAndView;
	}
	
	/**
	 * @param modelAndView
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView showLoginnPage(ModelAndView modelAndView, UserInfo user){
		modelAndView.addObject("userInfo", user);
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	/**
	 * @param modelAndView
	 * @param userInfo
	 * @param bindingResult
	 * @param request
	 * @return
	 * @throws UserInfoException
	 */
	@RequestMapping(value="/validate/login", method = RequestMethod.GET)
	public ModelAndView validateUser(ModelAndView modelAndView,@ModelAttribute ProductInfo product, UserInfo userInfo, HttpServletRequest request) throws UserInfoException{
		String message = userInfoService.validateUser(userInfo);
		if(message.contains("Success")) {
			List<ProductInfo> infoList = productInfoService.getProducts();
			if(infoList.isEmpty()) {
				modelAndView.addObject("errorMessage", env.getProperty("UserInfoController.CATELOGUE_EMPTY"));
			}else {
				modelAndView.addObject("productList", infoList);
				modelAndView.addObject("productInfoList", infoList);
			}
			userInfo = userInfoService.fetchUser(userInfo);
			modelAndView.addObject("confirmationMessage", env.getProperty("UserInfoController.CATELOGUE_SUCCESS"));
			modelAndView.addObject("userName", userInfo.getUserName());
			modelAndView.addObject("userId", userInfo.getUserId());
			modelAndView.addObject("email", userInfo.getEmail());
			modelAndView.addObject("shoppingCart", new ShoppingCart());
			modelAndView.setViewName("dashboard");
		}else {
			modelAndView.addObject("errorMessage", message);
			modelAndView.setViewName("login");
		}
		return modelAndView;
	}
	
	/**
	 * @param modelAndView
	 * @param userInfo
	 * @param bindingResult
	 * @param request
	 * @return
	 * @throws UserInfoException
	 */
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public ModelAndView addUser(ModelAndView modelAndView,@Valid UserInfo userInfo, BindingResult bindingResult, HttpServletRequest request) throws UserInfoException {
		if(bindingResult.hasErrors()) {
			modelAndView.setViewName("register");
			return modelAndView;
		}
		String message = userInfoService.addUser(userInfo);
		if(message.contains("Success")) {
			modelAndView.addObject("confirmationMessage", message);
			
			try{
	              mailService.sendASynchronousMail(userInfo.getEmail(), "AltCart Registration!", "Successfully registered Mr."+userInfo.getUserName());			
			}catch (MailException e) {
				System.out.println(e.getMessage());
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}else {
			modelAndView.addObject("errorMessage", message);
		}
		modelAndView.setViewName("register");
		return modelAndView;
	}

}

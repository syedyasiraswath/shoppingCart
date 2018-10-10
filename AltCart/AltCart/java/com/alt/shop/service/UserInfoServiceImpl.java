/**
 * 
 */
package com.alt.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.alt.shop.entity.UserInfo;
import com.alt.shop.exception.UserInfoException;
import com.alt.shop.repository.UserInfoRepository;

/**
 * @author Syedyasiraswath
 *
 */
@Service
public class UserInfoServiceImpl  implements UserInfoService{
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private UserInfoRepository userInfoRepository;

	@Autowired
	private UserInfoService userInfoService;
	
	/* (non-Javadoc)
	 * @see com.alt.shop.service.UserInfoService#fetchUser(com.alt.shop.entity.UserInfo)
	 */
	@Override
	public UserInfo fetchUser(UserInfo userInfo) {
		// TODO Auto-generated method stub
		String pwd = userInfo.getPassword();
		String email = userInfo.getEmail();
		userInfo = userInfoRepository.findByEmail(email);
		if(userInfo != null) {
				return userInfo;
		}
		return null;
	}

	
	/* (non-Javadoc)
	 * @see com.alt.shop.service.UserInfoService#addUser(com.alt.shop.entity.UserInfo)
	 */
	@Override
	public String addUser(UserInfo userInfo) {
		try {
			userInfo = userInfoRepository.addUser(userInfo);
		}catch(DataIntegrityViolationException e) {
			return environment.getProperty("UserInfoServiceImpl.REGISTRATION_FAILURE")+" : "+userInfo.getEmail()+" already present in the database!";
		}catch(UserInfoException e) {
			return environment.getProperty("UserInfoServiceImpl.REGISTRATION_FAILURE")+":"+e.getMessage();
		}
		return environment.getProperty("UserInfoServiceImpl.REGISTRATION_SUCCESS");
	}


	/* (non-Javadoc)
	 * @see com.alt.shop.service.UserInfoService#validateUser(com.alt.shop.entity.UserInfo)
	 */
	@Override
	public String validateUser(UserInfo userInfo) {
		// TODO Auto-generated method stub
		userInfo = userInfoService.fetchUser(userInfo);
		if(userInfo != null) {
			if(userInfo.getPassword().equalsIgnoreCase(userInfo.getPassword())) {
				return environment.getProperty("UserInfoServiceImpl.LOGIN_SUCCESS");
			}
		}
		return environment.getProperty("UserInfoServiceImpl.LOGIN_FAILURE");
	}
}

/**
 * 
 */
package com.alt.shop.service;

import com.alt.shop.entity.UserInfo;

/**
 * @author Syedyasiraswath
 *
 */
public interface UserInfoService {

	/**
	 * @param userInfo
	 * @return
	 */
	public String addUser(UserInfo userInfo);
	
	/**
	 * @param userInfo
	 * @return
	 */
	public String validateUser(UserInfo userInfo);

	/**
	 * @param userInfo
	 * @return
	 */
	public UserInfo fetchUser(UserInfo userInfo);
}

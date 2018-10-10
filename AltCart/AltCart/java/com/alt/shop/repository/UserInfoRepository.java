
package com.alt.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alt.shop.entity.UserInfo;

/**
 * @author Syedyasiraswath
 *
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer>, UserInfoRepositoryCustom {

	/**
	 * @param email
	 * @return
	 */
	public UserInfo findByEmail(String email);
}

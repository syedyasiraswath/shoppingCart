
package com.alt.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alt.shop.entity.ShoppingCart;

/**
 * @author Syedyasiraswath
 *
 */
@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer>, ShoppingCartRepositoryCustom {

	/**
	 * @param userId
	 * @param productInfoId
	 * @return
	 */
	public ShoppingCart findByUserIdAndProductInfoId(Integer userId, Integer productInfoId);
	
	/**
	 * @param userId
	 * @return
	 */
	public List<ShoppingCart> findByUserId(Integer userId);
}


package com.alt.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alt.shop.entity.ProductInfo;

/**
 * @author Syedyasiraswath
 *
 */
@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfo, Integer>, ProductInfoRepositoryCustom {

}

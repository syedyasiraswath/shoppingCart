/**
 * 
 */
package com.alt.shop.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.alt.shop.entity.ProductInfo;

/**
 * @author Syedyasiraswath
 *
 */
@Transactional
public class ProductInfoRepositoryImpl implements ProductInfoRepositoryCustom {
	
	@PersistenceContext
	private EntityManager em;

	/* (non-Javadoc)
	 * @see com.alt.shop.repository.ProductInfoRepositoryCustom#updateproductInfo(com.alt.shop.entity.ProductInfo)
	 */
	@Override
	public ProductInfo updateproductInfo(ProductInfo productInfo) {
		// TODO Auto-generated method stub
		em.merge(productInfo);
		em.flush();
		em.close();
		return productInfo;
	}

}

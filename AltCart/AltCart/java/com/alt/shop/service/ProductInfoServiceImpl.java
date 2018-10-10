/**
 * 
 */
package com.alt.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alt.shop.entity.ProductInfo;
import com.alt.shop.repository.ProductInfoRepository;

/**
 * @author Syedyasiraswath
 *
 */
@Service
public class ProductInfoServiceImpl  implements ProductInfoService{
	
	@Autowired
	private ProductInfoRepository productInfoRepository;

	@Override
	public List<ProductInfo> getProducts() {
		// TODO Auto-generated method stub
		return productInfoRepository.findAll();
	}


}

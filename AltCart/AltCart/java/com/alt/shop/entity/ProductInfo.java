/**
 * 
 */
package com.alt.shop.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Syedyasiraswath
 *
 */
@Entity
@Table(name = "PRODUCTINFO")
public class ProductInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4874685489793596397L;

	@Id
	@Column(name = "PRODUCTINFOID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer productInfoId;
	
	@Column(name = "PRODUCTNAME")
	public String productName;
	
	@Column(name = "PRODUCTRATE")
	public Integer productRate;
	
	@Column(name = "PRODUCTAVAILQTY")
	public Integer productAvailQty;

	/**
	 * @return the productInfoId
	 */
	public Integer getProductInfoId() {
		return productInfoId;
	}

	/**
	 * @param productInfoId the productInfoId to set
	 */
	public void setProductInfoId(Integer productInfoId) {
		this.productInfoId = productInfoId;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the productRate
	 */
	public Integer getProductRate() {
		return productRate;
	}

	/**
	 * @param productRate the productRate to set
	 */
	public void setProductRate(Integer productRate) {
		this.productRate = productRate;
	}

	/**
	 * @return the productAvailQty
	 */
	public Integer getProductAvailQty() {
		return productAvailQty;
	}

	/**
	 * @param productAvailQty the productAvailQty to set
	 */
	public void setProductAvailQty(Integer productAvailQty) {
		this.productAvailQty = productAvailQty;
	}

	
}

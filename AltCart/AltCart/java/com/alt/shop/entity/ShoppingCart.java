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
@Table(name = "SHOPPINGCART")
public class ShoppingCart implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4874685489793596397L;

	@Id
	@Column(name = "CARTID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer cartId;
	
	@Column(name = "USERID")
	public Integer userId;
	
	@Column(name = "PRODUCTINFOID")
	public Integer productInfoId;
	
	@Column(name = "QUANTITY")
	public Integer quantity;

	/**
	 * @return the cartId
	 */
	public Integer getCartId() {
		return cartId;
	}

	/**
	 * @param cartId the cartId to set
	 */
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

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
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	
	
}

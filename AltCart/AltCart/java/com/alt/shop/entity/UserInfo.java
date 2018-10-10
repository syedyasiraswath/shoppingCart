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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 * @author Syedyasiraswath
 *
 */
@Entity
@Table(name = "USERINFO")
public class UserInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4204352863286757968L;

	@Id
	@Column(name = "USERID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer userId;
	
	@Column(name = "USERNAME")
	@NotNull
	@Size(min = 3, max = 45, message = "Please provide a valid User Name!")
	public String userName;
	
	@Column(name = "AGE")
	@NotNull
	@Positive(message = "Please provide a valid Age!")
	public Integer age;
	
	@Column(name = "GENDER")
	@NotNull(message = "Please provide a valid Gender!")
	public String gender;
	
	@Column(name = "EMAIL")
	@NotNull(message = "Please provide a valid Email!")
	@Email
	public String email;
	
	@Column(name = "ADDRESS")
	@NotNull(message = "Please provide a valid Address!")
	public String address;
	
	@Column(name = "PASSWORD")
	@NotNull
	@Size(min = 6, max = 6, message = "Password should be 6 characters")
	public String password;

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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}

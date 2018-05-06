package com.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ShoppingCart entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "shopping_cart", catalog = "cloud_shopping")
public class ShoppingCart implements java.io.Serializable {
	// Fields

	private Integer userId;
	private Integer productId;
	private Integer productNum;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getProductNum() {
		return productNum;
	}
	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}
	public ShoppingCart(Integer userId, Integer productId, Integer productNum) {
		this.userId = userId;
		this.productId = productId;
		this.productNum = productNum;
	}
	public ShoppingCart() {
	}
	@Override
	public String toString() {
		return "ShoppingCart [userId=" + userId + ", productId=" + productId
				+ ", productNum=" + productNum + "]";
	}

}
package com.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * EasybuyProductCategory entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "easybuy_product_category", catalog = "easybuy")
public class ProductCategory implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Integer parentId;
	private Integer type;
	private String iconClass;

	// Constructors

	/** default constructor */
	public ProductCategory() {
	}

	/** minimal constructor */
	public ProductCategory(String name, Integer parentId) {
		this.name = name;
		this.parentId = parentId;
	}

	/** full constructor */
	public ProductCategory(String name, Integer parentId, Integer type,
			String iconClass) {
		this.name = name;
		this.parentId = parentId;
		this.type = type;
		this.iconClass = iconClass;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "parentId", nullable = false)
	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "iconClass")
	public String getIconClass() {
		return this.iconClass;
	}

	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}

	@Override
	public String toString() {
		return "ProductCategory [id=" + id + ", name=" + name + ", parentId="
				+ parentId + ", type=" + type + ", iconClass=" + iconClass
				+ "]";
	}
	
}
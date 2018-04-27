package com.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user", catalog = "cloud_shopping")
public class User implements java.io.Serializable {

	// Fields

	private Integer uid;
	private String uname;
	private String upwd;
	private String phone;
	private String email;
	private Integer type;
	// Constructors

	

	public User() {
	}

	/** full constructor */
	public User(String uname, String upwd, String phone, String email,Integer type) {
		this.uname = uname;
		this.upwd = upwd;
		this.phone = phone;
		this.email = email;
		this.type=type;
	}



	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "uid", unique = true, nullable = false)
	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	@Column(name = "uname", length = 11)
	public String getUname() {
		return this.uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	@Column(name = "upwd", nullable = false, length = 30)
	public String getUpwd() {
		return this.upwd;
	}

	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}

	@Column(name = "phone", length = 20)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "email", length = 30)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", upwd=" + upwd
				+ ", phone=" + phone + ", email=" + email + ", type=" + type +"]";
	}

}
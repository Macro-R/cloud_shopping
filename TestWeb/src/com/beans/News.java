package com.beans;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * News entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "news", catalog = "cloud_shopping", uniqueConstraints = @UniqueConstraint(columnNames = "title"))
public class News implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String content;
	private Date createTime;
	private String img;
	private String module;

	// Constructors

	public String getModlue() {
		return module;
	}

	public void setModlue(String module) {
		this.module = module;
	}



	public News(Integer id, String title, String content, Date createTime,
			String img, String module) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.createTime = createTime;
		this.img = img;
		this.module = module;
	}
	

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
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

	@Column(name = "title", unique = true, nullable = false, length = 40)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content", nullable = false, length = 1000)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "createTime", nullable = false, length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "img")
	public String getImg() {
		return this.img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public News() {
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", content=" + content
				+ ", createTime=" + createTime + ", img=" + img + ", module="
				+ module + "]";
	}

	

}
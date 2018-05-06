package com.utils;

import java.io.Serializable;
import java.util.List;

public class PageInfo<T> implements Serializable {
	//当前页
	private int pageNum=1;
	//每页数量
	private int pageSize=5;
	//总记录数
	private int total;
	//总页数
	private int pages;
	//结果集
	private List<T> list ;
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		if(total>0){
			this.total = total;
			this.pages=(total%pageSize==0)?(total/pageSize):(total/pageSize+1);//获取总记录数时把总页数也获取
		}
		
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public PageInfo(int pageNum, int pageSize, int total, int pages,
			List<T> list) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.total = total;
		this.pages = pages;
		this.list = list;
	}
	public PageInfo() {
		super();
	}
	@Override
	public String toString() {
		return "PageInfo [pageNum=" + pageNum + ", pageSize=" + pageSize
				+ ", total=" + total + ", pages=" + pages + ", list=" + list
				+ "]";
	}
	
}

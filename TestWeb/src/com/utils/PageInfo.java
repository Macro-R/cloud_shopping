package com.utils;

import java.io.Serializable;
import java.util.List;

public class PageInfo<T> implements Serializable {
	private int pageNum=1;
	private int pageSize=3;
	private int total;
	private int pages;
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
			this.pages=(total%pageSize==0)?(total/pageSize):(total/pageSize+1);
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

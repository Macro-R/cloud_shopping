package com.services;

import java.io.Serializable;
import java.util.List;

import com.beans.News;
import com.utils.PageInfo;

public interface NewsService {
	public int add(News t);
	public int delete(Serializable t);
	public int updata(News t);
	public List<News> select();
	public News findById(Serializable t);
	public int deletepic(Serializable t) ;
	public String filePath(Serializable t);
	PageInfo<News> findAlls(int pageNum, int pageSize);
	int getTotalCount();
	boolean delAll(String...str);
}

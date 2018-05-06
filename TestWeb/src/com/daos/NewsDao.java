package com.daos;

import java.io.Serializable;
import java.util.List;

import com.beans.News;

public interface NewsDao extends BaseDao<News>{
	int deletepic(Serializable t);
	String filePath(Serializable t);
	List<News> findAlls(int pageNum, int pageSize);
	public int getTotalCount();
	boolean delAll(String...str);
}

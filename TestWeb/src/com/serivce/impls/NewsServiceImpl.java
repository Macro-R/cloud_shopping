package com.serivce.impls;

import java.io.Serializable;
import java.util.List;

import com.beans.News;
import com.beans.User;
import com.dao.impls.NewsDaoImpl;
import com.daos.NewsDao;
import com.services.NewsService;
import com.utils.MemcachedUtil;
import com.utils.PageInfo;

public class NewsServiceImpl implements NewsService {
	NewsDao newsDao=new NewsDaoImpl();
	@Override
	public int add(News t) {
		return newsDao.add(t);
	}

	@Override
	public int delete(Serializable t) {
		return newsDao.delete(t);
	}

	@Override
	public int updata(News t) {
		return newsDao.updata(t);
	}

	@Override
	public List<News> select() {
		return newsDao.select();
	}

	@Override
	public News findById(Serializable t) {
			return newsDao.findById(t);
	}

	@Override
	public int deletepic(Serializable t) {
		return newsDao.deletepic(t);
	}

	@Override
	public String filePath(Serializable t) {
		return newsDao.filePath(t);
	}

	@Override
	public PageInfo<News> findAlls(int pageNum, int pageSize) {
		PageInfo<News> pageInfo = new PageInfo<>();
		// 给pageInfo对象的list集合赋值
		pageInfo.setList(newsDao.findAlls(pageNum, pageSize));
		return pageInfo;
	}

	@Override
	public int getTotalCount() {
		return newsDao.getTotalCount();
	}

	@Override
	public boolean delAll(String...str) {
		return newsDao.delAll(str);
	}
	
}

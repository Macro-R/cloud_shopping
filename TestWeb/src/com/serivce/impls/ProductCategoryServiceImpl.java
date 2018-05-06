package com.serivce.impls;

import java.io.Serializable;
import java.util.List;

import com.beans.ProductCategory;
import com.daos.ProductCategoryDao;
import com.dao.impls.ProductCategoryDaoImpl;
import com.services.ProductCategoryService;

public class ProductCategoryServiceImpl implements ProductCategoryService{
	ProductCategoryDao pc=new ProductCategoryDaoImpl();
	@Override
	public int add(ProductCategory t) {
		// TODO Auto-generated method stub
		return pc.add(t);
	}

	@Override
	public int delete(Serializable t) {
		// TODO Auto-generated method stub
		return pc.delete(t);
	}

	@Override
	public int updata(ProductCategory t) {
		// TODO Auto-generated method stub
		return pc.updata(t);
	}

	@Override
	public List<ProductCategory> select() {
		// TODO Auto-generated method stub
		return pc.select();
	}

}

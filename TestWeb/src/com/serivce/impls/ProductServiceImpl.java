package com.serivce.impls;

import java.io.Serializable;
import java.util.List;

import com.beans.Product;
import com.daos.ProductDao;
import com.dao.impls.ProductDaoImpl;
import com.services.ProductService;

public class ProductServiceImpl implements ProductService{
	ProductDao pd=new ProductDaoImpl();
	@Override
	public int add(Product t) {
		// TODO Auto-generated method stub
		return pd.add(t);
	}

	@Override
	public int delete(Serializable t) {
		// TODO Auto-generated method stub
		return pd.delete(t);
	}

	@Override
	public int updata(Product t) {
		// TODO Auto-generated method stub
		return pd.updata(t);
	}

	@Override
	public List<Product> select() {
		// TODO Auto-generated method stub
		return pd.select();
	}

	@Override
	public Product findById(Serializable t) {
		return pd.findById(t);
	}

	@Override
	public List<Product> finAllId(String... id) {
		
		return pd.finAllId(id);
	}

}

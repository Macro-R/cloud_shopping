package com.serivce.impls;

import java.io.Serializable;
import java.util.List;

import com.beans.Product;
import com.beans.ShoppingCart;
import com.dao.impls.ShoppingCartDaoImpl;
import com.daos.ShoppingCartDao;
import com.services.ShoppingCartService;

public class ShoppingCartSerivceImpl implements ShoppingCartService{
ShoppingCartDao shoppingCartDao=new ShoppingCartDaoImpl();
	@Override
	public int add(ShoppingCart t) {
		return shoppingCartDao.add(t);
	}

	@Override
	public int delete(Serializable t) {
		return shoppingCartDao.delete(t);
	}

	@Override
	public int updata(ShoppingCart t) {
		return shoppingCartDao.updata(t);
	}

	@Override
	public List<ShoppingCart> select() {
		return shoppingCartDao.select();
	}

	@Override
	public ShoppingCart findById(Serializable t) {
		return shoppingCartDao.findById(t);
	}

	@Override
	public List<ShoppingCart> select(Serializable t) {
		return shoppingCartDao.select(t);
	}

	

}

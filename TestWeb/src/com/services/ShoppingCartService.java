package com.services;

import java.io.Serializable;
import java.util.List;

import com.beans.Product;
import com.beans.ShoppingCart;

public interface ShoppingCartService {
	 int add(ShoppingCart t);
	 int delete(Serializable t);
	 int updata(ShoppingCart t);
	 List<ShoppingCart> select();
	 ShoppingCart findById(Serializable t);
	 List<ShoppingCart> select(Serializable t);
	
}

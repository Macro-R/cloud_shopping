package com.daos;

import java.io.Serializable;
import java.util.List;

import com.beans.ShoppingCart;



public interface ShoppingCartDao extends BaseDao<ShoppingCart>{
	List<ShoppingCart> select(Serializable t);
}

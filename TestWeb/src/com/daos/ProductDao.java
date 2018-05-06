package com.daos;

import java.util.List;

import com.beans.Product;

public interface ProductDao extends BaseDao<Product> {
	List<Product> finAllId(String...id);
}

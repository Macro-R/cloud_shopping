package com.services;

import java.io.Serializable;
import java.util.List;
import com.beans.Product;

public interface ProductService {
	public int add(Product t);
	public int delete(Serializable t);
	public int updata(Product t);
	public List<Product> select();
	Product findById(Serializable t);
	 List<Product> finAllId(String...id);
}

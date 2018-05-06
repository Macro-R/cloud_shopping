package com.services;

import java.io.Serializable;
import java.util.List;
import com.beans.ProductCategory;

public interface ProductCategoryService {
	public int add(ProductCategory t);
	public int delete(Serializable t);
	public int updata(ProductCategory t);
	public List<ProductCategory> select();
}

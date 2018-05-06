package com.services;

import java.io.Serializable;
import java.util.List;

import com.beans.Order;

public interface OrderService {
	public int add(Order t);
	public int delete(Serializable t);
	public int updata(Order t);
	public List<Order> select();
}

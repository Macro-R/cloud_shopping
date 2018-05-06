package com.services;

import java.io.Serializable;
import java.util.List;

import com.beans.OrderDetail;

public interface OrderDetailService {
	public int add(OrderDetail t );
	public int delete(Serializable t);
	public int updata(OrderDetail t);
	public List<OrderDetail> select();
}

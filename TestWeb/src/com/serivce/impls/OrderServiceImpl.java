package com.serivce.impls;

import java.io.Serializable;
import java.util.List;

import com.beans.Order;
import com.daos.OrderDao;
import com.dao.impls.OrderDaoImpl;
import com.services.OrderService;

public class OrderServiceImpl implements OrderService{
OrderDao orderDao=new OrderDaoImpl();
	@Override
	public int add(Order t) {
		return 	orderDao.add(t);
	}

	@Override
	public int delete(Serializable t) {
		return orderDao.delete(t);
	}

	@Override
	public int updata(Order t) {
		return orderDao.updata(t);
	}

	@Override
	public List<Order> select() {
		return orderDao.select();
	}

}

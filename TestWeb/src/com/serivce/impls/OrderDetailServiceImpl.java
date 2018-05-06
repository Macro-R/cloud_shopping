package com.serivce.impls;

import java.io.Serializable;
import java.util.List;

import com.beans.OrderDetail;
import com.dao.impls.OrderDetailDaoImpl;
import com.daos.OrderDetailDao;
import com.services.OrderDetailService;

public class OrderDetailServiceImpl implements OrderDetailService{
	OrderDetailDao od=new OrderDetailDaoImpl();
	@Override
	public int add(OrderDetail t) {
		return od.add(t);
	}

	@Override
	public int delete(Serializable t) {
		return od.delete(t);
	}

	@Override
	public int updata(OrderDetail t) {
		return od.updata(t);
	}

	@Override
	public List<OrderDetail> select() {
		return od.select();
	}

}

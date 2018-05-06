package com.dao.impls;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.beans.OrderDetail;
import com.daos.OrderDetailDao;
import com.ntg.utils.ResultsSetUtil;
import com.utils.JdbcUtil;

public class OrderDetailDaoImpl extends JdbcUtil  implements OrderDetailDao{
	@Override
	public int add(OrderDetail t) {
		String sql ="insert into order_detail(orderId,productId,quantity,cost) values(?,?,?,?)";
		Object [] object={t.getOrderId(),t.getProductId(),t.getQuantity(),t.getCost()};
		int result=0;
		try {
			result=executeUpdate(sql, object);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		
	}

	@Override
	public int delete(Serializable t) {
		String sql="delete from order_detail where Id=?";
		int result=0;
		Object[] object={t};
		try {
			result=executeUpdate(sql, object);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		
	}

	@Override
	public int updata(OrderDetail t) {
		int result=0;
		String sql="update order_detail set orderId=?,productId=?,quantity=?,cost=? where Id=?";
		Object[] objects={t.getOrderId(),t.getProductId(),t.getQuantity(),t.getCost(),t.getId()};
		try {
			result=executeUpdate(sql,objects);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		
	}

	@Override
	public List<OrderDetail> select() {
		String sql="select * from order_detail";
		List<OrderDetail> list =null;
		try {
			resultSet=executeQuery(sql);
			list=ResultsSetUtil.findAll(resultSet, OrderDetail.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}

	@Override
	public OrderDetail findById(Serializable t) {
		// TODO Auto-generated method stub
		return null;
	}

}

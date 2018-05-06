package com.dao.impls;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.beans.Order;
import com.daos.OrderDao;

import com.ntg.utils.ResultsSetUtil;
import com.utils.JdbcUtil;
/**
 * 订单实现类
 *
 * @author Macro
 *
 */
public class OrderDaoImpl extends JdbcUtil implements OrderDao{

	@Override
	public int add(Order t) {
		String sql ="insert into order(userId,loginName,userAddress,cost,serialNumber) values(?,?,?,?,?)";
		Object [] object={t.getUserId(),t.getLoginName(),t.getUserAddress(),t.getCost(),t.getSerialNumber()};
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
		String sql="delete from order where id=?";
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
	public int updata(Order t) {
		int result=0;
		String sql="update order set loginName=?,userAddress=?,cost=?,serialNumber=? where userId=?";
		try {
			result=executeUpdate(sql, t.getLoginName(),t.getUserAddress(),t.getCost(),t.getSerialNumber(),t.getUserId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Order> select() {
		String sql="select * from order";
		List<Order> list =null;
		try {
			resultSet=executeQuery(sql);
			list=ResultsSetUtil.findAll(resultSet, Order.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Order findById(Serializable t) {
		// TODO Auto-generated method stub
		return null;
	}
}

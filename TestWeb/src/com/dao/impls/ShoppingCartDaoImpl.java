package com.dao.impls;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.beans.ProductCategory;
import com.beans.ShoppingCart;
import com.daos.ShoppingCartDao;
import com.ntg.utils.ResultsSetUtil;
import com.utils.JdbcUtil;

public class ShoppingCartDaoImpl extends JdbcUtil implements ShoppingCartDao{

	@Override
	public int add(ShoppingCart t) {
		String sql="insert into shopping_cart(userId,productId,productNum) values(?,?,?)";
		int result=0;
		try {
			Object[] objects={t.getUserId(),t.getProductId(),t.getProductNum()};
			result =this.executeUpdate(sql, objects);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int delete(Serializable t) {
		String sql ="delete from shopping_cart where userId=?";
		int result=0;
		try {
			result=this.executeUpdate(sql, t);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updata(ShoppingCart t) {
		int result=0;
		String sql="update shopping_cart set productId=?,productNum=? where userId=? and productId=?";
		Object[] objects={t.getProductId(),t.getProductNum(),t.getUserId(),t.getProductId()};
		try {
			result=executeUpdate(sql,objects);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<ShoppingCart> select(Serializable t) {
		String sql="select * from shopping_cart where userId=?";
		List<ShoppingCart> list =null;
		try {
			resultSet=executeQuery(sql,t);
			list=ResultsSetUtil.findAll(resultSet,ShoppingCart.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
		return list;
	}

	@Override
	public ShoppingCart findById(Serializable t) {
		String sql="select * from shopping_cart where userId=?";
		ShoppingCart shoppingCart=new ShoppingCart();
		try {
			resultSet=executeQuery(sql);
			shoppingCart=ResultsSetUtil.findById(resultSet,ShoppingCart.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
		return shoppingCart;
	}

	@Override
	public List<ShoppingCart> select() {
		// TODO Auto-generated method stub
		return null;
	}

}

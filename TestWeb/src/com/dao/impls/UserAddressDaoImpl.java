package com.dao.impls;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.beans.UserAddress;
import com.daos.UserAddressDao;
import com.ntg.utils.ResultsSetUtil;
import com.utils.JdbcUtil;

public class UserAddressDaoImpl extends JdbcUtil  implements UserAddressDao{

	@Override
	public int add(UserAddress t) {
		String sql="insert into user_address(userId,address,isDefault,remark) values(?,?,?,?)";
		Object[] paramt={t.getUserId(),t.getAddress(),t.getIsDefault(),t.getRemark()};
		int rowNum=0;
		try {
			rowNum=executeUpdate(sql,paramt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowNum;
	}
	/**
	 * 删除用户方法
	 */
	
	@Override
	public int delete(Serializable t) {	
		String sql="delete from  user_address where Id=?";
		Object[] objects={t};
		int rowNum=0;
		try {
			rowNum=executeUpdate(sql,objects);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowNum;
	}
	/**
	 * 用户更新方法
	 */
	@Override
	public int updata(UserAddress t) {
		String sql ="update  user_address set userId=?,address=?,isDefault=?,remark=? where Id=?";
		Object [] object={t.getUserId(),t.getAddress(),t.getIsDefault(),t.getRemark(),t.getId()};
		int rowNum=0;
		try {
			rowNum =executeUpdate(sql, object);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return rowNum;
	}
	/**
	 * 查询所有用户
	 */
	@Override
	public List<UserAddress> select() {
		String sql="select * from user_address";
		List<UserAddress> list=new ArrayList<UserAddress>();
		try {
			ResultSet resultSet=executeQuery(sql);
			list=ResultsSetUtil.findAll(resultSet,UserAddress.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;//返回list集合
	}
	@Override
	public UserAddress findById(Serializable t) {
		return null;
	}
}

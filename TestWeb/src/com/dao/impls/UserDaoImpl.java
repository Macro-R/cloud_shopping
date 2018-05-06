package com.dao.impls;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.spy.memcached.MemcachedClient;

import com.beans.User;
import com.daos.BaseDao;
import com.daos.UserDao;
import com.ntg.utils.ResultsSetUtil;
import com.utils.JdbcUtil;
import com.utils.MemcachedUtil;

/**
 * 用户方法接口实现类
 * @author Macro
 *
 */
public class UserDaoImpl extends JdbcUtil implements UserDao{
	/**
	 * 添加用户方法
	 */
	@Override
	public int add(User t) {
		String sql="insert into user(uname,upwd,phone,email) values(?,?,?,?)";
		Object[] paramt={t.getUname(),t.getUpwd(),t.getPhone(),t.getEmail()};
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
		String num=null;
		if (t instanceof String) {
			num="uname";
		}
		if(t instanceof Integer){
			num="id";
		}
		
		String sql="delete from  user where "+num+"=?";
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
	public int updata(User t) {
		String sql ="update  user set loginName=?,userName=?,password=?,sex=? where id=?";
		Object [] object={t.getUname(),t.getUpwd(),t.getPhone(),t.getEmail()};
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
	public List<User> select() {
		String sql="select * from user";
		List<User> list=new ArrayList<User>();
		try {
			ResultSet resultSet=executeQuery(sql);
			list=ResultsSetUtil.findAll(resultSet,User.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
		return list;//返回list集合
	}
	/**
	 * 登录方法
	 */
	public User login(String loginName,String password){
//		(uname=? and upwd=?)or(phone =? and upwd=? )or(email=? and upwd=?) 
		String sql="select * from user where (uname=? or phone =? or email=?)";
		Object [] paramt={loginName,loginName,loginName};
		User user=null;
		try {
			resultSet=executeQuery(sql,paramt);
			user=ResultsSetUtil.findById(resultSet,User.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
		return user;//返回user对象
	}

	

@Override
public User findById(Serializable t) {

	String sql="select * from user  where uid=?";
	Object [] objects={t};
	ResultSet rs=null;
	User user=null;
	try {
		rs =executeQuery(sql, objects);
		user=ResultsSetUtil.findById(resultSet,User.class);
	} catch (SQLException e) {
		e.printStackTrace();
	}finally{
		close();
	}
	return user;
}
@Override
public boolean findByName(Serializable t) {
	String sql ="select * from user where uname=?";
	try {
		ResultSet set=this.executeQuery(sql,t);
		if (set.next()) {
			return true;
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}finally{
		close();
	}
	return false;
}
}

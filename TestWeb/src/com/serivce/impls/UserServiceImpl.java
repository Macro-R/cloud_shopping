package com.serivce.impls;

import java.io.Serializable;
import java.util.List;

import com.beans.User;
import com.dao.impls.UserDaoImpl;
import com.daos.UserDao;
import com.services.UserSrevice;
import com.utils.MemcachedUtil;

public class UserServiceImpl implements UserSrevice{
UserDao userDao=new UserDaoImpl();
	@Override
	public int add(User t) {
		return userDao.add(t);
	}

	@Override
	public int delete(Serializable t) {
		return userDao.delete(t);
	}

	@Override
	public int updata(User t) {
		return userDao.updata(t);
	}

	@Override
	public List<User> select() {
		
		return userDao.select();
		
	}

	@Override
	public User login(String loginName, String password) {
		User user=null;
		/*if(MemcachedUtil.getIntance().get("myUser")==null){*/
		user=userDao.login(loginName, password);
	/*	MemcachedUtil.getIntance().set("myUser", 60*60, user);//保存六十秒
*/		return user;
		/*}else {
			return (User)MemcachedUtil.getIntance().get("myUser");
		}*/
		
	}
	/**
	 * 测试memcached
	 */
	UserDao dao =new UserDaoImpl();
	@Override
	public User findby(int id) {
		if(MemcachedUtil.getIntance().get("myUser")==null){
		User user=dao.findById(id);
		System.out.println("进入数据库获取数据");
		MemcachedUtil.getIntance().set("myUser", 10, user);
		return user;
		}else {
			System.out.println("进入memcached获取数据");
			return (User)MemcachedUtil.getIntance().get("myUser");
		}
	}

	@Override
	public boolean findByName(Serializable t) {
		return userDao.findByName(t);
	}

	public static void main(String[] args) {
		UserServiceImpl userSrevice=new UserServiceImpl();
		User user=userSrevice.findby(7);
		System.out.println(user);
	}
}

package com.daos;

import java.io.Serializable;

import com.beans.User;
/**
 * 用户接口继承主接口
 *
 * @author Macro
 *
 */
public interface UserDao extends BaseDao<User>{
	public User login(String loginName,String password);
	User findById(Serializable t);
	boolean findByName(Serializable t);
}

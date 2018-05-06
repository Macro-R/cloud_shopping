package com.services;

import java.io.Serializable;
import java.util.List;

import com.beans.User;

public interface UserSrevice {
	public int add(User t);
	public int delete(Serializable t);
	public int updata(User t);
	public List<User> select();
	public User login(String loginName,String password);
	public User findby(int id);
	boolean findByName(Serializable t);
}

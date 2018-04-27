package com.daos;

import java.io.Serializable;
import java.util.List;

import com.beans.User;
/**
 * 
 *增删改查主接口
 * @author Macro
 *
 */
public interface BaseDao<T> {
	int add(T t);
	int delete(Serializable t);
	int updata(T t);
	List<T> select();
	T findById(Serializable t);
}

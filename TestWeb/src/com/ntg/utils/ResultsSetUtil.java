package com.ntg.utils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultsSetUtil {
	/**
	 * 查询某个ID的实体
	 * @param resultSet
	 * @param clazz
	 * @return
	 */
	public static <T> T findById(ResultSet resultSet,Class<T> clazz){
		T object=null;
		try {
			
			if (resultSet.next()) {
				object=clazz.newInstance();//获取对象
			    Field[] fields = clazz.getDeclaredFields();//获取字段
				for (Field field : fields) {//遍历对象所有字段
					field.setAccessible(true);//获取去操作私有字段权限
					System.out.println(field.getName());
					field.set(object,resultSet.getObject(field.getName()));//第一个参数为对象，第二个参数为字段值
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return object;
	}
	
	/**
	 * 查询全部实体
	 * @param resultSet
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> findAll(ResultSet resultSet,Class<T> clazz){
		T object=null;
		List<T> list =new ArrayList<T>();
		try {
			while (resultSet.next()) {
				object=clazz.newInstance();
				Field[] fields = clazz.getDeclaredFields();
				for ( Field field: fields) {
					field.setAccessible(true);
					field.set(object, resultSet.getObject(field.getName()));
				}
				list.add(object);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return list;  
		
	}
}



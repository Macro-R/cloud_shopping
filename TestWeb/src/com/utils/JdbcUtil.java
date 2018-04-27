package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 遍历工具类
 *
 * @author Macro
 *
 */
public class JdbcUtil {
	protected	static Connection connection=null;
	protected	static PreparedStatement ps=null;
	protected	static ResultSet resultSet=null;

	/**
	 * 加载驱动
	 */
	private static  boolean loading(){
		try {
			Class.forName(ConfigManager.getInstance().getValue("driver"));
			connection=DriverManager.getConnection(ConfigManager.getInstance().getValue("url"),
					ConfigManager.getInstance().getValue("user"),ConfigManager.getInstance().getValue("password"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * 关闭资源
	 */
	public static void close() {
		if(resultSet!=null){
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(ps!=null){
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(connection!=null){
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 增删改操作
	 * @throws SQLException 
	 */
	public int executeUpdate(String sql,Object... object) throws SQLException{
		int result=0;
		if(loading()){
			ps=connection.prepareStatement(sql);
			if(object!=null){
				for(int i=0;i<object.length;i++){
					ps.setObject(i+1, object[i]);
				}
			}
			result=ps.executeUpdate();
		}
		close();
		return result;
	}
	/**
	 * 查询
	 * @throws SQLException 
	 */
	public ResultSet executeQuery(String sql,Object... object) throws SQLException{
		if(loading()){
			ps=connection.prepareStatement(sql);
			if(object!=null){
				for(int i=0;i<object.length;i++){
					ps.setObject(i+1, object[i]);
				}
			}
			resultSet=ps.executeQuery();
		}
		return resultSet;
	}
}

package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
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
	public static  boolean loading(){
		try {	
			//初始换上下文对象tomcat容器
			Context initialContext = new InitialContext();
		    //通过数据源中的name属性获取指定的数据源>>根据context.xml:数据源的地方
			DataSource data = (DataSource)initialContext.lookup("java:comp/env/jdbc/news");
			//在连接池获得空闲连接数据库对象
			connection = data.getConnection();
			return true;
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}
	/**
	 * 加载驱动
	 */
	/*private static  boolean loading(){
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
	}*/
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

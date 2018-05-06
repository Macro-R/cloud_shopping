package com.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
	
private static ConfigManager cm=new ConfigManager();

private static Properties properties;

/**
 * 无参构造，把配置文件的键值加载再输入流中
 */
	private ConfigManager() {
		properties = new Properties();
		InputStream iS = ConfigManager.class.getClassLoader().getResourceAsStream("database.properties");
		try {
			properties.load(iS);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				iS.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 返回自身
	 * 
	 */
	public static synchronized ConfigManager getInstance(){
		return cm;
	}
	/**
	 * 用getInstance()方法点getValue()方法就可以获取配置文件的某个值
	 */
	public static String getValue(String key){
		return properties.getProperty(key);
	}
}

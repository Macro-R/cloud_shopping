package com.utils;

import java.io.IOException;
import java.net.InetSocketAddress;

import net.spy.memcached.MemcachedClient;

public class MemcachedUtil {
	private  static MemcachedUtil util;
	private static MemcachedClient client=null;
	static{
		util=new MemcachedUtil();
		try {
			client=new MemcachedClient(new InetSocketAddress("127.0.0.1",11211));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private MemcachedUtil(){}
	public static MemcachedClient getIntance(){
		return client;
	}
}

package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.User;

@WebFilter("/*")
public class CharsetFilter implements Filter{

	@Override
	public void destroy() {
		System.out.println("CharsetFilter==============>初始化");
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		System.out.println("CharsetFilter==============>doFile进行处理");
		arg0.setCharacterEncoding("utf-8");
		arg1.setCharacterEncoding("utf-8");
		HttpServletRequest request=(HttpServletRequest)arg0;
		HttpServletResponse response=(HttpServletResponse) arg1;
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		User user=(User)request.getSession().getAttribute("user");
		String requestURI = request.getRequestURI();
		System.out.println("这是请求的URI："+requestURI);
		if(user==null&&requestURI.contains("my-add")){
			System.out.println("没登录进入结算");
			response.sendRedirect("login.jsp");
		}else{
			System.out.println("CharsetFilter==============>放行");
			arg2.doFilter(arg0, arg1);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("CharsetFilter==============>销毁");
	}

}

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
@WebFilter("/back/*")
public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		System.out.println("LoginFilter==============>初始化");
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		System.out.println("LoginFilter==============>进行处理");
		HttpServletRequest request=(HttpServletRequest) arg0;
		HttpServletResponse response =(HttpServletResponse) arg1;
		User user=(User)request.getSession().getAttribute("user");
		String requestURI = request.getRequestURI();
		System.out.println("这是请求的URI："+requestURI);
		if(user!=null||requestURI.contains("login")){
			System.out.println("LoginFilter===============>放行");
			arg2.doFilter(arg0, arg1);
		}else{
			System.out.println("LoginFilter===============>没登录");
			response.sendRedirect("../login.jsp");
		}
		/*arg2.doFilter(arg0, arg1);*/
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("LoginFilter==============>销毁");
	}

}

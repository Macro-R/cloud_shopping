package com.serlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.Md5Encrypt.Md5Encrypt;
import com.beans.User;
import com.serivce.impls.UserServiceImpl;
import com.services.UserSrevice;
@WebServlet(value={"/UserSerlet"})

public class UserSerlet extends HttpServlet{
	private final Logger logger=Logger.getLogger(UserSerlet.class);
	@Override
	public void init() throws ServletException{
		logger.info("初始化时，init()方法被调用");
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserSrevice us=new UserServiceImpl();
		User user=null;
		String pwd=null;
		String q=null;
		byte[] bs=null;
		resp.setCharacterEncoding("utf-8");
		String method=req.getParameter("method");
		switch (method) {
		case "add":
			user=new User();
			user.setUname(req.getParameter("name"));
			try {
				pwd = Md5Encrypt. getEncryptedPwd(req.getParameter("pwd"));//获得加密后的16进制
				bs = Md5Encrypt.hexStringToByte(pwd);//16进制的字符串转换成数组
	    		q =Md5Encrypt. byteToHexString(bs);//baty数组转换成16进制字符串
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			
			user.setUpwd(q);
			String ep=req.getParameter("email_phone");
			if (ep.indexOf("@")!=-1) {
				user.setEmail(ep);
				logger.info("注册邮箱");
			} else {
				user.setPhone(ep);
				logger.info("注册手机");
			}
			
			if(us.add(user)!=0){
				req.getSession().setAttribute("user",user);
				logger.info("注册成功");
				resp.sendRedirect("login.jsp");
				
			}else {
				logger.info("注册失败");
				resp.sendRedirect("register.jsp");
			}
			
			break;
		case "login":		
			user=us.login(req.getParameter("nep"),req.getParameter("pwd"));
			try {
				/*System.out.println("输入密码："+req.getParameter("pwd"));
				System.out.println("数据库数据："+user.getUname()+user.getUpwd());*/
				if(Md5Encrypt.validPassword(req.getParameter("pwd"), user.getUpwd())||req.getParameter("pwd").equals(user.getUpwd())){
					Cookie cookie=new Cookie("pwd", user.getUpwd());
					Cookie cookie1=new Cookie("name",user.getUname());
					if(user!=null){
						logger.info(req.getParameter("nep")+"登录成功！");
						req.getSession().setAttribute("user",user);
						if(req.getParameter("rspwd")!=null){
							cookie1.setMaxAge(60*60);
							cookie.setMaxAge(60*60);
							resp.addCookie(cookie);
							resp.addCookie(cookie1);
						}else{
							cookie1.setMaxAge(0);
							cookie.setMaxAge(0);
							resp.addCookie(cookie);
							resp.addCookie(cookie1);
						}
						if(user.getType()==1){
							resp.sendRedirect("back/index.jsp");
						}else{
							resp.sendRedirect("index.jsp");
						}
						
					}
				}else {
					logger.info(req.getParameter("nep")+"登录失败！");
					resp.sendRedirect("login.jsp");
				}
				
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			break;
		}
		logger.info("处理请求时，doGet()方法被调用");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		logger.info("处理请求时，doPost()方法被调用");
		req.setCharacterEncoding("UTF-8");
		doGet(req, resp);
	}
	@Override
	public void destroy() {
		super.destroy();
		logger.info("释放系统资源时，destroy()方法被调用");
	}
	
}

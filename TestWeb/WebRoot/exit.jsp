<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.beans.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
   <%
/*    	session.removeAttribute("user");*/
	/* session.Abandon */
   	session.invalidate();
   	 /* session.set */
 /*   	request.getSession().setMaxInactiveInterval(1); */
   	if(null==request.getSession(false)){   
   if(true==request.getSession(true).isNew()){   
   System.out.println("session没过期");   
      }   
else{   
System.out.println("session已经过期");   
}   
}
    
   	response.sendRedirect("login.jsp");
    %>


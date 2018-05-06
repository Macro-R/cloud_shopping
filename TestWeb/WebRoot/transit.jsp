<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'transit.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery.js"></script>
  </head>
  
  <body>
    <c:choose>
    	<c:when test="${requestScope.a == 'login'} ">
    		<c:if test="${requestScope.b eq 'trues'} ">
    		 	<!-- <script type="text/javascript">
    			 	alert("登录成功！");
    			 </script> -->
    			<%
    				response.sendRedirect("index.jsp");
    			 %>
    			
    		</c:if>
    		<c:if test="${requestScope.b eq 'falses'}">
    		 <!-- <script type="text/javascript">
    			 	alert("登录失败！");
    			 </script> -->
    			<%
    				response.sendRedirect("login.jsp");
    			 %>
    			
    		</c:if>
    	</c:when>
    	<c:when test="${requestScope.a  eq 'add'}">
    		<c:if test="${requestScope.b  eq 'trues'}">
    			<!--  <script type="text/javascript">
    			 	alert("注册成功！");
    			 </script> -->
    			<%
    				response.sendRedirect("login.jsp");
    			 %>
    			
    		</c:if>
    		<c:if test="${requestScope.b eq 'falses'}">
    		 	<!-- <script type="text/javascript">
    			 	alert("注册失败！");
    			 </script> -->
    			<%
    				response.sendRedirect("register.jsp");
    			 %>
    			
    		</c:if>
    	</c:when>
    	<c:otherwise>
    	
    	</c:otherwise>
    </c:choose>
  </body>
</html>

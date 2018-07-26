<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>我是购物车：</h2>
	<%
		Map<String, Integer> map = (Map<String, Integer>)session.getAttribute("cart");
		if (map == null) {
	%>
		<span>购物车为空</span>
	<%
		} else {
			for (String key : map.keySet()) {
				int value = map.get(key);
	%>
				<h4>商品：<%= key%>, 数目：<%=value %></h4>
	<%
			}
		}
	%>
	<a href="ClearCartServlet"><h4>清空购物车</h4></a>
</body>
</html>
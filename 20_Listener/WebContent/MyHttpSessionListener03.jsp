<%@page import="com.jettjia.listener3.MyHttpSessionListener03"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	一访问JSP页面，HttpSession就创建了，创建好的Session的Id是：${pageContext.session.id}
	<%
		session.setAttribute("bean", new MyHttpSessionListener03("haha"));
	%>
</body>
</html>
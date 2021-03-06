package com.jettjia.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HTTPServletReauest extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * 0 获得客户机信息
		 */
		String requestUrl = request.getRequestURL().toString();// 得到请求的URL地址
		String requestUri = request.getRequestURI();// 得到请求的资源
		String queryString = request.getQueryString();// 得到请求的URL地址中附带的参数
		String remoteAddr = request.getRemoteAddr();// 得到来访者的IP地址
		String remoteHost = request.getRemoteHost();
		int remotePort = request.getRemotePort();
		String remoteUser = request.getRemoteUser();
		String method = request.getMethod();// 得到请求URL地址时使用的方法
		String pathInfo = request.getPathInfo();
		String localAddr = request.getLocalAddr();// 获取WEB服务器的IP地址
		String localName = request.getLocalName();// 获取WEB服务器的主机名
		response.setCharacterEncoding("UTF-8");// 设置将字符以"UTF-8"编码输出到客户端浏览器
		// 通过设置响应头控制浏览器以UTF-8的编码显示数据，如果不加这句话，那么浏览器显示的将是乱码
		response.setHeader("content-type", "text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
        out.write("<br/>");
        out.write("请求的URL地址："+requestUrl);
        out.write("<br/>");
        out.write("请求的资源："+requestUri);
        out.write("<br/>");
        out.write("得到请求的URL地址中附带的参数的资源："+queryString);
        out.write("<br/>");
        out.write("得到来访者的IP地址："+remoteAddr);
		
		out.write("<hr/>");
		
		/**
		 * 1 获取客户端请求头信息
		 */
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String name = headerNames.nextElement();
			String value = request.getHeader(name);
			out.write("<br/>");
			out.write(name + ":" + value);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

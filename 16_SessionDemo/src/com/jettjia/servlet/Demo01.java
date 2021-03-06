package com.jettjia.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Demo01 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获得session对象
		HttpSession session = request.getSession();
		// 获得sessionId
		String sessionId = session.getId();
		
		// 存放数据
		session.setAttribute("name", "jett");
		// 取出数据
		Object name = session.getAttribute("name");
		// 移除数据
		session.removeAttribute("name");
		System.out.println(session.getAttribute("name"));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

package com.jettjia.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
    
	/**
	 * request: 请求的信息
	 * response: 响应给浏览器的信息
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1 获取请求数据
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username + " " + password);
		
		// 2 校验数据，此处忽略
		
		// 3 获取向客户端输出内容对象
		PrintWriter pw = response.getWriter();
		
		// 4 判断用户是否是admin, 123。是的话，登录加1
		if ("admin".equals(username) && "123".equals(password)) {
			//pw.write("login success");
			
			// 获取统计登录次数的值
			Object obj = getServletContext().getAttribute("count");
			
			int totalCount = 0;
			if (obj != null) {
				totalCount = (int)obj;
			}
			// 设置count的值
			getServletContext().setAttribute("count", totalCount+1);
			
			// 登录成功，跳转到成功页面
			response.setStatus(302);
			response.setHeader("Location", "login_success.html");
		} else {
			pw.write("login fail");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

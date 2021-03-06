package com.jettjia.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jettjia.util.CookieUtil;

public class Demo02 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if ("admin".equals(username) && "123".equals(password)) {
			// 获得客户端返回的cookie
			Cookie[] cookies = request.getCookies();
			// 从数组里，找出记录登陆时间的cookie
			Cookie lastTimeCookie = CookieUtil.findCookie(cookies, "lastTime");
			if (lastTimeCookie == null) { // 表示第一次登陆
				Cookie c = new Cookie("lastTime", System.currentTimeMillis()+"");
				c.setMaxAge(60*60);
				response.addCookie(c);
				response.getWriter().write("欢迎您：" + username);
			} else { // 再次登陆
				// 取出上次登陆记录在cookie里的登陆时间
				long lastVisitTime = Long.parseLong(lastTimeCookie.getValue());
				// 输出到页面
				response.getWriter().write("欢迎您：" + username + "，您上次登陆是：" + new Date(lastVisitTime));
				// 更新登陆时间
				Cookie c = new Cookie("lastTime", System.currentTimeMillis()+"");
				response.addCookie(c);
			}
		} else {
			response.getWriter().write("登陆失败");
		}
	}
	
	private String SimpleDateFormat(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

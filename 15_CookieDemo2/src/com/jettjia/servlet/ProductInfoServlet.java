package com.jettjia.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jettjia.util.CookieUtil;

public class ProductInfoServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1 获取用户浏览的商品ID
		String id = request.getParameter("id");
		
		// 2 获取客户端返回的所有cookie数组
		Cookie[] cookies = request.getCookies();
		Cookie findCookie = CookieUtil.findCookie(cookies, "history"); // 获得history这个cookie的对象
		
		// 3 判断cookie对象是否已经存在
		if (findCookie == null) { // 第一次访问
			// 1 设置cookie
			Cookie c = new Cookie("history", id);
			// 2 设置过期时间
			c.setMaxAge(24*60*60*7); // 7天
			// 3 设置访问工程的时候，才带cookie过来
			c.setPath(request.getContextPath()); // 该路径是: /项目名
			// 4 返回cookie到客户端
			response.addCookie(c);
		} else { // 第二次及后续访问
			// 1 获取以前设置的cookie值
			String ids = findCookie.getValue();
			// 2 现在浏览的商品和以前浏览的商品拼接成新的值,用#号隔开
			findCookie.setValue(id + "#" + ids);
			// 3 设置过期时间
			findCookie.setMaxAge(24*60*60*7); // 7天
			findCookie.setPath(request.getContextPath()); // 该路径是: /项目名
			// 4 返回cookie到客户端
			response.addCookie(findCookie);
		}
		// 4 跳转到商品详情页
		response.sendRedirect("product_info.htm");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

package com.jettjia.servlet;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CarServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		// 1 获取到下单的商品id
		int id = Integer.parseInt(request.getParameter("id"));
		// 2 模拟商品数组
		String[] goods = {"Iphone7", "小米6", "三星Note8", "魅族7", "华为9"};
		String name = goods[id]; // 商品名称
		
		// 3 获取购物车里的session数据，存放到Map里，并且保证只保存一次 Map<String , Integer>  iphoen7 3
		Map<String, Integer> map = (Map<String, Integer>) request.getSession().getAttribute("cart");
		// session里没有数据
		if (map == null) {
			map = new LinkedHashMap<String, Integer>();
			request.getSession().setAttribute("cart", map);
		}
		
		// 4 判断购物车里有没有该商品
		if (map.containsKey(name)) {
			map.put(name, map.get(name) + 1); // 如果已经存在，则商品数目+1
		} else {
			map.put(name, 1); // 没有购买过该商品，直接商品数目为1
		}
		
		// 获得 sessionId， 并把其写入cookie，返回给客户端
		String sId = request.getSession().getId();
		Cookie cookie = new Cookie("JSESSIONID", sId);
		cookie.setMaxAge(60*60*24*7);
		response.addCookie(cookie);
		
		// 5 输出界面
		response.getWriter().write("<a href='product_list.jsp'><h3>继续购物</h3></a><br>");
		response.getWriter().write("<a href='cart.jsp'><h3>去购物车结算</h3></a>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

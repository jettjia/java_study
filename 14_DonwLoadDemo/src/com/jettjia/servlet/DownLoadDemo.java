package com.jettjia.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jettjia.DonwLoadUtil.DownLoadUtil;

public class DownLoadDemo extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1 获得请求的filename 名称
		String filename = request.getParameter("filename");
		// 中文名称处理
		filename = new String(filename.getBytes("ISO-8859-1"), "UTF-8");
		// 2 获得这个文件在tomcat里的绝对路径
		String path = getServletContext().getRealPath("download/" + filename);
		
		
		// 3.1 下载的时候，提醒下载，而不是直接下载
		// 3.2 获取访问的客户端，不同的客户端，对于中文处理不同，如下
		String clientType = request.getHeader("User-Agent");
		if (clientType.contains("Firefox")) {
			// 专门处理firefox方法, 在DownLoadUtil工具类中
			filename = DownLoadUtil.base64EncodeFileName(filename);
		} else {
			// 谷歌，IE浏览器
			filename = URLEncoder.encode(filename, "UTF-8");
		}
		response.setHeader("Content-Disposition", "attachment; filename="+filename);
		// 4 将获取到的文件转入到输入流中
		FileInputStream is = new FileInputStream(path);
		ServletOutputStream ot = response.getOutputStream();
		// 5 将输入流中内容返回给客户端
		int len = 0;
		byte[] buf = new byte[1024];
		while((len = is.read(buf)) != -1) {
			ot.write(buf, 0, len);
		}
		ot.close();
		is.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

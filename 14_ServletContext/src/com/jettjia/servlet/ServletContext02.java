package com.jettjia.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletContext02 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//test01();
		//test02();
		test03();
	}
	
	/**
	 * 根据classloader去获取工程下的资源    类加载器（JDBC）
	 */
	private void test03() {
		try {
			// 1 获取属性对象
			Properties properties = new Properties();
			/**
			 * 2 ClassLoader 去获取工程下的资源
			 * 获取该java文件的class ，然后获取到加载这个class到虚拟机中的那个类加载器对象。
			 * 
			 * ServletContext getServletContext()获取到的路径是：(就是项目的根目录)
			 * 		D:\develop\java_study\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\14_ServletContext
			 * ClassLoader getClass().getClassLoader()获取到的路径是：
			 * 		\wtpwebapps\14_ServletContext\WEB-INF\classes
			 * 		默认的ClassLoader路径是在上面路径下，要回到14_ServletContext下，才能进入file目录。
			 * 		解决方法：../../ 先回到\14_ServletContext这一层，然后进入\wtpwebapps\14_ServletContext\file\config.properties
			 */
			//System.out.println(getClass().getClassLoader());
			InputStream is = this.getClass().getClassLoader().getResourceAsStream("../../file/config.properties");
			// 3 属性对象载入文档流
			properties.load(is);
			// 4 属性对象获取name的属性值
			String name = properties.getProperty("name");
			System.out.println(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据相对路径，直接获取文档流对象getResourceAsStream()
	 */
	private void test02() {
		try {
			// 1 获取servletContext对象
			ServletContext servletContext = getServletContext();
			// 2 获取属性对象
			Properties properties = new Properties();
			// 3 根据相对路径，直接获取流对象
			InputStream resourceAsStream = servletContext.getResourceAsStream("file/config.properties");
			// 4 属性对象载入文档流
			properties.load(resourceAsStream);
			// 5 属性对象获取name的属性值
			String name = properties.getProperty("name");
			System.out.println(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 先获取路径，再获取流对象
	 * 
	 * @throws IOException
	 */
	private void test01() throws IOException {
		// 1 获取servletContext对象
		ServletContext servletContext = getServletContext();
		// 2 获取给定的文件在服务器端的绝对路径
		// String realPath = servletContext.getRealPath("");
		// System.out.println(realPath); //
		// D:\develop\java_study\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\14_ServletContext
		String realPath = servletContext.getRealPath("file/config.properties");
		// 3 创建属性对象
		Properties properties = new Properties();
		// 4 获取文档流
		InputStream is = new FileInputStream(realPath);
		// 5 属性对象载入文档流
		properties.load(is);
		// 6 获取name属性值
		String name = properties.getProperty("name");
		System.out.println(name);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

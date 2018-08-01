package com.jettjia.spring.demo3;

import javax.annotation.Resource;

public class ProductService {
	
	@Resource(name="productDao")
	private ProductDao productDao;
	@Resource(name="orderDao")
	private OrderDao orderDao;
	
	/*public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}*/

	public void save() {
		System.out.println("ProductService执行save");
		orderDao.save();
		productDao.save();
	}
}

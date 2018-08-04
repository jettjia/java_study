package com.jettjia.mybatis.dao;

import java.util.List;

import com.jettjia.mybatis.pojo.User;

public interface UserDao {
    
    /**
     * 根据用户ID查询用户信息
     * @param id
     * @return
     */
    User getUserById(Integer id);
    
    /**
     * 根据用户名查找用户列表
     * @param name
     * @return
     */
    List<User> getUserByUserName(String name);
    
    /**
     * 添加用户
     * @param user
     */
    void insertUser(User user);
}

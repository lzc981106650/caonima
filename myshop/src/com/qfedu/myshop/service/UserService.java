package com.qfedu.myshop.service;

import com.qfedu.myshop.entity.User;

import java.sql.SQLException;

public interface UserService {

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     * @throws SQLException
     */
    User findUserByUserName(String username) throws SQLException;

    /**
     * 注册User
     * @param user
     * @return
     * @throws SQLException
     */
    boolean registerUser(User user) throws SQLException;

    /**
     * 根据激活码，修改用户状态
     * @param code
     * @return
     * @throws SQLException
     */
    boolean active(String code) throws SQLException;
}

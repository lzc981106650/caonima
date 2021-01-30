package com.qfedu.myshop.dao;

import com.qfedu.myshop.entity.User;

import java.sql.SQLException;

public interface UserDao {
    /**
     * 根据用户名查找用户
     * @param userName
     * @return
     * @throws SQLException
     */
    User findByUserName(String username) throws SQLException;

    /**
     * 注册插入用户
     * @param user
     * @return
     * @throws SQLException
     */
    int insertUser(User user) throws SQLException;

    /**
     * 根据激活码，查找对应用户
     * @param code
     * @return
     * @throws SQLException
     */
    User findUserByCode(String code) throws SQLException;

    /**
     * 更新用户
     * @param user
     * @return
     * @throws SQLException
     */
    int updateUser(User user) throws SQLException;
}

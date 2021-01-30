package com.qfedu.myshop.dao.impl;

import com.qfedu.myshop.dao.UserDao;
import com.qfedu.myshop.entity.User;
import com.qfedu.myshop.utils.BaseDao;

import java.sql.SQLException;

public class UserDaoImpl extends BaseDao implements UserDao {

    /**
     * 根据用户名查询用户是否存在
     * @param username
     * @return
     * @throws SQLException
     */
    @Override
    public User findByUserName(String username) throws SQLException {
        String sql = " SELECT u_id uid,u_name username,u_password upassword,u_email email,u_sex usex,u_status ustatus,u_code code,u_role urole FROM shoping2009.user WHERE u_name=?";
        return queryBean(sql, User.class, username);
    }

    /**
     * 插入数据，添加新用户
     * @param user
     * @return
     * @throws SQLException
     */
    @Override
    public int insertUser(User user) throws SQLException {
        String sql = "insert into shoping2009.user(u_name, u_password, u_email, u_sex, u_status, u_code, u_role) VALUES (?,?,?,?,?,?,?)";

        return queryRunner.update(sql, user.getUsername(), user.getUpassword(), user.getEmail(), user.getUsex(), user.getUstatus(), user.getCode(), user.getUrole());
    }

    /**
     * 根据激活码，查找对应用户
     * @param code
     * @return
     * @throws SQLException
     */
    @Override
    public User findUserByCode(String code) throws SQLException {
        String sql = "select u_id uid, u_name username, u_password upassword, u_email email, u_sex usex, u_status ustatus, u_code code, u_role urole from shoping2009.user where u_code=?";
        return queryBean(sql, User.class, code);
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     * @throws SQLException
     */
    @Override
    public int updateUser(User user) throws SQLException {
        String sql = "update shoping2009.user set u_status = ?,u_name=?,u_password=?,u_email=?,u_sex=? where u_id=?";
        int num = queryRunner.update(sql, user.getUstatus(), user.getUsername(), user.getUpassword(), user.getEmail(), user.getUsex(), user.getUid());
        return num;
    }
}

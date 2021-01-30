package com.qfedu.myshop.service.impl;

import com.qfedu.myshop.dao.UserDao;
import com.qfedu.myshop.dao.impl.UserDaoImpl;
import com.qfedu.myshop.entity.User;
import com.qfedu.myshop.service.UserService;
import com.qfedu.myshop.utils.*;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public User findUserByUserName(String username) throws SQLException {
        return userDao.findByUserName(username);
    }

    @Override
    public boolean registerUser(User user) throws SQLException {
        user.setUstatus(Constants.USER_NOT_ACTIVE+"");

        user.setUrole(Constants.ROLE_CUSTOMER);

        user.setCode(RandomUtils.createActive());

        user.setUpassword(MD5Utils.md5(user.getUpassword()));

        EmailUtils.sendEmail(user);

        int num =  userDao.insertUser(user);

        if(num > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean active(String code) throws SQLException {
        code = Base64Utils.decode(code);

        User user = userDao.findUserByCode(code);

        if(user != null) {
            user.setUstatus(Constants.USER_ACTIVE + "");
            int num = userDao.updateUser(user);
            if(num > 0) {
                return true;
            }
        }
        return false;
    }
}

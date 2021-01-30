package com.qfedu.myshop.service;

import com.qfedu.myshop.entity.Type;

import java.sql.SQLException;
import java.util.List;

public interface TypeService {


    /**
     * 获取商品所有类别
     * @return
     */
    List<Type> getAllType() throws SQLException;

}

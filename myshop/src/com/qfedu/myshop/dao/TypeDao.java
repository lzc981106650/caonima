package com.qfedu.myshop.dao;

import com.qfedu.myshop.entity.Type;

import java.sql.SQLException;
import java.util.List;

/**
 * 商品类型 相关
 */
public interface TypeDao {


    /**
     * 获取商品所有类别
     * @return
     */
    List<Type> getAllType() throws SQLException;

}

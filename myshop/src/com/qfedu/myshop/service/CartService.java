package com.qfedu.myshop.service;

import com.qfedu.myshop.entity.Cart;

import java.sql.SQLException;
import java.util.List;

public interface CartService {
    // 展示
    List<Cart> show(int uid) throws SQLException;

    // 创建购物车
    Boolean create(int uid, int pid) throws SQLException;

    // 添加商品数据
//    int add(Cart cart) throws SQLException;

    // 购物车有商品 更新购物车
    int update(Cart cart) throws SQLException;

    Cart findCartByCid(int cid) throws SQLException;

    // 删除购物车中的商品
    int delete(int cid) throws SQLException;

    // 清空购物车
    int clear(int uid) throws SQLException;

}

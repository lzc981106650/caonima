package com.qfedu.myshop.dao;

import com.qfedu.myshop.entity.Cart;

import java.sql.SQLException;
import java.util.List;

public interface CartDao {
    List<Cart> show(int uid) throws SQLException;

    // 创建购物车
    Cart create(int pid, int uid) throws SQLException;

   // 添加商品数据
   int add(Cart cart) throws SQLException;

    // 购物车有商品 更新购物车
    int update(Cart cart) throws SQLException;

    Cart findCartByCid(int cid) throws SQLException;

    // 删除商品
    int delete(int cid) throws SQLException;

    // 清除购物车
    int clear(int uid) throws SQLException;
}


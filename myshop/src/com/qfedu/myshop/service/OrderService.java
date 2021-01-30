package com.qfedu.myshop.service;

import com.qfedu.myshop.entity.Orders;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface OrderService {
    // 展示订单列表
    List<Orders> show(int uid) throws SQLException;

    // 创建订单
    int create(int uid, int aid, int sum) throws SQLException;

    Orders detail(String oid) throws SQLException;


    /**
     * 修改订单状态
     * @param state
     * @param oid
     * @return
     */
    boolean modifyState(String oid,int state) throws SQLException;
}

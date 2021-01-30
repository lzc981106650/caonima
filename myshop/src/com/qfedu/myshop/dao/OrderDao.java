package com.qfedu.myshop.dao;

import com.qfedu.myshop.entity.Orders;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao {
    List<Orders> show(int uid) throws SQLException;

    // 创建订单
    int add(Orders orders) throws SQLException;

    Orders detail(String oid) throws SQLException;
    /**
     * 修改订单状态
     * @param state
     * @param oid
     * @return
     */
    int modifyState(String oid,int state) throws SQLException;

}

package com.qfedu.myshop.dao;

import com.qfedu.myshop.entity.Address;

import java.sql.SQLException;
import java.util.List;

public interface AddressDao {
    // 展示
    List<Address> findAllAddress(int uid) throws SQLException;

    // 增加
    int add(Address address) throws SQLException;

    // 删除
    int delete(int aid) throws SQLException;

    // 修改
    int update(Address address) throws SQLException;

    // 设置默认地址
    int setDefaultOne(int uid) throws SQLException;

    int setDefaultTwo(int aid) throws SQLException;

    // 通过aid找到地址详细信息
    Address findAddressByAid(int aid) throws SQLException;
}

package com.qfedu.myshop.dao;

import com.qfedu.myshop.entity.Cart;
import com.qfedu.myshop.entity.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItermDao {
    int addIterm(List<Cart> carts, String oid) throws SQLException;

    List<Item> detail(String oid) throws SQLException;
}

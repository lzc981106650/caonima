package com.qfedu.myshop.service.impl;

import com.qfedu.myshop.dao.AddressDao;
import com.qfedu.myshop.dao.CartDao;
import com.qfedu.myshop.dao.ItermDao;
import com.qfedu.myshop.dao.OrderDao;
import com.qfedu.myshop.dao.impl.AddressDaoImpl;
import com.qfedu.myshop.dao.impl.CartDaoImpl;
import com.qfedu.myshop.dao.impl.ItermDaoImpl;
import com.qfedu.myshop.dao.impl.OrderDaoImpl;
import com.qfedu.myshop.entity.Address;
import com.qfedu.myshop.entity.Cart;
import com.qfedu.myshop.entity.Item;
import com.qfedu.myshop.entity.Orders;
import com.qfedu.myshop.service.OrderService;
import utils.RandomUtils;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private static OrderDao orderDao = new OrderDaoImpl();
    private static AddressDao addressDao = new AddressDaoImpl();
    private static CartDao cartDao = new CartDaoImpl();
    private static ItermDao itermDao = new ItermDaoImpl();

    @Override
    public List<Orders> show(int uid) throws SQLException {
        return orderDao.show(uid);
    }

    @Override
    public int create(int uid, int aid, int sum) throws SQLException {
        Address address = addressDao.findAddressByAid(aid);
        Orders orders = new Orders();
        BigDecimal sum1 = new BigDecimal(sum);

        orders.setUid(uid);
        orders.setAid(aid);
        orders.setOcount(sum1);
        orders.setOstate(1);
        orders.setOtime(new Date(System.currentTimeMillis()));
        orders.setAddress(address);
        orders.setOid(RandomUtils.createOrderId());
        orderDao.add(orders);
        List<Cart> carts = cartDao.show(uid);
        itermDao.addIterm(carts, orders.getOid());
        cartDao.clear(uid);
        return 1;
    }

    @Override
    public Orders detail(String oid) throws SQLException {
        Orders orders = orderDao.detail(oid);
        List<Item> items = itermDao.detail(oid);
        orders.setItems(items);
        return orders;
    }

    @Override
    public boolean modifyState(String oid, int state) throws SQLException {
        if (orderDao.modifyState(oid,state) == 1){
            return true;
        }
        return false;
    }
}

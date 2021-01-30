package com.qfedu.myshop.service.impl;

import com.qfedu.myshop.dao.CartDao;
import com.qfedu.myshop.dao.ProductDao;
import com.qfedu.myshop.dao.impl.CartDaoImpl;
import com.qfedu.myshop.dao.impl.ProductDaoImpl;
import com.qfedu.myshop.entity.Cart;
import com.qfedu.myshop.entity.Product;
import com.qfedu.myshop.service.CartService;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class CartServiceImpl implements CartService {
    private static CartDao cartDao = new CartDaoImpl();
    private static ProductDao productDao = new ProductDaoImpl();

    @Override
    public List<Cart> show(int uid) throws SQLException {
        return cartDao.show(uid);
    }

    @Override
    public Boolean create(int pid, int uid) throws SQLException {
        Cart cart = cartDao.create(pid,uid);
        if (cart!=null){
            cart.setCnum(cart.getCnum() + 1);
            cartDao.update(cart);
        }else{
            Product product = productDao.getGoodDetailByProductId(pid);
            Cart cart1 = new Cart();
            cart1.setCnum(1);
            cart1.setProduct(product);
            cart1.setUid(uid);
            cart1.setPid(pid);
            cartDao.add(cart1);
        }
        return true;
    }

//    @Override
//    public Boolean addProductToCart(int uid, int pid) throws IllegalAccessException, SQLException, InvocationTargetException {
//        Cart cart = cartDao.create(uid,pid);
//        if (cart!=null){
//            cart.setCnum(cart.getCnum() + 1);
//            cartDao.update(cart);
//        }else{
//            Product product = productDao.getGoodDetailByProductId(pid);
//            Cart cart1 = new Cart();
//            cart1.setCnum(1);
//            cart1.setProduct(product);
//            cart1.setUid(uid);
//            cart1.setPid(pid);
//            cartDao.add(cart1);
//        }
//        return true;
//
//    }


//    @Override
//    public int add(Cart cart) throws SQLException {
//        return cartDao.add(cart);
//    }

    @Override
    public int update(Cart cart) throws SQLException {
        return cartDao.update(cart);
    }

    @Override
    public Cart findCartByCid(int cid) throws SQLException {
        return cartDao.findCartByCid(cid);

    }

    @Override
    public int delete(int cid) throws SQLException {
        return cartDao.delete(cid);
    }

    @Override
    public int clear(int uid) throws SQLException {
        return cartDao.clear(uid);
    }
}

package com.qfedu.myshop.service;

import com.qfedu.myshop.entity.PageBean;
import com.qfedu.myshop.entity.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * 商品相关业务
 */
public interface ProductService {


    /**
     *
     * @param tid   商品类型
     * @param currentPage   当前页
     * @param pageSize     每页大小
     * @return
     */
    PageBean<Product> getProductByTypeAndPage(int tid, int currentPage, int pageSize) throws SQLException;

    /**
     *
     * @param pid
     * @return
     * @throws SQLException
     */
    Product getGoodDetailByProductId(int pid) throws SQLException;
}

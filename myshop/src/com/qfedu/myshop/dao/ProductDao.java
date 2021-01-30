package com.qfedu.myshop.dao;

import com.qfedu.myshop.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {

    /**
     * 按照商品类型  分页查询
     * @param tid
     * @param currentPage
     * @param pageSize
     * @return
     */
    List<Product> getProductByTypeAndPage(int tid, int currentPage, int pageSize) throws SQLException;

    /**
     * 根据商品类型 计算总数
     * @param tid
     * @return
     */
    int getProductCountByType(int tid) throws SQLException;

    /**
     *
     * @param pid
     * @return
     * @throws SQLException
     */
    Product getGoodDetailByProductId(int pid) throws SQLException;
}

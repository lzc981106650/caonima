package com.qfedu.myshop.service.impl;

import com.qfedu.myshop.dao.ProductDao;
import com.qfedu.myshop.dao.impl.ProductDaoImpl;
import com.qfedu.myshop.entity.PageBean;
import com.qfedu.myshop.entity.Product;
import com.qfedu.myshop.service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao = new ProductDaoImpl();

    @Override
    public PageBean<Product> getProductByTypeAndPage(int tid, int currentPage, int pageSize) throws SQLException {

//        1.根据类型 分页查询出来集合
         List<Product> productList =  productDao.getProductByTypeAndPage(tid,currentPage,pageSize);

//         2.根据类型查询总条数
        int count = productDao.getProductCountByType(tid);

//          3.返回结果
        return new PageBean<>(productList,currentPage,pageSize,count);
    }

    @Override
    public Product getGoodDetailByProductId(int pid) throws SQLException {
        return productDao.getGoodDetailByProductId(pid);
    }
}

package com.qfedu.myshop.dao.impl;

import com.qfedu.myshop.dao.ProductDao;
import com.qfedu.myshop.entity.Product;
import com.qfedu.myshop.utils.BaseDao;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class ProductDaoImpl extends BaseDao implements ProductDao {
    /**
     * 按照商品类型  分页查询
     * @param tid
     * @param currentPage
     * @param pageSize
     * @return
     * @throws SQLException
     */
    @Override
    public List<Product> getProductByTypeAndPage(int tid, int currentPage, int pageSize) throws SQLException {

//        SELECT * FROM product WHERE t_id = 1 LIMIT 8,8;
        String sql = "SELECT p_id pid,t_id tid,p_name pname,p_time ptime,p_image pimage,p_price pprice,p_state pstate,p_info pinfo FROM shoping2009.product WHERE t_id = ? LIMIT ?,?";

        return queryBeanList(sql, Product.class, tid, (currentPage - 1) * pageSize, pageSize);
    }

    /**
     *  根据商品类型 计算总数
     * @param tid
     * @return
     * @throws SQLException
     */
    @Override
    public int getProductCountByType(int tid) throws SQLException {

//        SELECT COUNT(*) FROM product WHERE t_id = 1;
        String sql = "SELECT COUNT(*) FROM shoping2009.product WHERE t_id = ?";
        long count = queryRunner.query(sql, new ScalarHandler<>(), tid);
        return (int) count;
    }

    @Override
    public Product getGoodDetailByProductId(int pid) throws SQLException {
        String sql = "SELECT p_id pid,t_id tid,p_name pname,p_time ptime,p_image pimage,p_price pprice,p_state pstate,p_info pinfo FROM shoping2009.product WHERE p_id = ?";
        return queryBean(sql, Product.class, pid);
    }

}

package com.qfedu.myshop.dao.impl;

import com.qfedu.myshop.dao.CartDao;
import com.qfedu.myshop.entity.Cart;
import com.qfedu.myshop.entity.Product;
import com.qfedu.myshop.utils.BaseDao;
import com.qfedu.myshop.utils.JDBCUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CartDaoImpl extends BaseDao implements CartDao {
    private QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSource());

    /**
     * 展示列表
     * @param uid
     * @return
     * @throws SQLException
     */
    @Override
    public List<Cart> show(int uid) throws SQLException {
        String sql = "select p.p_name as pname,p.p_id as pid,p.t_id as tid," +
                "p.p_time as ptime,p.p_image as pimage,p_state as pstate," +
                "p.p_info as pinfo, p.p_price as pprice,c.c_id as cid,c.u_id as uid,c.c_count as ccount," +
                "c.c_num as cnum from shoping2009.product p join shoping2009.cart c on p.p_id = c.p_id where" +
                " c.u_id = ?;";
        List<Map<String, Object>> query = queryRunner.query(sql, new MapListHandler(), uid);
        ArrayList<Cart> carts = new ArrayList<>();

        if (query == null) {
            return null;
        }

        for (Map<String, Object> stringObjectMap : query) {
            Cart cart = new Cart();
            Product product = new Product();

            try {
                BeanUtils.populate(cart,stringObjectMap);
                BeanUtils.populate(product, stringObjectMap);
            } catch(IllegalAccessException e) {
                e.printStackTrace();
            } catch(InvocationTargetException e) {
                e.printStackTrace();
            }

            cart.setProduct(product);
            carts.add(cart);
        }

        return carts;
    }

    /**
     * 创建购物车第一步
     *      查询购物车是否含有商品
     * @param pid
     * @param uid
     * @return
     * @throws SQLException
     */
    @Override
    public Cart create(int pid, int uid) throws SQLException {
        String sql = "select c_id cid, u_id uid, p.p_id pid, c_count ccount, c_num cnum, t_id tid, p_name pname, p_time ptime, p_image pimage, p_price pprice, p_state pstate, p_info pinfo from shoping2009.cart c left join shoping2009.product p on c.p_id = p.p_id where u_id = ? and c.p_id = ?;";

        Map<String, Object> query = queryRunner.query(sql, new MapHandler(), uid, pid);

        if (query == null){
            return null;
        }
        Product product = new Product();
        Cart cart = new Cart();
        try {
            BeanUtils.populate(product, query);
            BeanUtils.populate(cart, query);
            cart.setProduct(product);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return cart;
    }

    /**
     * 创建购物车第二步
     *      购物车里添加商品
     * @param cart
     * @return
     * @throws SQLException
     */
    @Override
    public int add(Cart cart) throws SQLException {
        String sql = "insert into shoping2009.cart( u_id, p_id, c_count, c_num) VALUES (?,?,?,?)";

        return queryRunner.update(sql, cart.getUid(), cart.getPid(), cart.getCcount(), cart.getCnum());

    }

    /**
     * 修改更新
     * @param cart
     * @return
     * @throws SQLException
     */
    @Override
    public int update(Cart cart) throws SQLException {
        String sql = "update shoping2009.cart set c_count = ?, c_num = ? where c_id = ?";
        Object[] parameters = {cart.getCcount(), cart.getCnum(), cart.getCid()};
        return queryRunner.update(sql, parameters);
    }

    /**
     * 通过cid查询信息
     * @param cid
     * @return
     * @throws SQLException
     */
    @Override
    public Cart findCartByCid(int cid) throws SQLException {
        String sql = "select c_id cid, u_id uid, p.p_id pid, c_count ccount, c_num cnum, t_id tid, p_name pname, p_time ptime, p_image pimage, p_price pprice, p_state pstate, p_info pinfo from shoping2009.cart c left join shoping2009.product p on c.p_id = p.p_id where c_id = ?;";

        Map<String, Object> query = queryRunner.query(sql, new MapHandler(), cid);

        if (query == null){
            return null;
        }
        Product product = new Product();
        Cart cart = new Cart();
        try {
            BeanUtils.populate(product, query);
            BeanUtils.populate(cart, query);
            cart.setProduct(product);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return cart;
    }

    /**
     * 通过cid删除
     * @param cid
     * @return
     * @throws SQLException
     */
    @Override
    public int delete(int cid) throws SQLException {
        String sql = "delete from shoping2009.cart where c_id = ?";
        return super.update(sql, cid);
    }

    /**
     * 订单创建完成，清空购物车
     * @param uid
     * @return
     * @throws SQLException
     */
    @Override
    public int clear(int uid) throws SQLException {
        String sql = "delete from shoping2009.cart where u_id = ?";
        return queryRunner.update(sql, uid);
    }
}

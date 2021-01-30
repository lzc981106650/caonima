package com.qfedu.myshop.dao.impl;

import com.qfedu.myshop.dao.OrderDao;
import com.qfedu.myshop.entity.Address;
import com.qfedu.myshop.entity.Orders;
import com.qfedu.myshop.utils.BaseDao;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl extends BaseDao implements OrderDao {

    /**
     * 订单列表展示
     * @param uid
     * @return
     * @throws SQLException
     */
    @Override
    public List<Orders> show(int uid) throws SQLException {
        String sql = "select o.o_id as oid,o.u_id as uid,o.a_id as aid,o.o_count as ocount,o.o_time as otime,o_state as ostate,a.a_name as aname,a.a_phone as aphone,a.a_detail as adetail,a.a_state as astate from shoping2009.address a join shoping2009.orders o on a.a_id = o.a_id where o.u_id = ?;";
        List<Map<String, Object>> query = queryRunner.query(sql, new MapListHandler(), uid);
        List<Orders> ordersList = new ArrayList<>();
        if (query == null) {
            return null;
        }


        for (Map<String, Object> stringObjectMap : query) {
            Orders orders = new Orders();
            Address address = new Address();
            try {
                BeanUtils.populate(orders, stringObjectMap);
                BeanUtils.populate(address, stringObjectMap);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            orders.setAddress(address);
            ordersList.add(orders);

        }

        return ordersList;
    }

    /**
     * 订单添加
     * @param orders
     * @return
     * @throws SQLException
     */
    @Override
    public int add(Orders orders) throws SQLException {
        String sql = "insert into shoping2009.orders(o_id, u_id, a_id, o_count, o_time, o_state) VALUES (?,?,?,?,?,?)";

//        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
//        String datestr= formatter.format(orders.getOtime());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String datestr = formatter.format(orders.getOtime());
        return queryRunner.update(sql,orders.getOid(),orders.getUid(),orders.getAid(),orders.getOcount(),datestr,orders.getOstate());
    }

    /**
     * 订单详情查询
     * @param oid
     * @return
     * @throws SQLException
     */
    @Override
    public Orders detail(String oid) throws SQLException {
        String sql = "select o.o_id as oid,o.u_id as uid,o.a_id as aid,o.o_count as ocount,o.o_time as otime,o_state as ostate,a.a_name as aname,a.a_phone as aphone,a.a_detail as adetail,a.a_state as astate from shoping2009.address a join shoping2009.orders o on a.a_id = o.a_id where o.o_id = ?";
        Map<String, Object> query = queryRunner.query(sql, new MapHandler(), oid);

        Orders orders = new Orders();
        Address address = new Address();

        try {
            BeanUtils.populate(orders, query);
            BeanUtils.populate(address, query);
        } catch(IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        orders.setAddress(address);
        return orders;
    }

    /**
     * 支付完成，修改状态
     * @param oid
     * @param state
     * @return
     * @throws SQLException
     */
    @Override
    public int modifyState(String oid,int state) throws SQLException {
        String sql = "update orders set o_state = ? where o_id = ?";
        return queryRunner.execute(sql,state,oid);
    }

}

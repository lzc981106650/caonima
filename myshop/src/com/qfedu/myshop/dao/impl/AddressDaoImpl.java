package com.qfedu.myshop.dao.impl;

import com.qfedu.myshop.dao.AddressDao;
import com.qfedu.myshop.entity.Address;
import com.qfedu.myshop.utils.BaseDao;

import java.sql.SQLException;
import java.util.List;

public class AddressDaoImpl extends BaseDao implements AddressDao {
    /**
     * 所有地址
     * @param uid
     * @return
     * @throws SQLException
     */
    @Override
    public List<Address> findAllAddress(int uid) throws SQLException {
        String sql = "select a_id as aid, u_id as uid,a_name as aname,a_phone " +
                "as aphone,a_detail as adetail ,a_state as astate from " +
                "shoping2009.address where u_id = ? order by a_state desc";
        return queryBeanList(sql, Address.class, uid);
    }

    /**
     * 添加地址
     * @param address
     * @return
     * @throws SQLException
     */
    @Override
    public int add(Address address) throws SQLException {
        String sql = "insert into shoping2009.address (u_id,a_name,a_phone,a_detail,a_state) value(?,?,?,?,?)";
        Object[] parameters = {address.getUid(), address.getAname(), address.getAphone(), address.getAdetail(), address.getAstate()};
        return super.update(sql, parameters);
    }

    /**
     * 删除地址
     * @param aid
     * @return
     * @throws SQLException
     */
    @Override
    public int delete(int aid) throws SQLException {
        String sql = "delete from address where a_id = ?";
        return super.update(sql, aid);
    }

    /**
     * 更改地址
     * @param address
     * @return
     * @throws SQLException
     */
    @Override
    public int update(Address address) throws SQLException {
        String sql = "update shoping2009.address set a_name = ?, a_phone = ?, a_detail = ? where a_id = ?";
        Object[] parameters = {address.getAname(), address.getAphone(), address.getAdetail(), address.getAid()};
        return super.update(sql, parameters);
    }

    /**
     * 设置默认地址第一步
     *      所有地址修改为非默认地址
     * @param uid
     * @return
     * @throws SQLException
     */
    @Override
    public int setDefaultOne(int uid) throws SQLException {
        String sql = "update shoping2009.address set a_state = 0 where u_id = ?";
        return update(sql, uid);
    }

    /**
     * 设置默认地址第二步
     *      根据aid设置默认地址
     * @param aid
     * @return
     * @throws SQLException
     */
    @Override
    public int setDefaultTwo(int aid) throws SQLException {
        String sql = "update shoping2009.address set a_state = 1 where a_id = ?";
        return update(sql, aid);
    }

    /**
     * 通过地址id找到该id地址的所有信息
     * @param aid
     * @return
     * @throws SQLException
     */
    @Override
    public Address findAddressByAid(int aid) throws SQLException {
        String sql = "select a_id as aid, u_id as uid,a_name as aname,a_phone as aphone,a_detail as adetail ,a_state as astate from shoping2009.address where a_id = ?";
        return queryBean(sql, Address.class, aid);
    }

}

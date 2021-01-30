package com.qfedu.myshop.service.impl;

import com.qfedu.myshop.dao.AddressDao;
import com.qfedu.myshop.dao.impl.AddressDaoImpl;
import com.qfedu.myshop.entity.Address;
import com.qfedu.myshop.service.AddressService;

import java.sql.SQLException;
import java.util.List;

public class AddressServiceImpl implements AddressService {
    private static AddressDao addressDao = new AddressDaoImpl();

    @Override
    public List<Address> findAllAddress(int uid) throws SQLException {
        return addressDao.findAllAddress(uid);
    }

    @Override
    public int add(Address address) throws SQLException {
        return addressDao.add(address);
    }

    @Override
    public int delete(int aid) throws SQLException {
        return addressDao.delete(aid);
    }

    @Override
    public int update(Address address) throws SQLException {
        return addressDao.update(address);
    }

    @Override
    public int setDefaultOne(int uid) throws SQLException {
        return addressDao.setDefaultOne(uid);
    }

    @Override
    public int setDefaultTwo(int aid) throws SQLException {
        return addressDao.setDefaultTwo(aid);
    }


}

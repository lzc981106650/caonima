package com.qfedu.myshop.service.impl;

import com.qfedu.myshop.dao.TypeDao;
import com.qfedu.myshop.dao.impl.TypeDaoImpl;
import com.qfedu.myshop.entity.Type;
import com.qfedu.myshop.service.TypeService;

import java.sql.SQLException;
import java.util.List;

public class TypeServiceImpl implements TypeService {


    private TypeDao typeDao = new TypeDaoImpl();


    @Override
    public List<Type> getAllType() throws SQLException {
        return typeDao.getAllType();
    }


}

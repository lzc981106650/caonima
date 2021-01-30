package com.qfedu.myshop.dao.impl;

import com.qfedu.myshop.dao.TypeDao;
import com.qfedu.myshop.entity.Type;
import com.qfedu.myshop.utils.BaseDao;

import java.sql.SQLException;
import java.util.List;

public class TypeDaoImpl extends BaseDao implements TypeDao {
    @Override
    public List<Type> getAllType() throws SQLException {


        // 如何解决 实体类属性 和 数据表字段 不一致问题
//        select t_id tid,t_name tname,t_info tInfo from type
//        select t_id as tid,t_name as tname,t_info as tInfo from type
        String sql = "select t_id as tid,t_name as tname,t_info as tInfo from shoping2009.type";
        return queryBeanList(sql, Type.class);
    }
}


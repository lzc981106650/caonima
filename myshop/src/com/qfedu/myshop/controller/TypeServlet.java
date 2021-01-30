package com.qfedu.myshop.controller;

import com.alibaba.fastjson.JSON;
import com.qfedu.myshop.entity.Type;
import com.qfedu.myshop.service.TypeService;
import com.qfedu.myshop.service.impl.TypeServiceImpl;
import com.qfedu.myshop.utils.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 * 商品类型 相关
 */
@WebServlet("/type.do")
public class TypeServlet extends BaseServlet {
    private TypeService typeService;

    @Override
    public void init() throws ServletException {
        typeService = new TypeServiceImpl();
    }

    public String getTypeList(HttpServletRequest req, HttpServletResponse resp) throws SQLException {

        // 去数据库获取
//         1.去service  2.dao获取
        List<Type> typeList = typeService.getAllType();

        return JSON.toJSONString(typeList);
    }

}

package com.qfedu.myshop.controller;

import com.qfedu.myshop.entity.Address;
import com.qfedu.myshop.entity.User;
import com.qfedu.myshop.service.AddressService;
import com.qfedu.myshop.service.impl.AddressServiceImpl;
import com.qfedu.myshop.utils.BaseServlet;
import com.qfedu.myshop.utils.Constants;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/address.do")
public class AddressServlet extends BaseServlet {
    private AddressService addressService = null;

    /**
     * 初始化
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        addressService = new AddressServiceImpl();
    }

    /**
     * 显示地址 相关的信息
     * 购物车连接
     * @param req
     * @param resp
     * @return
     */
    public String show(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("loginUser");
        int uid = user.getUid();

        List<Address> addressList = null;
        try {
            addressList = addressService.findAllAddress(uid);
            req.setAttribute("list", addressList);
            return Constants.FORWARD + "/self_info.jsp";
        } catch(SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("msg", "服务器异常");
        return Constants.FORWARD + "/login.jsp";
    }

    /**
     * 添加新地址
     *
     * @param req
     * @param resp
     * @return
     */
    public String add(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, String[]> parameterMap = req.getParameterMap();

        Address address = new Address();

        try {
            BeanUtils.populate(address, parameterMap);
            addressService.add(address);
            return Constants.FORWARD + "/address.do?method=show";
        } catch(IllegalAccessException | InvocationTargetException | SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("msg", "服务器异常");
        return Constants.FORWARD + "/login.jsp";
    }

    /**
     * 删除地址
     * @param req
     * @param resp
     * @return
     */
    public String delete(HttpServletRequest req, HttpServletResponse resp) {
        String aid1 = req.getParameter("aid");
        int aid = Integer.valueOf(aid1);
        try {
            addressService.delete(aid);
            return Constants.FORWARD + "/address.do?method=show";
        } catch(SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("msg", "服务器异常");
        return Constants.FORWARD + "/login.jsp";
    }

    /**
     * 修改地址
     * @param req
     * @param resp
     * @return
     */
    public String update(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, String[]> parameterMap = req.getParameterMap();
        Address address = new Address();
        try {
            BeanUtils.populate(address, parameterMap);
            addressService.update(address);
            return Constants.FORWARD + "/address.do?method=show";
        } catch(IllegalAccessException | InvocationTargetException | SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("msg", "服务器异常");
        return Constants.FORWARD + "/login.jsp";
    }

    /**
     * 设置默认地址
     *
     * @param req
     * @param ersp
     * @return
     */
    public String setDefault(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("loginUser");
        int uid = user.getUid();
        try {
            addressService.setDefaultOne(uid);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        String aid1 = req.getParameter("aid");
        int aid = Integer.valueOf(aid1);
        try {
            addressService.setDefaultTwo(aid);
            return Constants.FORWARD + "/address.do?method=show";
        } catch(SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("msg", "服务器异常");
        return Constants.FORWARD + "/login.jsp";
    }
}

package com.qfedu.myshop.controller;

import com.qfedu.myshop.entity.Cart;
import com.qfedu.myshop.entity.User;
import com.qfedu.myshop.service.CartService;
import com.qfedu.myshop.service.impl.CartServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import utils.BaseServlet;
import utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/cart.do")
public class CartServlet extends BaseServlet {
    private CartService cartService = null;

    /**
     * 初始化
     *
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        cartService = new CartServiceImpl();
    }

    /**
     * 购物车展示列表
     *
     * @param req
     * @param resp
     * @return
     */
    public String show(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession httpSession = req.getSession();
        User user = (User) httpSession.getAttribute("loginUser");
        int uid = user.getUid();

//        String uid1 = req.getParameter("uid");
//        int uid = Integer.valueOf(uid1);
        List<Cart> cartList = null;
        try {
            cartList = cartService.show(uid);
            req.setAttribute("list", cartList);
            return utils.Constants.FORWARD + "/cart.jsp";
        } catch(SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("msg", "服务器异常");
        return utils.Constants.FORWARD + "/login.jsp";
    }

    /**
     * 创建购物车
     *
     * @param req
     * @param resp
     * @return
     */
    public String create(HttpServletRequest req, HttpServletResponse resp) {
        int pid = Integer.parseInt(req.getParameter("pid"));
        User user = (User) req.getSession().getAttribute("loginUser");
//        String uid1 = req.getParameter("uid");
//        int uid = Integer.valueOf(uid1);


        try {
            cartService.create(pid, user.getUid());

            return Constants.FORWARD + "cartSuccess.jsp";
        } catch(SQLException e) {
            e.printStackTrace();
        }


        req.setAttribute("msg", "服务器异常");
        return Constants.FORWARD + "/login.jsp";
    }

    /**
     * 更新购物车
     *
     * @param req
     * @param resp
     * @return
     * @throws SQLException
     */
    public String update(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        int cid = Integer.parseInt(req.getParameter("cid"));
        int cnum = Integer.parseInt(req.getParameter("cnum"));
        User user = (User) req.getSession().getAttribute("loginUser");

        Cart cart = cartService.findCartByCid(cid);
        cart.setCnum(cnum);
        cartService.update(cart);
        return com.qfedu.myshop.utils.Constants.FORWARD + "/cart.do?method=show&uid=" + user.getUid();

    }

    /**
     * 删除购物车一行
     *
     * @param req
     * @param resp
     * @return
     */
    public String delete(HttpServletRequest req, HttpServletResponse resp) {
        int cid = Integer.parseInt(req.getParameter("cid"));
        try {
            cartService.delete(cid);
            return Constants.FORWARD + "/cart.do?method=show";
        } catch(SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("msg", "服务器异常");
        return com.qfedu.myshop.utils.Constants.FORWARD + "/login.jsp";
    }

    /**
     * 清除购物车，删除所有
     * @param req
     * @param resp
     * @return
     */
    public String clear(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession httpSession = req.getSession();
        User user = (User) httpSession.getAttribute("loginUser");
        int uid = user.getUid();

        try {
            cartService.clear(uid);
            return Constants.FORWARD + "/cart.do?method=show&uid=" + user.getUid();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("msg", "服务器异常");
        return com.qfedu.myshop.utils.Constants.FORWARD + "/login.jsp";
    }

}

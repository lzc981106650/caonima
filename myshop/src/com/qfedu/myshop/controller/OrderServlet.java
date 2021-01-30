package com.qfedu.myshop.controller;

import com.qfedu.myshop.entity.Address;
import com.qfedu.myshop.entity.Cart;
import com.qfedu.myshop.entity.Orders;
import com.qfedu.myshop.entity.User;
import com.qfedu.myshop.service.AddressService;
import com.qfedu.myshop.service.CartService;
import com.qfedu.myshop.service.OrderService;
import com.qfedu.myshop.service.impl.AddressServiceImpl;
import com.qfedu.myshop.service.impl.CartServiceImpl;
import com.qfedu.myshop.service.impl.OrderServiceImpl;
import utils.BaseServlet;
import utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/order.do")
public class OrderServlet extends BaseServlet {
    private OrderService orderService;
    private AddressService addressService;
    private CartService cartService;

    @Override
    public void init() throws ServletException {
        orderService = new OrderServiceImpl();
        addressService = new AddressServiceImpl();
        cartService = new CartServiceImpl();
    }

    /**
     * 用户地址选择
     *
     * @param req
     * @param resp
     * @return
     */
    public String preView(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession httpSession = req.getSession();
        User user = (User) httpSession.getAttribute("loginUser");
        int uid = user.getUid();

        try {
            List<Cart> cartList = cartService.show(uid);
            List<Address> addressList = addressService.findAllAddress(uid);

            req.setAttribute("cartList", cartList);
            req.setAttribute("addressList", addressList);

            return Constants.FORWARD + "order.jsp";
        } catch(SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("msg", "服务器异常");
        return com.qfedu.myshop.utils.Constants.FORWARD + "/login.jsp";
    }

    /**
     * 订单列表展示
     *
     * @param req
     * @param resp
     * @return
     */
    public String show(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession httpSession = req.getSession();
        User user = (User) httpSession.getAttribute("loginUser");
        int uid = user.getUid();

        try {
            List<Orders> ordersList = orderService.show(uid);
            req.setAttribute("list", ordersList);
            httpSession.setAttribute("ordersList", ordersList);
            return Constants.FORWARD + "/orderList.jsp";
        } catch(SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("msg", "服务器异常");
        return com.qfedu.myshop.utils.Constants.FORWARD + "/login.jsp";
    }

    /**
     * 创建订单
     * sum订单总价格  cid sum price
     *
     * @param req
     * @param resp
     * @return
     */
    public String create(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        int aid = Integer.parseInt(req.getParameter("aid"));
//        int price = Integer.parseInt(req.getParameter("price"));
        int sum = Integer.parseInt(req.getParameter("sum"));
        // 获取uid
//        HttpSession httpSession = req.getSession();
//        User user = (User) httpSession.getAttribute("loginUser");
//        int uid = user.getUid();
        int uid = Integer.parseInt(req.getParameter("uid"));

        orderService.create(uid, aid, sum);
        return Constants.FORWARD + "/order.do?method=show";
    }

    /**
     * 订单详情页
     * @param req
     * @param resp
     * @return
     * @throws SQLException
     */
    public String detail(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        String oid = req.getParameter("oid");
        Orders orders = orderService.detail(oid);
        req.setAttribute("order", orders);
        return Constants.FORWARD+"/orderDetail.jsp";
    }

    /**
     * 修改用户订单状态
     * @param req
     * @param resp
     * @return
     * @throws SQLException
     */
    public String changeStatus(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        String oid = req.getParameter("oid");
        if(orderService.modifyState(oid,4)){
            return Constants.REDIRECT + "/order.do?method=show";
        }
        return null;
    }


}

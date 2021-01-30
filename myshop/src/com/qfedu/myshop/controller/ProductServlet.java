package com.qfedu.myshop.controller;

import com.qfedu.myshop.entity.PageBean;
import com.qfedu.myshop.entity.Product;
import com.qfedu.myshop.service.ProductService;
import com.qfedu.myshop.service.impl.ProductServiceImpl;
import com.qfedu.myshop.utils.BaseServlet;
import com.qfedu.myshop.utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@WebServlet("/product.do")
public class ProductServlet extends BaseServlet {

    private ProductService productService = null;

    @Override
    public void init() throws ServletException {
        productService = new ProductServiceImpl();
    }

    /**
     * 按照类型 分页差选所有商品
     *
     * @param req
     * @param resp
     * @return
     */
    public String getProductByType(HttpServletRequest req, HttpServletResponse resp) {

        // 获取参数  类型  当前页   页的大小
        String tidStr = req.getParameter("tid");
        String currentPageStr = req.getParameter("currentPage");
        String pageSizeStr = req.getParameter("pageSize");

        int tid = 0;
        if(tidStr != null) {
            tid = Integer.valueOf(tidStr);
        }

        int currentPage = 1;
        if(currentPageStr != null) {
            currentPage = Integer.valueOf(currentPageStr);
        }

        int pageSize = 8;
        if(pageSizeStr != null) {
            pageSize = Integer.valueOf(pageSizeStr);
        }


        try {
            // 分页查询商品
            // 将差选结果  封装PageBean
            PageBean<Product> pageBean = productService.getProductByTypeAndPage(tid, currentPage, pageSize);
            req.setAttribute("pageBean", pageBean);


            //查询成功之后转发
            return Constants.FORWARD + "/goodsList.jsp";
        } catch(SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("msg", "服务器错误");
        return Constants.FORWARD + "/message.jsp";
    }

    /**
     * 商品详情
     * @param req
     * @param resp
     * @return
     */
    public String detail(HttpServletRequest req, HttpServletResponse resp) {
        String pid = req.getParameter("pid");

        Product product = null;

        int productid = Integer.valueOf(pid);

        try {
            product = productService.getGoodDetailByProductId(productid);
        } catch(SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("product", product);

        return Constants.FORWARD + "/goodsDetail.jsp";
    }
}

package com.qfedu.myshop.controller;

import com.qfedu.myshop.utils.BaseServlet;
import com.qfedu.myshop.utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/TestServlet")
public class TestServlet extends BaseServlet {




    public void test1(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        响应前端数据有几种方式：
//        1.转发 （jsp  servlet）

//        req.setAttribute("msg", "TestServlet--test1");
//        req.getRequestDispatcher("/message.jsp").forward(req,resp);


//        2.重定向  （jsp  servlet） 不可以使用req 传递参数
//        resp.sendRedirect(req.getServletContext().getContextPath()+"/message.jsp");

//        3.直接响应( 响应json)

          resp.getWriter().write("TestServlet--test1");

    }

    /**
     * 在锋迷在线项目中 同一使用当前格式 跳转界面，返回数据
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String test2(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        响应前端数据有几种方式：
//        1.转发 （jsp  servlet）
//        req.setAttribute("msg", "TestServlet--test2");
        // springmvc 原理就是类似逻辑
//        return Constants.FORWARD +"/message.jsp";

//        2.重定向  （jsp  servlet） 不可以使用req 传递参数
//         return Constants.REDIRECT + "/message.jsp";

//        3.直接响应( 响应json,字符串)
          return  "TestServlet--test2";

    }

}

package com.qfedu.myshop.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * BaseServlet 提供给所有的Servlet程序，完成对应的方法处理过程
 */
public abstract class BaseServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        // exam: method == listStudent
        String method = req.getParameter("method");

        if (method==null){// 如果传 method 则直接跳转首页
            System.out.println("错误：没有传递method方法名，跳转首页");
            method = "redirectIndex";
        }

        Method targetMethod = null;
        try {
            // public void listStudent(HttpServletRequest req, HttpServletResponse resp)
            targetMethod = this.getClass().getMethod(method, HttpServletRequest.class, HttpServletResponse.class);

            // 执行目标
            Object result =   targetMethod.invoke(this, req, resp);

            if (result != null) {
                //转发 重定向  返回字符
                String str = (String) result;
                if (str.startsWith(Constants.FORWARD)) {
                    //转发
                    String path = str.substring(str.indexOf(Constants.FLAG) + 1);
                    req.getRequestDispatcher(path).forward(req,resp);
                }else if (str.startsWith(Constants.REDIRECT)){
                    //重定向
                    String path = str.substring(str.indexOf(Constants.FLAG) + 1);
                    resp.sendRedirect(path);
                }else{  // 返回响应数据
                    resp.getWriter().println(str);
                }
            }

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    /**
     * 重定向到 首页
     * @param req
     * @param resp
     * @return
     */
    public String  redirectIndex(HttpServletRequest req, HttpServletResponse resp){

        return Constants.REDIRECT+"/index.jsp";
    }
}

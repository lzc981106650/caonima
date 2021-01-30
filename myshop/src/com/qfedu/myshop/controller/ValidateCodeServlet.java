package com.qfedu.myshop.controller;

import cn.dsna.util.images.ValidateCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/validateCodeServlet.do")
public class ValidateCodeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //产生验证码
//        int width, 验证码宽度
//        int height, 验证码高度
//        int codeCount, 验证码有几位
//        int lineCount， 有几条模糊线段
        ValidateCode validateCode = new ValidateCode(100,30,4,30);
        // 得到随机生成的验证码
        String code =  validateCode.getCode();

        // 将 验证码保存到 session
        req.getSession().setAttribute("code",code);

        // 将生成的验证码图片返回给前端
        validateCode.write(resp.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

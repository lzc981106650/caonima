package com.qfedu.myshop.filter;

import com.qfedu.myshop.entity.User;
import com.qfedu.myshop.service.UserService;
import com.qfedu.myshop.service.impl.UserServiceImpl;
import com.qfedu.myshop.utils.Base64Utils;
import com.qfedu.myshop.utils.Constants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter("/login.jsp")
public class AutoLoginFilter implements Filter {
    private UserService userService = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userService = new UserServiceImpl();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        Cookie[] cookies = httpServletRequest.getCookies();

        for(Cookie cookie : cookies) {
            if(cookie.getName().equals("user")) {
                // 获取用户信息
                // 解码用户名密码
                String userMessage = Base64Utils.decode(cookie.getValue());

                String username = userMessage.split(Constants.FLAG)[0];
                String password = userMessage.split(Constants.FLAG)[1];

                try {
                    User user = userService.findUserByUserName(username);
                    // 自动登录
                    if(password.equals(user.getUpassword())) {
                        // 验证 密码md5 hash 之后的用户密码
                        httpServletRequest.getSession().setAttribute("loginUser", user);
                        httpServletRequest.getRequestDispatcher("/index.jsp").forward(servletRequest, servletResponse);
                        return;
                    }

                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        // 放行 让用户输入密码登录
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}

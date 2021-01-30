package com.qfedu.myshop.controller;

import com.qfedu.myshop.entity.User;
import com.qfedu.myshop.service.UserService;
import com.qfedu.myshop.service.impl.UserServiceImpl;
import com.qfedu.myshop.utils.Base64Utils;
import com.qfedu.myshop.utils.BaseServlet;
import com.qfedu.myshop.utils.Constants;
import com.qfedu.myshop.utils.MD5Utils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@WebServlet("/user.do")
public class UserServlet extends BaseServlet {
    private UserService userService = null;

    /**
     * 初始化
     *
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }

    /**
     * 检查用户名是否重复
     *
     * @param req
     * @param resp
     * @return
     */
    public String checkUserName(HttpServletRequest req, HttpServletResponse resp) {
        // 去service dao 根据用户名查询，是否含有当前用户
        String username = req.getParameter("username");
        try {
            User user = userService.findUserByUserName(username);
            if(user != null) {
                // 用户存在
                return "1";
            } else {
                // 不存在
                return "0";
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("msg", "服务器错误");
        return Constants.FORWARD + "/message.jsp";
    }

    /**
     * 注册用户
     *
     * @param req
     * @param resp
     * @return
     */
    public String register(HttpServletRequest req, HttpServletResponse resp) {

        try {
            //接收参数
            User user = new User();
            // 装配user实体类
            BeanUtils.populate(user, req.getParameterMap());

            // 	1.判断两次密码是否一致
            String upassword2 = req.getParameter("upassword2");

            if(! user.getUpassword().equals(upassword2)) {
                req.setAttribute("msg", "两次密码不一致，请重新注册");
                return Constants.FORWARD + "/message.jsp";
            }

            // 2.在数据库添加user
            userService.registerUser(user);
            return Constants.FORWARD + "/registerSuccess.jsp";

        } catch(IllegalAccessException e) {
            e.printStackTrace();
        } catch(InvocationTargetException e) {
            e.printStackTrace();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("msg", "注册失败，请重新注册");
        return Constants.FORWARD + "/message.jsp";

    }

    /**
     * 激活用户
     *
     * @param req
     * @param resp
     * @return
     */
    public String active(HttpServletRequest req, HttpServletResponse resp) {

        // 获取的编码的激活码
        String code = req.getParameter("c");

        try {
            boolean result = userService.active(code);
            if(result) {
                req.setAttribute("msg", "激活成功，请登录！！！");
                return Constants.FORWARD + "/message.jsp";
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("msg", "激活失败，请登录！！！");
        return Constants.FORWARD + "/message.jsp";
    }

    /**
     * 用户登录
     *
     * @param req
     * @param resp
     * @return a.验证码 session  比对
     * <p>
     * ​    b.查看用户是否激活，未激活 提示激活
     * <p>
     * c.查询用户的真正密码，比对
     * <p>
     * ​    d.跳转首页
     */
    public String login(HttpServletRequest req, HttpServletResponse resp) {

        String username = req.getParameter("username");
        // 未加密密码
        String password = req.getParameter("password");
        // 获取验证码
        String code = req.getParameter("code");
        // 两周内自动登录
        String auto = req.getParameter("auto");

        HttpSession httpSession = req.getSession();

        //a.验证码 session  比对
        String sessionCode = (String) httpSession.getAttribute("code");
        if(! sessionCode.equalsIgnoreCase(code)) {// 验证码不匹配
            req.setAttribute("msg", "验证码错误");
            return Constants.FORWARD + "/login.jsp";
        }


        try {
            User user = userService.findUserByUserName(username);

            if(user != null) {// 存在用户

//                b.查看用户是否激活，未激活 提示激活
                if(user.getUstatus().equals(Constants.USER_ACTIVE + "")) {// 已激活

//                     c.查询用户的真正密码，比对
                    if(user.getUpassword().equals(MD5Utils.md5(password))) {// 密码正确


                        if("on".equals(auto)) {// 如果用户选择自动登录，则将用户的用户名 和 密码 加密保存到cookie 中

                            // 对用户名 密码冲编码
                            String userMessage = Base64Utils.encode(user.getUsername() + Constants.FLAG + user.getUpassword());

                            Cookie cookie = new Cookie("user", userMessage);
                            // 设置时间
                            cookie.setMaxAge(60 * 60 * 24 * 14);
                            cookie.setPath("/");

                            // 将cookie 保存到浏览器中
                            resp.addCookie(cookie);
                        } else {  // 清空cookie
                            Cookie cookie = new Cookie("user", "");
                            // 设置时间
                            cookie.setMaxAge(0);
                            cookie.setPath("/");
                            // 将cookie 保存到浏览器中
                            resp.addCookie(cookie);
                        }
                        // loginUser 设置用户 密码正确 用户登录
                        httpSession.setAttribute("loginUser", user);

                        return Constants.FORWARD + "/index.jsp";
                    } else {
                        req.setAttribute("msg", "密码错误");
                        return Constants.FORWARD + "/login.jsp";
                    }
                } else {
                    req.setAttribute("msg", "该用未激活，请登录邮箱激活");
                    return Constants.FORWARD + "/login.jsp";
                }
            } else {
                req.setAttribute("msg", "该用户不存在");
                return Constants.FORWARD + "/login.jsp";
            }


        } catch(SQLException e) {
            e.printStackTrace();
        }


        req.setAttribute("msg", "服务器异常");
        return Constants.FORWARD + "/login.jsp";

    }

    /**
     * 注销用户登录
     *
     * @param req
     * @param resp
     * @return
     */
    public String logOut(HttpServletRequest req, HttpServletResponse resp) {

        // session 失效
        HttpSession httpSession = req.getSession();
        httpSession.invalidate();

        // 清空cookie
        Cookie cookie = new Cookie("user", "");
        // 设置时间
        cookie.setMaxAge(0);
        cookie.setPath("/");
        // 将cookie 保存到浏览器中
        resp.addCookie(cookie);

        return Constants.REDIRECT + "/login.jsp";
    }

    public String getAddress(HttpServletRequest req, HttpServletResponse resp) {

        return Constants.FORWARD + "/self_info.jsp";
    }
}

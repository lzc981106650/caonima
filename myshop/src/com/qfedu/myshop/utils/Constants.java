package com.qfedu.myshop.utils;

/**
 * 存放静态常量
 *
 * 一个程序的所有公用的静态常量放置在 同一个类或者多个类中 接口
 *
 */
public class Constants {


    /**
     * 默认方法
     */
    public static final String INDEX = "index";

    /**
     * 转发开始标记
     */
    public static final String  FORWARD = "forward:";


    /**
     * 重定向路径
     */
    public static final String  REDIRECT = "redirect:";


    /**
     * 用户的状态
     */
    public static final int USER_ACTIVE = 1;

    public static final int USER_NOT_ACTIVE = 0;


    /**
     * 用户模块激活状态
     */
    public static final int ACTIVE_FAIL = 0;
    public static final int ACTIVE_SUCCESS = 1;
    public static final int ACTIVE_ALREADY = 2;


    /**
     * 用户分类
     * 普通用户 0
     * 管理员 1
     */
    public static final int ROLE_CUSTOMER = 0;
    public static final int ROLE_ADMIN = 1;


    /**
     * 自动登录cookie名
     */
    public static final String AUTO_NAME = "autoUser";

    /**
     * 分割符
     */
    public static final String FLAG = ":";


    private static final float PAI = 3.1415f;

    /**
     * 登陆状态 未激活
     */
    public static  final int USER_LOGIN_STATUS_NOACTIVE = 0;

    /**
     *登陆状态 用户名 或者 密码错误
     */
    public static  final int USER_LOGIN_STATUS_ERROR = 1;

    /**
     * 登陆状态 登陆成功
     */
    public static  final int USER_LOGIN_STATUS_SUCCESS = 2;

}

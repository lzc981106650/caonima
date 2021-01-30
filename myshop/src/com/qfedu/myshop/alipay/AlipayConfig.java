package com.qfedu.myshop.alipay;

import com.alipay.api.AlipayClient;

public class AlipayConfig {
        /**
         * 应用号
         */
        public static String APP_ID = "2016102500758736";
        /**
         * 商户的私钥
         */
        public static String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCH/ex7N/jriRzuijIfVqZEt+uZ70K9J8JYyENj9xyPwYgehsjNSPFYrjN/JBYAhi7embc3UQM9x+l3GF4EFzhP705HrvtGX0IAte2tokbwY4o1PJnemaka7dIUoUsY1SRB9j5tI5ii4TQeBnBJYAI+Ljcda8zec5ci54UJVWf0fmS3Khgc7BBuo4YJjULelWRjXE8atWI/3Xbpk5jDp8qjZO72/TBUk4gv12YwWj4cPNepMRlsw1/2sINK8lB+0cDms+6L2tLw8ut3M0Ji0aAqKeiik5/TaHjB8HndVEs2TFej2nE1Wt5JeEHm3ZpOdRAp2Y8UuAtmskX7tJZZuQAhAgMBAAECggEAabo5CPNTS4umOq6mmUa8reBwA5XIxVcL+gp4mleclQdZ1KHlq/Rx7whAcTCVCoNIHThVAWSyqG2sTTNcpMGIvlzG92RLZ0LYBcVrq/rmPC6nXQxodG/y0UTkd7WOtRfAwKe5kRCDst5zroRC+Ej0dXNo1i7hcHFdp31oKQGFeMdC17nKwW5kLWJEr0/4XbW1CaxQA3p92hGIiMlBo1Di5qGLbngDOnQspkfeuPtW0wZz2A+cLPCOQF/45u6rkaTyEnN/F2Dzf3zA5xf+22baE4gYThumixqMGfIwvjLlWj9aOd7nNx+oHuV7cjxLvhVK52ZNw68VXP+5V/xtB9sFgQKBgQDmdDr+iLjSzd2hd0Bu8prLMyf+atvdONj8XJ8engv7f501nCVANdAvbNyLZQFu0tBX7jqpEHuUXzJLNifJ//D662CGbCypLrSMgZ03Ka8pPEVNElxecE5memKYxVAkwThxRnRAkXuy2egL+sZQ3N/AewubNEPCL1tEO4d4GYM3JQKBgQCXERG8OwrltAi56r7ICSuAZCtX4gAE/AHaXPQaa513URjyeLDcNh6DRnlnEcw/9fQAPX9msN3TdxrkPZdDuwwBEisqAi1daCA5E5tmaGpD1HBYaDPGel58nLH16adIWxacUfAh+p4ZJ4vl5WE3EBDTGIz42daEPd21JByIYiyiTQKBgQDYwfZDNsHwtWWC27GlKVdjTOiPKl9GgpPFOjEIEUvf8DYkGIwJCxSXpuMB0a05WdxQ+GgAYNp7wdaCW5utKai1ufJ+PPz83EtB5R/EpNZ2CJR+W4i54xQ6214lp2EsUPkPwl7R5ITewoq5ydQCdt2WI6XVA6G/4d8MHJnc4tJCaQKBgB8E6UBeW0l5Y0VRt2T5xQ3zA5djF5/uYy8EYTc/m7DRXS8Eq0Q8dJ1+DhWk6OzFQxn9hrh+Xr46ObJeFz5YHQnnQie5FLmWVO+DYChyDkfpSjxNATyjCd3Kixw0ox3PXmAwzv8bJ/WAUlCdfg3BZcMCyoeaq1qyHWFBjf5PMl0NAoGBAN3Rkfia8mzeO1twUYLq502msFQw3HbVidvoXfe99gJ3DkrfIsJ+6lVSbkTdWPrC1Mb2HR7YTXz3g559Wi+PoP2cVQE255jOC2I3jA8M18oWml65BtyYUBBTDhI5J/Cys/PumRqcaCZTXeuot0PJcXx5kGaox1iiFfW2kpB59OqR";


        public static final String FORMAT = "JSON";
        /**
         * 编码
         */
        public static String CHARSET = "UTF-8";
        /**
         * 支付宝公钥
         */
        public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAy08cAxWDPggV3gPb+DdtfY80W3a6xR4snySjaGlafy+hRDvx6+LxvE6frvgOXCwG9vXzN3GC5qX3kLRWf9pV+Y4G8IYvZPqJVa4wgEsIXg8tJ05W/hMTJykb3yRTz2CJxoe/ri1KUCRRx4LLlDHA2oLBebxRF6oqjDG4VvZHCwsptOH/VTQkzxiBM8Ox0SA8yYHgMaW++CSWNudT/HWDLtoAZRO7eruPtmDTG73hBqDa0bFddaPaYtD3sW+y+NWkbMbI+y56x6K8xwUq8WsIE3aZf5kpTlIUQu+6gpTZO19qYby2qH4oQpUKZY4w83xnxfigMNja9iQ7rkXMsQJb4QIDAQAB";
        /**
         * 支付宝网关地址
         */
        public static String GATEWAY = "https://openapi.alipaydev.com/gateway.do";
        /**
         * 成功付款回调 ***********************************
         */
        public static final String RETURN_URL = "http:localhost:8080/alipay.do?method=returnUrl";

        /**
         * 前台通知地址
         */
        public static String NOTIFY_URL = "http:localhost:8080/alipay/return.jsp";
        /**
         * 参数类型
         */
        public static String PARAM_TYPE = "json";

        public static String SIGN_TYPE = "RSA2";
        /**
         * 成功标识
         */
        public static final String SUCCESS_REQUEST = "TRADE_SUCCESS";
        /**
         * 交易关闭回调(当该笔订单全部退款完毕,则交易关闭)
         */
        public static final String TRADE_CLOSED = "TRADE_CLOSED";
        /**
         * 收款方账号
         */
        public static final String SELLER_ID = "cboeiw4228@sandbox.com";
        /**
         * 支付宝请求客户端入口
         */
        private volatile static AlipayClient alipayClient = null;


}



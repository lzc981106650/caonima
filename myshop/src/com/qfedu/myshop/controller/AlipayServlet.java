package com.qfedu.myshop.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.qfedu.myshop.alipay.AlipayConfig;
import com.qfedu.myshop.service.OrderService;
import com.qfedu.myshop.service.impl.OrderServiceImpl;
import com.qfedu.myshop.utils.Constants;
import utils.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/alipay.do")
public class AlipayServlet extends BaseServlet {


    /**
     * 调用支付宝 后台进行订单支付
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String pay(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //实例化客户端,填入所需参数
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.GATEWAY, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        //在公共参数中设置回跳和通知地址
        request.setReturnUrl(AlipayConfig.RETURN_URL);
        request.setNotifyUrl(AlipayConfig.NOTIFY_URL);
        //根据订单编号,查询订单相关信息
//        Order order = payService.selectById(orderId);
        //商户订单号，商户网站订单系统中唯一订单号，必填
        String oid = req.getParameter("orderId");
        //付款金额，必填
        String money =  req.getParameter("price");
        //订单名称，必填
        String productName = req.getParameter("productName");
        //商品描述，可空
        String body = "";
        request.setBizContent("{\"out_trade_no\":\""+ oid +"\","
                + "\"total_amount\":\""+ money +"\","
                + "\"subject\":\""+ productName +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        String form = "";
        try {
            form = alipayClient.pageExecute(request).getBody(); // 调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return form;// 直接将完整的表单html输出到页面
    }

    /**
     * 付款成功的回调
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws AlipayApiException
     */
    public String returnUrl(HttpServletRequest request, HttpServletResponse response)
            throws IOException, AlipayApiException, SQLException {
        System.out.println("=================================同步回调=====================================");
        // 获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (String key : requestParams.keySet()) {

            String[] values = (String[]) requestParams.get(key);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("utf-8"), "utf-8");
            params.put(key, valueStr);
        }

        System.out.println(params);//查看参数都有哪些

        // 商户订单号
        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

        // 支付宝交易号
        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

        // 付款金额
        String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

        System.out.println("商户订单号=" + out_trade_no);
        System.out.println("支付宝交易号=" + trade_no);
        System.out.println("付款金额=" + total_amount);

        //支付成功，修复支付状态  修改订单状态
        OrderService ordersService = new OrderServiceImpl();
        if (ordersService.modifyState(out_trade_no, 2)){
            //跳转付款成功页面
            return Constants.REDIRECT + "/order.do?method=show";
        }
        return null;
    }

}

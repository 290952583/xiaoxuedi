package com.xiaoxuedi.servlet;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.xiaoxuedi.config.AlipayProperties;
import com.xiaoxuedi.service.PayService;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@WebServlet(name = "alipayNotify", urlPatterns = "/servlet/alipayNotify")
@Slf4j
public class AlipayNotifyServlet extends HttpServlet {

    private static final long serialVersionUID = 8031133938454140403L;
    @Resource
    private AlipayProperties alipayProperties;
    
    @Autowired
    private PayService payService;
    
    /**
     * 支付宝支付异步回调地址，逻辑处理
     */
    @Override
    
    protected void service(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {
//        HttpServletRequest request = Application.getRequest();
    	log.info("进入支付宝Pay异步通知");
        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        //切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
        //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
        try {
            boolean flag = AlipaySignature.rsaCheckV1(params, alipayProperties.getAlipayPublicKey(), alipayProperties.getCharset(),"RSA2");

            log.info("进入支付宝Pay异步通知，验证状态"+flag);
            String outTradeNo=params.get("out_trade_no").toString();//订单编号
            String orderId=URLDecoder.decode(params.get("passback_params").toString());//订单id
            //更新订单状态
                    /*WAIT_BUYER_PAY	交易创建，等待买家付款
                      TRADE_CLOSED	未付款交易超时关闭，或支付完成后全额退款
                      TRADE_SUCCESS	交易支付成功
                      TRADE_FINISHED	交易结束，不可退款*/
            
            switch (params.get("trade_status").toString())
            {
                case "WAIT_BUYER_PAY"://交易创建，等待买家付款
                {
                	payService.chargeOrdersStatus(outTradeNo, "WAIT");
                    break;
                }
                case "TRADE_CLOSED"://未付款交易超时关闭，或支付完成后全额退款
                {
                	payService.chargeOrdersStatus(outTradeNo, "CANCEL");
                    break;
                }
                case "TRADE_SUCCESS"://交易支付成功
                {
                	payService.chargeOrdersStatus(outTradeNo, "SUCCESS");
                    break;
                }
                case "TRADE_FINISHED"://交易结束，不可退款
                {
                	payService.chargeOrdersStatus(outTradeNo, "FINISH");
                	break;
                }
            }
            
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

    }
}
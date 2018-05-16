package com.xiaoxuedi.controller.api;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.github.wxpay.sdk.WXPay;
import com.xiaoxuedi.config.AlipayProperties;
import com.xiaoxuedi.config.WxPayProperties;
import com.xiaoxuedi.model.Output;
import com.xiaoxuedi.model.pay.AppOrderInput;
import com.xiaoxuedi.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/pay")
public class PayController extends AbstractController {

    @Autowired
    private PayService payService;

    @Resource
    private AlipayProperties alipayProperties;

    @Resource
    private WxPayProperties wxPayProperties;

    private WXPay wxpay;

    /**
     * 支付宝支付下单
     * @param input
     * @return
     */
    @PostMapping("alipayCreateOrder")
    public Output alipayCreateOrder(AppOrderInput input){

        String respOrderInfo="";
        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient(alipayProperties.getServerUrl(), alipayProperties.getAppId(), alipayProperties.getAppPrivateKey(),
                "json", alipayProperties.getCharset(), alipayProperties.getAlipayPublicKey(), "RSA2");
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(input.getBody());
        model.setSubject(input.getSubject());
        model.setOutTradeNo(input.getOutTradeNo());
        /*该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        注：若为空，则默认为15d。*/
        model.setTimeoutExpress(alipayProperties.getTimeout());//
        model.setTotalAmount(input.getTotalAmount());
        model.setProductCode("QUICK_MSECURITY_PAY");//销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY
        model.setStoreId(input.getStoreId());
        request.setBizModel(model);
        request.setNotifyUrl(alipayProperties.getNotifyUrl());
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
        //     System.out.println(response.getBody());
            respOrderInfo=response.getBody();//就是orderString 可以直接给客户端请求，无需再做处理。
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }


        return  Output.output(respOrderInfo);
    }

    /**
     * 微信支付统一下单
     * @param input
     * @return
     */
    @PostMapping("wxCreateOrder")
    public Output wxPayCreateOrder(AppOrderInput input) throws Exception {
        wxpay = new WXPay(wxPayProperties);
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("body", input.getBody());
        data.put("out_trade_no", input.getOutTradeNo());
        data.put("device_info", "WEB");
        data.put("fee_type", "CNY");
        data.put("total_fee", input.getTotalAmount());
        data.put("spbill_create_ip", "127.0.0.1");//用户端实际ip
        data.put("notify_url", wxPayProperties.getNotifyUrl());
        data.put("trade_type", "NATIVE");
        data.put("product_id", "12");
        // data.put("time_expire", "20170112104120");

        try {
            Map<String, String> r = wxpay.unifiedOrder(data);
            System.out.println(r);
            return  Output.output(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    return  Output.outputError();
    }

}
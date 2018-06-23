package com.xiaoxuedi.service;


import org.springframework.stereotype.Service;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.xiaoxuedi.config.WxPayProperties;
import com.xiaoxuedi.entity.MissionEntity;
import com.xiaoxuedi.entity.OrdersEntity;
import com.xiaoxuedi.repository.MissionRepository;
import com.xiaoxuedi.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
public class PayService {
	
    @Resource
    private MissionRepository missionRepository;
    
    @Resource
    private OrderRepository orderRepository;
    
    @Resource
    private WxPayProperties wxPayProperties;
	
	
    /**
     * 更新订单状态
     * @param outTradeNo
     */
    public void  chargeOrdersStatus(String outTradeNo,String status)
    {
    	MissionEntity mission = missionRepository.findById(outTradeNo);
    	
    	OrdersEntity order = orderRepository.findById(outTradeNo);
    	if (mission != null )
    	{
    		switch (status)
            {
                case "WAIT"://交易创建，等待买家付款
                {
                	mission.setStatus(MissionEntity.Status.WAIT);//等待支付
                    break;
                }
                case "CANCEL"://未付款交易超时关闭，或支付完成后全额退款
                {
                	mission.setStatus(MissionEntity.Status.CANCEL);//取消
                    break;
                }
                case "SUCCESS"://交易支付成功
                {
                	mission.setStatus(MissionEntity.Status.SUCCESS);//支付成功
                    break;
                }
                case "FINISH"://交易结束，不可退款
                {
                	mission.setStatus(MissionEntity.Status.FINISH);//完成
                	break;
                }
            }
    		missionRepository.save(mission);
    	}else if(order != null) {
    		
    		
    		switch (status)
            {
                case "WAIT"://交易创建，等待买家付款
                {
                	order.setStatus(OrdersEntity.Status.WAIT);//等待支付
                    break;
                }
                case "CANCEL"://未付款交易超时关闭，或支付完成后全额退款
                {
                	order.setStatus(OrdersEntity.Status.CANCEL);//取消
                    break;
                }
                case "SUCCESS"://交易支付成功
                {
                	order.setStatus(OrdersEntity.Status.SUCCESS);//支付成功
                    break;
                }
                case "FINISH"://交易结束，不可退款
                {
                	order.setStatus(OrdersEntity.Status.FINISH);//完成
                	break;
                }
            }
    		orderRepository.save(order);
    	}else {
    		log.error("查询订单失败：outTradeNo="+outTradeNo);
    		return;
    	}
    	
    }
    
    /**
     *  支付结果通知
     * @param notifyData    异步通知后的XML数据
     * @return
     */
    public String payBack(String notifyData) {
        WXPay wxpay = new WXPay(wxPayProperties);
        String xmlBack="";
        Map<String, String> notifyMap = null;
        try {
            notifyMap = WXPayUtil.xmlToMap(notifyData);         // 转换成map
            if (wxpay.isPayResultNotifySignatureValid(notifyMap)) {
                // 签名正确
                // 进行处理。
                // 注意特殊情况：订单已经退款，但收到了支付结果成功的通知，不应把商户侧订单状态从退款改成支付成功
                String  return_code = notifyMap.get("return_code");//状态
                String out_trade_no = notifyMap.get("out_trade_no");//订单号

                if(return_code.equals("SUCCESS")){
                    if(out_trade_no!=null){
                        //处理订单逻辑
                      
                        log.info(">>>>>支付成功");
                        log.info("微信手机支付回调成功订单号:{}",out_trade_no);
            			chargeOrdersStatus(out_trade_no, "SUCCESS");
                        
                        xmlBack = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                    }else {
                        log.info("微信手机支付回调失败订单号:{}",out_trade_no);
                        xmlBack = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
                    }

                }else {
                    String err_code=notifyMap.get("err_code");//业务错误代码
                    String err_code_des=notifyMap.get("err_code_des");//业务错误代码描述
                	chargeOrdersStatus(out_trade_no, "CANCEL");
    				log.error("支付业务结果失败，"+err_code+err_code_des);
                }
                return xmlBack;
            }else {
            	
                // 签名错误，如果数据里没有sign字段，也认为是签名错误
                log.error("手机支付回调通知签名错误");
                xmlBack = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
                return xmlBack;
            }
        } catch (Exception e) {
            log.error("手机支付回调通知失败",e);
            xmlBack = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
        }
        return xmlBack;
    }
    
    	
}

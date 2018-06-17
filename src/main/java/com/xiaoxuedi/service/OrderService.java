package com.xiaoxuedi.service;

import com.pingplusplus.Pingpp;
import com.pingplusplus.model.Charge;
import com.pingplusplus.model.Transfer;
import com.xiaoxuedi.Application;
import com.xiaoxuedi.config.PingxxProperties;
import com.xiaoxuedi.entity.CouponEntity;
import com.xiaoxuedi.entity.OrderCommodityEntity;
import com.xiaoxuedi.entity.OrdersEntity;
import com.xiaoxuedi.entity.UsersEntity;
import com.xiaoxuedi.model.Output;
import com.xiaoxuedi.model.PageInput;
import com.xiaoxuedi.model.order.AddOrderInput;
import com.xiaoxuedi.model.order.BalanceOutput;
import com.xiaoxuedi.model.order.ChargeInput;
import com.xiaoxuedi.model.order.ListOutput;
import com.xiaoxuedi.model.order.OrderCommodityInput;
import com.xiaoxuedi.model.order.OrderCommodityListOutput;
import com.xiaoxuedi.model.order.StatusesInput;
import com.xiaoxuedi.model.order.TransferInput;
import com.xiaoxuedi.model.order.wx.WxAddOrderInput;
import com.xiaoxuedi.model.order.wx.WxStatusesInput;
import com.xiaoxuedi.repository.CouponRepository;
import com.xiaoxuedi.repository.OrderCommodityRepository;
import com.xiaoxuedi.repository.OrderRepository;
import com.xiaoxuedi.repository.UserRepository;
import com.xiaoxuedi.util.OrderUtil;
import com.xiaoxuedi.util.StringUtil;

import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.xiaoxuedi.model.Output.*;

@Service
@Transactional
public class OrderService
{
    @Resource
    private OrderRepository orderRepository;
    @Resource
    private OrderCommodityRepository orderCommodityRepository;
    @Resource
    private CouponRepository couponRepository;
    @Resource
    private UserRepository userRepository;
    @Resource
    private PingxxProperties pingxxProperties;

    
    /**
     * 订单新增接口
     * @param input
     * @return
     */
    @Transactional
    public Output<Object> add(AddOrderInput input)
    {
    	
    	try
    	{
    		OrdersEntity order = input.toEntity();
    		order.setOrderNo(OrderUtil.getOrderIdByUUId());//订单编号
    		order.setCreateTime(new Timestamp(new Date().getTime()));//创建时间
    		order.setUser(UsersEntity.getUser());
    		//查询是否有红包可以使用
        	if(!StringUtil.isEmpty(input.getCouponId())) {
        		CouponEntity coupon = couponRepository.findOne(input.getCouponId());
        		//判断是否存在，且，不失效，不过期，满足可用金额
        		if(coupon!=null&&"valid".equals(coupon.getStatus())) {
        			long nowDate = new Date().getTime();
         			long endTime = coupon.getEndTime().getTime();
        			if(endTime>nowDate&&order.getActualAmount().compareTo(coupon.getFullAmountReduction())<0) {//可用
        				order.setCoupon_id(input.getCouponId());
        				order.setCouponAmount(coupon.getAmount());
        				if(order.getActualAmount().subtract(coupon.getAmount()).doubleValue()>0d) {
        					order.setActualAmount(order.getActualAmount().subtract(coupon.getAmount()));//实际金额
        				}else {
        					order.setActualAmount(new BigDecimal(0));//实际金额为0
        				}
        				//删除红包
        				couponRepository.delete(input.getCouponId());
        			}
        		}
        	}
        	order = orderRepository.save(order);
        	if (order == null)
        	{
        		return outputParameterError();
        	}
    		List<OrderCommodityInput> list = input.getCommodity();
    		for(OrderCommodityInput orderCommodityInput:list) {
    			if(orderCommodityInput!=null) {
    				OrderCommodityEntity orderCommodity = orderCommodityInput.toEntity();
        			if(orderCommodity!=null) {
        				orderCommodity.setOrderId(order.getId());
        				orderCommodityRepository.save(orderCommodity);
        			}
    			}
    		}
    		
        	
    		return output(order);
    	}
    	catch (Exception e)
    	{
    		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    		return outputParameterError();
    	}
    }

    /**
     * 订单新增接口
     * @param input
     * @return
     */
    @Transactional
    public Output<Object> wxAdd(WxAddOrderInput input)
    {

        try
        {
            OrdersEntity order = input.toEntity();
            order.setOrderNo(OrderUtil.getOrderIdByUUId());//订单编号
            order.setCreateTime(new Timestamp(new Date().getTime()));//创建时间
            order.setUser(UsersEntity.getUser(input.getUserid()));
            //查询是否有红包可以使用
            if(!StringUtil.isEmpty(input.getCouponId())) {
                CouponEntity coupon = couponRepository.findOne(input.getCouponId());
                //判断是否存在，且，不失效，不过期，满足可用金额
                if(coupon!=null&&"valid".equals(coupon.getStatus())) {
                    long nowDate = new Date().getTime();
                    long endTime = coupon.getEndTime().getTime();
                    if(endTime>nowDate&&order.getActualAmount().compareTo(coupon.getFullAmountReduction())<0) {//可用
                        order.setCoupon_id(input.getCouponId());
                        order.setCouponAmount(coupon.getAmount());
                        if(order.getActualAmount().subtract(coupon.getAmount()).doubleValue()>0d) {
                            order.setActualAmount(order.getActualAmount().subtract(coupon.getAmount()));//实际金额
                        }else {
                            order.setActualAmount(new BigDecimal(0));//实际金额为0
                        }
                        //删除红包
                        couponRepository.delete(input.getCouponId());
                    }
                }
            }
            order = orderRepository.save(order);
            if (order == null)
            {
                return outputParameterError();
            }
            List<OrderCommodityInput> list = input.getCommodity();
            for(OrderCommodityInput orderCommodityInput:list) {
                if(orderCommodityInput!=null) {
                    OrderCommodityEntity orderCommodity = orderCommodityInput.toEntity();
                    if(orderCommodity!=null) {
                        orderCommodity.setOrderId(order.getId());
                        orderCommodityRepository.save(orderCommodity);
                    }
                }
            }


            return output(order);
        }
        catch (Exception e)
        {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return outputParameterError();
        }
    }

    public Output<BalanceOutput> balance()
    {
        return output(new BalanceOutput().fromEntity(userRepository.getCurrentUser()));
    }

    public Output<String> charge(ChargeInput input)
    {
        Pingpp.apiKey = pingxxProperties.getApiKey();
        Pingpp.privateKey = pingxxProperties.getPrivateKey();

        OrdersEntity order = new OrdersEntity();
        order = orderRepository.save(order);
        if (order == null)
        {
            return outputParameterError();
        }

        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("order_no", order.getId());
        chargeParams.put("amount", input.getAmount());
        Map<String, String> app = new HashMap<>();
        app.put("id", pingxxProperties.getAppId());
        chargeParams.put("app", app);
        chargeParams.put("channel", input.getChannel());
        chargeParams.put("currency", "cny");
        chargeParams.put("client_ip", getIp());
        chargeParams.put("subject", pingxxProperties.getSubject());
        chargeParams.put("body", pingxxProperties.getBody());

        try
        {
            Charge charge = Charge.create(chargeParams);
            return output(charge.toString());
        }
        catch (Exception e)
        {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return outputParameterError();
        }
    }

    public Output transfer(TransferInput input)
    {
        Pingpp.apiKey = pingxxProperties.getApiKey();
        Pingpp.privateKey = pingxxProperties.getPrivateKey();

        UsersEntity user = userRepository.getCurrentUser();
        if (!user.isAuth())
        {
            return outputNotAuth();
        }
//        user.setBalance(user.getBalance() - input.getAmount());
//        if (user.getBalance() < 0)
//        {
//            return outputInsufficientBalance();
//        }
        userRepository.save(user);
        OrdersEntity order = new OrdersEntity();
        order = orderRepository.save(order);
        if (order == null)
        {
            return outputParameterError();
        }

        Map<String, Object> params = new HashMap<>();
        params.put("amount", input.getAmount());
        params.put("currency", "cny");
        params.put("type", "b2c");
        params.put("order_no", order.getId());
        params.put("channel", input.getChannel());
        params.put("description", "Your Description");
        Map<String, String> app = new HashMap<>();
        app.put("id", pingxxProperties.getAppId());
        params.put("app", app);
        switch (input.getChannel())
        {
            case "alipay":
            case "wx_pub":
                params.put("recipient", input.getRecipient());
                break;
        }
        params.put("extra", channelExtra(input));

        try
        {
            Transfer.create(params);
            return outputOk();
        }
        catch (Exception e)
        {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return outputParameterError();
        }
    }

    public void chargeSucceeded(String id)
    {
        OrdersEntity order = orderRepository.findOne(id);
        if (order == null || order.getType() != OrdersEntity.Type.CHARGE)
        {
            return;
        }
        order.setType(OrdersEntity.Type.CHARGE_SUCCEEDED);
        orderRepository.save(order);
    }

    public void transferSucceeded(String id)
    {
        OrdersEntity order = orderRepository.findOne(id);
        if (order == null || order.getType() != OrdersEntity.Type.TRANSFER)
        {
            return;
        }
        order.setType(OrdersEntity.Type.TRANSFER_SUCCEEDED);
        orderRepository.save(order);
    }

    public void transferFailed(String id)
    {
        OrdersEntity order = orderRepository.findOne(id);
        if (order == null || order.getType() != OrdersEntity.Type.TRANSFER)
        {
            return;
        }
        order.setType(OrdersEntity.Type.TRANSFER_FAILED);
        orderRepository.save(order);
    }
    
    
    
    
    /**
     * 订单失败
     * @param id
     */
    public void  ordersFailed(String id)
    {
        OrdersEntity order = orderRepository.findOne(id);
        if (order == null || order.getType() != OrdersEntity.Type.TRANSFER)
        {
            return;
        }
        order.setType(OrdersEntity.Type.TRANSFER_FAILED);
        orderRepository.save(order);
    }
    
    
    

    public Output<List<ListOutput>> list(PageInput input)
    {
        List<OrdersEntity> orders = orderRepository.findAllByUser(UsersEntity.getUser(), input.getPageableSortByTime());
        List<ListOutput> outputs = new ListOutput().fromEntityList(orders);
        for(ListOutput ordersEntity:outputs) {
        	List<OrderCommodityEntity> commoditylist=orderCommodityRepository.findAllByOrderId(ordersEntity.getId());
        	List<OrderCommodityListOutput> outputsCommodity=new OrderCommodityListOutput().fromEntityList(commoditylist);
        	ordersEntity.setOrderCommodity(outputsCommodity);
        }
        return output(outputs);
    }
    
    /**
     * 根据状态查询订单
     * @param input
     * @return
     */
    public Output<List<ListOutput>> list(StatusesInput input)
    {
    	List<OrdersEntity> orders = orderRepository.findAllByUserAndStatusIn(UsersEntity.getUser(), input.getStatuses(), input.getPageableSortByTime());
    	 List<ListOutput> outputs = new ListOutput().fromEntityList(orders);
         for(ListOutput ordersEntity:outputs) {
         	List<OrderCommodityEntity> commoditylist=orderCommodityRepository.findAllByOrderId(ordersEntity.getId());
         	List<OrderCommodityListOutput> outputsCommodity=new OrderCommodityListOutput().fromEntityList(commoditylist);
         	ordersEntity.setOrderCommodity(outputsCommodity);
         }
    	return output(outputs);
    }

    /**
     * 根据状态查询订单
     * @param input
     * @return
     */
    public Output<List<ListOutput>> wxList(WxStatusesInput input)
    {
        List<OrdersEntity> orders = orderRepository.findAllByUserAndStatusIn(UsersEntity.getUser(input.getUserid()), input.getStatuses(), input.getPageableSortByTime());
        List<ListOutput> outputs = new ListOutput().fromEntityList(orders);
        for(ListOutput ordersEntity:outputs) {
            List<OrderCommodityEntity> commoditylist=orderCommodityRepository.findAllByOrderId(ordersEntity.getId());
            List<OrderCommodityListOutput> outputsCommodity=new OrderCommodityListOutput().fromEntityList(commoditylist);
            ordersEntity.setOrderCommodity(outputsCommodity);
        }
        return output(outputs);
    }
    private String getIp()
    {
        HttpServletRequest request = Application.getRequest();
        if (request == null)
        {
            return "127.0.0.1";
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    private Map<String, Object> channelExtra(TransferInput input)
    {
        Map<String, Object> extra = new HashMap<>();

        switch (input.getChannel())
        {
            case "alipay":
                extra = alipayExtra(input);
                break;
            case "wx_pub":
                extra = wxPubExtra(input);
                break;
            case "unionpay":
            case "allinpay":
            case "jdpay":
                extra = unionpayExtra(input);
                break;
        }

        return extra;
    }

    private Map<String, Object> alipayExtra(TransferInput input)
    {
        Map<String, Object> extra = new HashMap<>();
        extra.put("recipient_name", input.getName());
        return extra;
    }

    private Map<String, Object> wxPubExtra(TransferInput input)
    {
        Map<String, Object> extra = new HashMap<>();
        extra.put("user_name", input.getName());
        return extra;
    }

    private Map<String, Object> unionpayExtra(TransferInput input)
    {
        Map<String, Object> extra = new HashMap<>();
        extra.put("card_number", input.getCardNumber());
        extra.put("user_name", input.getName());
        extra.put("open_bank_code", input.getOpenBankCode());
        return extra;
    }
}

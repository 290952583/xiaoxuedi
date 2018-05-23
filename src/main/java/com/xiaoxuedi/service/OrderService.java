package com.xiaoxuedi.service;

import com.pingplusplus.Pingpp;
import com.pingplusplus.model.Charge;
import com.pingplusplus.model.Transfer;
import com.xiaoxuedi.Application;
import com.xiaoxuedi.config.PingxxProperties;
import com.xiaoxuedi.entity.OrdersEntity;
import com.xiaoxuedi.entity.UsersEntity;
import com.xiaoxuedi.model.Output;
import com.xiaoxuedi.model.PageInput;
import com.xiaoxuedi.model.order.BalanceOutput;
import com.xiaoxuedi.model.order.ChargeInput;
import com.xiaoxuedi.model.order.ListOutput;
import com.xiaoxuedi.model.order.TransferInput;
import com.xiaoxuedi.repository.OrderRepository;
import com.xiaoxuedi.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
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
    private UserRepository userRepository;
    @Resource
    private PingxxProperties pingxxProperties;

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
        UsersEntity user = order.getUser();
//        user.setBalance(user.getBalance() + order.getAmount());
        userRepository.save(user);
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
        UsersEntity user = order.getUser();
//        user.setBalance(user.getBalance() - order.getAmount());
        userRepository.save(user);
        orderRepository.save(order);
    }

    public Output<List<ListOutput>> list(PageInput input)
    {
        List<OrdersEntity> orders = orderRepository.findAllByUser(UsersEntity.getUser(), input.getPageableSortByTime());
        List<ListOutput> outputs = new ListOutput().fromEntityList(orders);
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

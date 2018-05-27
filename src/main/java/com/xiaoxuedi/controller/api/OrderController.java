package com.xiaoxuedi.controller.api;


import com.pingplusplus.model.*;
import com.xiaoxuedi.Application;
import com.xiaoxuedi.config.PingxxProperties;
import com.xiaoxuedi.model.order.ChargeInput;
import com.xiaoxuedi.model.order.ListOutput;
import com.xiaoxuedi.model.order.StatusesInput;
import com.xiaoxuedi.model.order.TransferInput;
import com.xiaoxuedi.service.MissionService;
import com.xiaoxuedi.service.OrderService;
import com.xiaoxuedi.model.Output;
import com.xiaoxuedi.model.PageInput;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static com.xiaoxuedi.model.Output.output;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/order")
public class OrderController extends AbstractController
{
    @Autowired
    private OrderService orderService;
    @Autowired
    private MissionService missionService;

    @GetMapping("balance")
    public Output balance()
    {
        return orderService.balance();
    }

    @PostMapping("charge")
    public Output charge(@Valid @RequestBody ChargeInput input)
    {
        return orderService.charge(input);
    }

    @PostMapping("transfer")
    public Output transfer(@Valid @RequestBody TransferInput input)
    {
        return orderService.transfer(input);
    }

    @PostMapping("webHooks")
    public void webHooks(@RequestHeader("x-pingplusplus-signature") String signatureString, @RequestBody String body, HttpServletResponse response) throws Exception
    {
        byte[] signatureBytes = Base64.decodeBase64(signatureString);
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(getPubKey());
        signature.update(body.getBytes("UTF-8"));
        if (!signature.verify(signatureBytes))
        {
            response.setStatus(500);
            return;
        }

        Event event = Webhooks.eventParse(body);
        switch (event.getType())
        {
            case "charge.succeeded":
            {
                Charge charge = (Charge) event.getData().getObject();
                orderService.chargeSucceeded(charge.getOrderNo());
                break;
            }
            case "transfer.succeeded":
            {
                Transfer transfer = (Transfer) event.getData().getObject();
                orderService.transferSucceeded(transfer.getOrderNo());
                break;
            }
            case "transfer.failed":
            {
                Transfer transfer = (Transfer) event.getData().getObject();
                orderService.transferFailed(transfer.getOrderNo());
                break;
            }
        }
        response.setStatus(200);
    }

    private static PublicKey publicKey;

    private static PublicKey getPubKey() throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        if (publicKey == null)
        {
            PingxxProperties pingxxProperties = Application.getBean(PingxxProperties.class);
            byte[] pubKeyBytes = Base64.decodeBase64(pingxxProperties.getPublicKey());
            X509EncodedKeySpec spec = new X509EncodedKeySpec(pubKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(spec);
        }
        return publicKey;
    }

    /**
     * 查询所有订单
     * @param input
     * @return
     */
    @GetMapping("list")
    public Output list(@Valid PageInput input)
    {
    	Map<String, Object> map=new HashMap<String, Object>();
    	map.put("order", orderService.list(input));
    	map.put("mission", missionService.list(input));
    	
        return output(map);
    }
    /**
     *  根据状态查询所有订单
     * @param input
     * @return
     */
    @GetMapping("statusList")
    public Output statusList(@Valid StatusesInput input)
    {
    	Map<String, Object> map=new HashMap<String, Object>();
    	map.put("order", orderService.list(input));
    	map.put("mission", missionService.list(input));
    	return output(map);
    }
}

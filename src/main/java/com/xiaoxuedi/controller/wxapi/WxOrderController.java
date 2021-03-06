package com.xiaoxuedi.controller.wxapi;


import com.xiaoxuedi.controller.api.AbstractController;
import com.xiaoxuedi.model.Output;
import com.xiaoxuedi.model.PageInput;
import com.xiaoxuedi.model.order.AddOrderInput;
import com.xiaoxuedi.model.order.ChargeInput;
import com.xiaoxuedi.model.order.StatusesInput;
import com.xiaoxuedi.model.order.TransferInput;
import com.xiaoxuedi.model.order.wx.WxAddOrderInput;
import com.xiaoxuedi.model.order.wx.WxStatusesInput;
import com.xiaoxuedi.model.order.wx.WxTypesInput;
import com.xiaoxuedi.service.MissionService;
import com.xiaoxuedi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static com.xiaoxuedi.model.Output.output;

@RestController
@RequestMapping("wxapi/order")
public class WxOrderController extends AbstractController
{
    @Autowired
    private OrderService orderService;
    @Autowired
    private MissionService missionService;

   /* @GetMapping("balance")
    public Output balance()
    {
        return orderService.balance();
    }*/

    /**
     * 新增早餐订单接口
     * @param input
     * @return
     */
    @PostMapping("add")
    public Output add(@Valid @RequestBody WxAddOrderInput input)
    {
    	return orderService.wxAdd(input);
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

/*    @PostMapping("webHooks")
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
    }*/

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
    	orderService.list(input);
    	missionService.list(input);
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
    @PostMapping("statusList")
    public Output statusList(@Valid  @RequestBody WxStatusesInput input)
    {
//    	orderService.list(input);
//    	missionService.list(input);
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("order", orderService.wxList(input));
        map.put("mission", missionService.wxList(input));
        return output(map);
    }

    @PostMapping("typesList")
    public Output typesList(@Valid  @RequestBody WxTypesInput input)
    {
//    	orderService.list(input);
//    	missionService.list(input);
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("order", orderService.wxTypesList(input));
        map.put("mission", missionService.wxList(input));
        return output(map);
    }
}

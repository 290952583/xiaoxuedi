package com.xiaoxuedi.controller.api;

import com.xiaoxuedi.model.common.SendSmsInput;
import com.xiaoxuedi.service.CouponService;
import com.xiaoxuedi.service.DeliveryService;
import com.xiaoxuedi.service.SchoolService;
import com.xiaoxuedi.service.SmsService;
import com.xiaoxuedi.model.Output;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.xiaoxuedi.model.Output.outputOk;

@RestController
@RequestMapping("api/common")
public class CommonController extends AbstractController
{
    @Autowired
    private SchoolService schoolService;
    @Autowired
    private SmsService smsService;
    @Autowired
    private DeliveryService deliveryService;
    @Autowired
    private CouponService couponService;

    @PostMapping("sendSms")
    public Output sendSms(@Valid @RequestBody SendSmsInput input)
    {
        return smsService.sendSms(input);
    }

    @GetMapping("school")
    public Output school()
    {
        return schoolService.list();
    }

    @GetMapping("delivery")
    public Output delivery()
    {
        return deliveryService.list();
    }
    
    
    /**
     * 获取优惠价信息
     * @return
     */
    @PostMapping("coupon")
    public Output coupon()
    {
        return couponService.list();
    }

    @GetMapping("testLogin")
    public Output testLogin()
    {
        autoLogin("18201265103");
        return outputOk();
    }
}

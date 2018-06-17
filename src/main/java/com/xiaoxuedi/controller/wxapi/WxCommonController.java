package com.xiaoxuedi.controller.wxapi;

import com.xiaoxuedi.controller.api.AbstractController;
import com.xiaoxuedi.model.Output;
import com.xiaoxuedi.model.common.SendSmsInput;
import com.xiaoxuedi.model.coupon.wx.WxCouponInput;
import com.xiaoxuedi.service.CouponService;
import com.xiaoxuedi.service.DeliveryService;
import com.xiaoxuedi.service.SchoolService;
import com.xiaoxuedi.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.xiaoxuedi.model.Output.outputOk;

@RestController
@RequestMapping("wxapi/common")
public class WxCommonController extends AbstractController
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

    @PostMapping("delivery")
    public Output delivery()
    {
        return deliveryService.list();
    }
    
    
    /**
     * 获取优惠价信息
     * @return
     */
    @PostMapping("coupon")
    public Output coupon(@RequestBody WxCouponInput input)
    {
        return couponService.wxCouponlist(input.getUserid());
    }

    @GetMapping("testLogin")
    public Output testLogin()
    {
        autoLogin("18201265103");
        return outputOk();
    }
}

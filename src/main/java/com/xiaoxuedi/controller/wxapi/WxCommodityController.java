package com.xiaoxuedi.controller.wxapi;

import com.xiaoxuedi.model.Output;
import com.xiaoxuedi.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("wxapi/commodity")
public class WxCommodityController {

    @Autowired
    private CommodityService commodityService;

    /**
     * 根据商户id获取商品信息列表
     * list
     * fjx
     * 2018年6月6日
     * Output
     * @param businessId
     * @return
     */
    @GetMapping("list")
    public Output list(@RequestParam(value = "businessId", required = false) String businessId){
    	return commodityService.getListByBusinessId(businessId);
    }
}

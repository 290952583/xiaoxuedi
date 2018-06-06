package com.xiaoxuedi.controller.api;

import com.xiaoxuedi.model.Output;
import com.xiaoxuedi.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/commodity")
public class CommodityController {

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

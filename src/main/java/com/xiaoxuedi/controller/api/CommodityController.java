package com.xiaoxuedi.controller.api;

import com.xiaoxuedi.model.Output;
import com.xiaoxuedi.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/commodity")
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    @GetMapping("list")
    public Output list(){
        return commodityService.list();
    }
}

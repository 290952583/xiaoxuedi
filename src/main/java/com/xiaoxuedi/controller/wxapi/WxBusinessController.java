package com.xiaoxuedi.controller.wxapi;

import com.xiaoxuedi.model.Output;
import com.xiaoxuedi.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
    @RequestMapping("wxapi/business")
public class WxBusinessController {

    @Autowired
    private BusinessService BusinessService;

    //获取商家列表
    @GetMapping("list")
    public Output list(@RequestParam(value = "schoolId", required = false) String schoolId) {
        return BusinessService.list(schoolId);
    }
}

package com.xiaoxuedi.controller.api;

import com.xiaoxuedi.model.Output;
import com.xiaoxuedi.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/business")
public class BusinessController {

    @Autowired
    private BusinessService BusinessService;

    @GetMapping("list")
    public Output list(){
        return BusinessService.list();
    }
}

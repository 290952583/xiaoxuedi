package com.xiaoxuedi.service;

import com.xiaoxuedi.repository.OrderCommodityRepository;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;


@Service
@Transactional
public class OrderCommodityService {

    @Resource
    private OrderCommodityRepository orderCommodityRepository;

}

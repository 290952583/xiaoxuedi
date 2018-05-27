package com.xiaoxuedi.service;

import com.xiaoxuedi.entity.DeliveryEntity;
import com.xiaoxuedi.model.Output;
import com.xiaoxuedi.model.delivery.ListOutput;
import com.xiaoxuedi.repository.DeliveryRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

import static com.xiaoxuedi.model.Output.output;

@Service
@Transactional
public class DeliveryService {

    @Resource
    private DeliveryRepository deliveryRepository;

    /**
     * 查询所有快递公司列表
     * @return 快递公司列表（id,name）
     */
    public Output<List<ListOutput>> list(){
        List<DeliveryEntity> deliverys = deliveryRepository.findAll();
        List<ListOutput> outputs = new ListOutput().fromEntityList(deliverys);
        return output(outputs);
    }

}

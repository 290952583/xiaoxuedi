package com.xiaoxuedi.service;

import com.xiaoxuedi.entity.CommodityEntity;
import com.xiaoxuedi.model.Output;
import com.xiaoxuedi.model.commodity.ListOutput;
import com.xiaoxuedi.repository.CommodityRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

import static com.xiaoxuedi.model.Output.output;

@Service
@Transactional
public class CommodityService {

    @Resource
    private CommodityRepository commodityRepository;

    /**
     * 查询所有商品信息列表（无分页）
     * @return
     */
    public Output<List<ListOutput>> list()
    {
        List<CommodityEntity> commoditys = commodityRepository.findAll();
        List<ListOutput> outputs = new ListOutput().fromEntityList(commoditys);
        return output(outputs);
    }
}

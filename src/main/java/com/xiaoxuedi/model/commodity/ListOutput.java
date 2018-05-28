package com.xiaoxuedi.model.commodity;

import com.xiaoxuedi.entity.CommodityEntity;
import com.xiaoxuedi.model.ModelFromEntityList;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ListOutput implements ModelFromEntityList<CommodityEntity, ListOutput> {

    public String id;
    public String type;
    public String name;
    public String salesSum;
    public BigDecimal price;
    public BigDecimal originalPrice;
    public BigDecimal costPrice;
    public String imgs;
    public String businessId;

    @Override
    public ListOutput fromEntity(CommodityEntity commodity){
        this.id = commodity.getId();
        this.type = commodity.getType();
        this.name = commodity.getName();
        this.salesSum = commodity.getSalesSum();
        this.price = commodity.getPrice();
        this.originalPrice = commodity.getOriginalPrice();
        this.costPrice = commodity.getCostPrice();
        this.imgs = commodity.getImgs();
//        this.businessId = commodity.getBusinessId();
        return this;

    }

}

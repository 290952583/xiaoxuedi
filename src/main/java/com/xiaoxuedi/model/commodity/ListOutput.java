package com.xiaoxuedi.model.commodity;

import com.xiaoxuedi.entity.CommodityEntity;
import com.xiaoxuedi.model.ModelFromEntityList;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ListOutput implements ModelFromEntityList<CommodityEntity, ListOutput> {

    public String id;
    public String type;//商品类型
    public String name;//商品名称
    public String salesSum;//销量
    public BigDecimal price;//售价
    public BigDecimal originalPrice;//原价
    private BigDecimal packPrice;//打包费用
    public BigDecimal costPrice;
    public String imgs;//商品图片,可以多张（‘，’隔开）
    public String businessId;//商户id

    @Override
    public ListOutput fromEntity(CommodityEntity commodity){
        this.id = commodity.getId();
        this.type = commodity.getType();
        this.name = commodity.getName();
        this.salesSum = commodity.getSalesSum();
        this.price = commodity.getPrice();
        this.originalPrice = commodity.getOriginalPrice();
        this.costPrice = commodity.getCostPrice();
        this.packPrice =commodity.getPackPrice();
        this.imgs = commodity.getImgs();
        this.businessId = commodity.getBusinessId();
        return this;

    }

}

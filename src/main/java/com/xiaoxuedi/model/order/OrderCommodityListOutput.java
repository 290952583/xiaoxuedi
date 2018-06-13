package com.xiaoxuedi.model.order;

import java.math.BigDecimal;

import com.xiaoxuedi.entity.OrderCommodityEntity;
import com.xiaoxuedi.model.ModelFromEntityList;

import lombok.Data;

@Data
public class OrderCommodityListOutput implements ModelFromEntityList<OrderCommodityEntity, OrderCommodityListOutput>{
	
	
    /**
     * 商品id
     */
    private String commodityId;

    /**
     * 商品名称
     */
    private String commodityName;

    /**
     * 商品数量
     */
    private Integer commoditySum;

    /**
     * 商品总价
     */
    private BigDecimal commodityAmountSum;

    @Override
	public OrderCommodityListOutput fromEntity(OrderCommodityEntity orderCommodityEntity)
	{
    	this.commodityAmountSum=orderCommodityEntity.getCommodityAmountSum();
    	this.commodityId=orderCommodityEntity.getCommodityId();
    	this.commodityName=orderCommodityEntity.getCommodityName();
    	this.commoditySum=orderCommodityEntity.getCommoditySum();
		return this;
	}

}

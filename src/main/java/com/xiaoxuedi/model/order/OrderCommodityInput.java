package com.xiaoxuedi.model.order;


import java.math.BigDecimal;

import org.springframework.validation.annotation.Validated;

import com.xiaoxuedi.entity.OrderCommodityEntity;
import com.xiaoxuedi.model.ModelToEntity;

import lombok.Data;

/**
 * 订单商品信息
 * @author fjx
 *
 */
@Data
@Validated
public class OrderCommodityInput implements ModelToEntity<OrderCommodityEntity>{
	

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
	public OrderCommodityEntity toEntity() {
		OrderCommodityEntity orderCommodityEntity=new OrderCommodityEntity();
		orderCommodityEntity.setCommodityAmountSum(commodityAmountSum);
		orderCommodityEntity.setCommodityId(commodityId);
		orderCommodityEntity.setCommodityName(commodityName);
		orderCommodityEntity.setCommoditySum(commoditySum);
		return orderCommodityEntity;
	}

}

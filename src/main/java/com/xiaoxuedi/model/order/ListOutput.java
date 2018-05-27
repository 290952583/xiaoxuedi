package com.xiaoxuedi.model.order;

import com.xiaoxuedi.entity.OrdersEntity;
import com.xiaoxuedi.model.ModelFromEntityList;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class ListOutput implements ModelFromEntityList<OrdersEntity, ListOutput>
{
	private String id;
	private Timestamp time;
	private BigDecimal amount;
	private String missionId;
	private OrdersEntity.Type type;

	@Override
	public ListOutput fromEntity(OrdersEntity order)
	{
		id = order.getId();
		time = order.getCreateTime();
		amount = order.getOrderAmount();
		type = order.getType();
//		if (order.getMission() != null)
//		{
//			missionId = order.getMission().getId();
//		}
		return this;
	}
}

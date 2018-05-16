package com.xiaoxuedi.model.order;

import com.xiaoxuedi.entity.Order;
import com.xiaoxuedi.model.ModelFromEntityList;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ListOutput implements ModelFromEntityList<Order, ListOutput>
{
	private String id;
	private Timestamp time;
	private int amount;
	private String missionId;
	private Order.Type type;

	@Override
	public ListOutput fromEntity(Order order)
	{
		id = order.getId();
		time = order.getTime();
		amount = order.getAmount();
		type = order.getType();
		if (order.getMission() != null)
		{
			missionId = order.getMission().getId();
		}
		return this;
	}
}

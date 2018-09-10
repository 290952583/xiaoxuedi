package com.xiaoxuedi.model.order;

import com.xiaoxuedi.entity.OrdersEntity;
import com.xiaoxuedi.model.ModelFromEntityList;
import com.xiaoxuedi.repository.BusinessUsersRepository;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


@Data
public class ListOutput implements ModelFromEntityList<OrdersEntity, ListOutput>
{

	private String id;
	private String orderNo;//订单号
	private BigDecimal orderAmount;//订单金额
	private BigDecimal packPrice;//打包费
	private BigDecimal actualAmount;//实际金额
	private BigDecimal couponAmount;//优惠金额
	private BigDecimal deliveryAmount;//快递费
	private Timestamp createTime;//下单时间
	private Timestamp receiveTime;
	private Timestamp forecastTime;
	private Timestamp finishTime;//完成时间
	private OrdersEntity.Type type;//类型
	private String address;//地址
	private String remark;//备注
	private OrdersEntity.Status status;
	
	private List<OrderCommodityListOutput> orderCommodity;//订单商品
	
    private String businessName;//店铺名称
	private String businessImg;//店铺头像

	private String businessId;//店铺id

	@Override
	public ListOutput fromEntity(OrdersEntity order)
	{

		 this.id=order.getId();
		 this.orderNo=order.getOrderNo();//订单号
		 this.orderAmount=order.getOrderAmount();//订单金额
		 this.actualAmount=order.getActualAmount();//实际金额
		 this.couponAmount=order.getCouponAmount();//优化金额
		 this.deliveryAmount=order.getDeliveryAmount();//快递费
		 this.createTime=order.getCreateTime();//下单时间
		 this.receiveTime=order.getReceiveTime();
		 this.forecastTime=order.getForecastTime();
		 this.finishTime=order.getFinishTime();//完成时间
		 this.type=order.getType();//类型
		 this.address=order.getAddress();//地址
		 this.remark=order.getRemark();//备注
		 this.status=order.getStatus();
		 this.businessName=order.getBusinessName();
		 this.businessId=order.getBusinessId();
		this.packPrice=order.getPackPrice();
		return this;
	}
}

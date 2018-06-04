package com.xiaoxuedi.model.order;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import com.xiaoxuedi.entity.OrdersEntity;
import com.xiaoxuedi.model.ModelToEntity;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Validated
public class AddOrderInput implements ModelToEntity<OrdersEntity>
{
	@NotNull
	@Min(1)
    private BigDecimal orderAmount;//订单金额，商品总价
    
	@NotNull
	@Min(1)
    private BigDecimal actualAmount;//实际金额，应付
	
    @NotNull
    @Min(1)
    private BigDecimal deliveryAmount;//配送费
    
    @NotNull
    @Min(1)
    private BigDecimal packPrice;//打包费

//	@NotNull
//	@Min(1)
    private BigDecimal couponAmount;//优惠金额
    
//	@NotNull
    private String coupon_id;//优惠券id



//    @NotNull
    private String payType;//支付方式


    @NotNull
    private String address;//收货地址


    private String remark;//备注
    
    @NotNull
    private String businessName;//店铺名称
    
    @NotNull
    private String businessId;//店铺id
    
    private List<OrderCommodityInput> commodity;//商品信息

	@Override
	public OrdersEntity toEntity() {
		OrdersEntity order = new OrdersEntity();
		order.setActualAmount(actualAmount);
		order.setAddress(address);
		order.setCoupon_id(coupon_id);
		order.setCouponAmount(couponAmount);
		order.setDeliveryAmount(deliveryAmount);
		order.setOrderAmount(orderAmount);
		order.setPackPrice(packPrice);
		order.setPayType(payType);
		order.setRemark(remark);
		return order;
	}
    
    
}

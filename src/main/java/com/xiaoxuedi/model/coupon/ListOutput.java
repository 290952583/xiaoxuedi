package com.xiaoxuedi.model.coupon;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.xiaoxuedi.entity.CouponEntity;
import com.xiaoxuedi.model.ModelFromEntityList;
import lombok.Data;

@Data
public class ListOutput implements ModelFromEntityList<CouponEntity, ListOutput>
{
		private String id;

	    private String name;//红包名称

	    private String type;//红包类型

	    private BigDecimal amount;//红包金额

	    private BigDecimal fullAmountReduction;//满减金额限制

	    private Timestamp startTime;//开始时间

	    private Timestamp endTime;//失效时间

	    private String status;//红包状态

	@Override
    public ListOutput fromEntity(CouponEntity coupon) {
		this.id = coupon.getId();
		this.name=coupon.getName();
		this.type=coupon.getType();
		this.amount=coupon.getAmount();
		this.fullAmountReduction=coupon.getFullAmountReduction();
		this.startTime=coupon.getStartTime();
		this.endTime=coupon.getEndTime();
		this.status=coupon.getStatus();
		return this;
	}
}

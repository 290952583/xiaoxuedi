package com.xiaoxuedi.model.mission.wx;

import com.xiaoxuedi.entity.MissionEntity;
import com.xiaoxuedi.model.ModelFromEntityList;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class WxListOutput implements ModelFromEntityList<MissionEntity, WxListOutput>
{
	private String id;
	private String address;//收货地址
	private Timestamp createTime;//创建时间
	private MissionEntity.Status status;//状态
	private String distributionTime;//配送时间
	private String school;//学校区域
	private String getCode;//取件码
	private String remark;//备注
	private String orderNo;//订单编号
	private String delivery;//快递公司

	private BigDecimal price;//实际的订单价格

	@Override
	public WxListOutput fromEntity(MissionEntity mission)
	{
		this.id = mission.getId();
		this.address = mission.getAddress();
		this.createTime = mission.getCreateTime();
		this.status = mission.getStatus();
		this.distributionTime=mission.getDistributionTime();
		this.school = mission.getSchool();
		this.getCode = mission.getGetCode();
		this.remark = mission.getRemark();
		this.orderNo = mission.getMissionNo();
		this.price=mission.getPrice();
		this.delivery=mission.getDelivery();
		return this;
	}

}

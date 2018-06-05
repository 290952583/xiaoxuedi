package com.xiaoxuedi.model.mission.wx;

import com.xiaoxuedi.entity.MissionEntity;
import com.xiaoxuedi.entity.UsersEntity;
import com.xiaoxuedi.model.ModelToEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Validated
public class WxAddInput implements ModelToEntity<MissionEntity>
{

	@NotNull
	private String userid;
	@NotNull
    @Length(min = 2, max = 255)
	private String address;//收货地址
	
	@NotNull
	@Length(min = 4, max = 255)
	private String school;//学校区域

	@NotNull
	@Length(min = 4, max = 255)
	private String delivery;//快递公司
	
	@NotNull
	@Length(min = 2, max = 20)
	private String distributionTime;//配送时间	
	
	@NotNull
	@Length(min = 2, max = 20)
	private String type;//任务类型，代取快递（express）

//	@NotNull
//	@Length(min = 0, max = 30)
	private String getCode;//取件码
	
//	@NotNull
//	@Length(min = 0, max = 255)
	private String coupon_id;//红包id
//	@NotNull
//	@Length(min = 0, max = 512)
		private String remark;//备注
	@NotNull
	@Min(0)
	private BigDecimal price;//任务费用

	@Override
    public MissionEntity toEntity() {
        MissionEntity mission = new MissionEntity();
        mission.setAddress(getAddress());
        mission.setSchool(getSchool());
        mission.setDelivery(getDelivery());
        mission.setDistributionTime(getDistributionTime());
        mission.setType(getType());
        mission.setGetCode(getGetCode());
		mission.setPrice(getPrice());
		mission.setRemark(getRemark());
		mission.setCoupon_id(getCoupon_id());
        mission.setUser(UsersEntity.getUser(userid));
        return mission;
	}
}

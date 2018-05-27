package com.xiaoxuedi.model.mission;

import com.xiaoxuedi.entity.MissionEntity;
import com.xiaoxuedi.entity.UsersEntity;
import com.xiaoxuedi.model.ModelFromEntity;
import com.xiaoxuedi.model.ModelFromEntityList;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ListOutput implements ModelFromEntityList<MissionEntity, ListOutput>
{
	private String id;
	private UsersEntity user;
	private String address;//收货地址
	private Timestamp createTime;//创建时间
	private MissionEntity.Status status;//状态
	private UsersEntity acceptUser;//接单员
	private String distributionTime;//配送时间
	private String school;//学校区域
	private String getCode;//取见码
	private String remark;//备注

	@Override
	public ListOutput fromEntity(MissionEntity mission)
	{
		this.id = mission.getId();
		this.user = new UsersEntity().fromEntity(mission.getUser());
		this.address = mission.getAddress();
		this.createTime = mission.getCreateTime();
		this.status = mission.getStatus();
		this.distributionTime=mission.getDistributionTime();
		this.school = mission.getSchool();
		this.getCode = mission.getGetCode();
		this.remark = mission.getRemark();
		
		if (mission.getUser() != null) {
			acceptUser = new UsersEntity().fromEntity(mission.getUser());
		}
		return this;
	}

	@Data
	public class User implements ModelFromEntity<UsersEntity, User>
	{
		private String id;
		private String username;
		private String sex;
		private String mobile;
		private String school;

		@Override
		public User fromEntity(UsersEntity user)
		{
			id = user.getId();
			username = user.getUsername();
			sex = user.getSex();
			mobile = user.getMobile();
			school = user.getSchool().getSchool();
			return this;
		}
	}
}

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
	private String title;
	private String description;
	private String address;
    private int price;
	private Timestamp time;
	private MissionEntity.Status status;
	private UsersEntity acceptUser;
	private Timestamp acceptTime;

	@Override
	public ListOutput fromEntity(MissionEntity mission)
	{
		id = mission.getId();
		user = new UsersEntity().fromEntity(mission.getUser());
		address = mission.getAddress();
		price = mission.getPrice();
		time = mission.getCreateTime();
		status = mission.getStatus();
		if (mission.getUser() != null) {
			acceptUser = new UsersEntity().fromEntity(mission.getUser());
			acceptTime = mission.getCreateTime();
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

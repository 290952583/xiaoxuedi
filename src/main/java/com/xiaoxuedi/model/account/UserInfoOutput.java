package com.xiaoxuedi.model.account;

import com.xiaoxuedi.entity.SchoolEntity;
import com.xiaoxuedi.entity.UsersEntity;
import com.xiaoxuedi.model.ModelFromEntity;
import lombok.Data;

@Data
public class UserInfoOutput implements ModelFromEntity<UsersEntity, UserInfoOutput>
{
	private String id;
	private String username;
	private String mobile;
	private String idCard;
	private String name;
    private String sex;
    private UsersEntity.AuthStatus authStatus;
    private School school;
    private String jsessionId;

	@Override
    public UserInfoOutput fromEntity(UsersEntity user) {
		id = user.getId();
		username = user.getUsername();
		mobile = user.getMobile();
		idCard = user.getIdCard();
		name = user.getName();
		sex = user.getSex();
		authStatus = user.getAuthStatus();
		school = new School().fromEntity(user.getSchool());
		jsessionId = user.getJsessionId();
		return this;
	}

	@Data
    public static class School implements ModelFromEntity<SchoolEntity, School> {
		private String id;
		private String school;

		@Override
        public School fromEntity(SchoolEntity school) {
			this.id = school.getId();
			this.school = school.getSchool();
			return this;
		}
	}
}

package com.xiaoxuedi.model.account;

import com.xiaoxuedi.entity.UsersEntity;
import com.xiaoxuedi.model.ModelFromEntity;
import lombok.Data;

@Data
public class AuthOutput implements ModelFromEntity<UsersEntity, AuthOutput> {
    private UsersEntity.AuthStatus auth;

	@Override
    public AuthOutput fromEntity(UsersEntity user) {
		auth = user.getAuthStatus();
		return this;
	}
}

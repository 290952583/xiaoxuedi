package com.xiaoxuedi.model.account;

import com.xiaoxuedi.entity.User;
import com.xiaoxuedi.model.ModelFromEntity;
import lombok.Data;

@Data
public class AuthOutput implements ModelFromEntity<User, AuthOutput>
{
	private User.AuthStatus auth;

	@Override
	public AuthOutput fromEntity(User user)
	{
		auth = user.getAuthStatus();
		return this;
	}
}

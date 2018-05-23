package com.xiaoxuedi.entity;

public interface BelongUser
{
    UsersEntity getUser();

	default boolean isBelong()
	{
        String userId = UsersEntity.getUserId();
        if (userId == null || getUser() == null)
			return  false;

        return getUser().getId().equals(userId);
	}
}

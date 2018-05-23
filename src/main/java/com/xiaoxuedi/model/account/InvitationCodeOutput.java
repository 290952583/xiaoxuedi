package com.xiaoxuedi.model.account;

import com.xiaoxuedi.entity.UsersEntity;
import com.xiaoxuedi.model.ModelFromEntity;
import lombok.Data;

@Data
public class InvitationCodeOutput implements ModelFromEntity<UsersEntity, InvitationCodeOutput>
{
	private String invitationCode;
	private int invitationCount;

	@Override
    public InvitationCodeOutput fromEntity(UsersEntity user) {
		invitationCode = user.getInvitationCode();
		invitationCount = user.getInvitationCount();
		return this;
	}
}

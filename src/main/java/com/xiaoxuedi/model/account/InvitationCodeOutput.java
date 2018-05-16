package com.xiaoxuedi.model.account;

import com.xiaoxuedi.entity.User;
import com.xiaoxuedi.model.ModelFromEntity;
import lombok.Data;

@Data
public class InvitationCodeOutput implements ModelFromEntity<User, InvitationCodeOutput>
{
	private String invitationCode;
	private int invitationCount;

	@Override
	public InvitationCodeOutput fromEntity(User user)
	{
		invitationCode = user.getInvitationCode();
		invitationCount = user.getInvitationCount();
		return this;
	}
}

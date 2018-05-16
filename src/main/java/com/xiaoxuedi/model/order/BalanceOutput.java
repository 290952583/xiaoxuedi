package com.xiaoxuedi.model.order;

import com.xiaoxuedi.entity.User;
import com.xiaoxuedi.model.ModelFromEntity;
import lombok.Data;

@Data
public class BalanceOutput implements ModelFromEntity<User, BalanceOutput>
{
	private int balance;

	@Override
	public BalanceOutput fromEntity(User user)
	{
		balance = user.getBalance();
		return this;
	}
}

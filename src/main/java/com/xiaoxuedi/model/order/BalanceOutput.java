package com.xiaoxuedi.model.order;

import com.xiaoxuedi.entity.UsersEntity;
import com.xiaoxuedi.model.ModelFromEntity;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BalanceOutput implements ModelFromEntity<UsersEntity, BalanceOutput> {
    private BigDecimal balance;

	@Override
    public BalanceOutput fromEntity(UsersEntity user) {
		balance = user.getBalance();
		return this;
	}
}

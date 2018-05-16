package com.xiaoxuedi.model.address;

import com.xiaoxuedi.entity.Address;
import com.xiaoxuedi.model.ModelUpdateEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Validated
public class UpdateInput implements ModelUpdateEntity<Address>
{
	@NotNull
	private String id;

	@NotNull
	@Length(min = 2)
	private String address;

	@Override
	public void update(Address address)
	{
		address.setAddress(this.address);
	}
}

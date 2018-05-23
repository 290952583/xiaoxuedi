package com.xiaoxuedi.model.address;

import com.xiaoxuedi.entity.AddressEntity;
import com.xiaoxuedi.entity.UsersEntity;
import com.xiaoxuedi.model.ModelToEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Validated
public class AddInput implements ModelToEntity<AddressEntity>
{
	@NotNull
	@Length(min = 2)
	private String address;

	@Override
    public AddressEntity toEntity() {
        AddressEntity address = new AddressEntity();
        address.setAddress(getAddress());
        address.setUser(UsersEntity.getUser());
        return address;
	}
}

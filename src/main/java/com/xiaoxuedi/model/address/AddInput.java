package com.xiaoxuedi.model.address;

import com.xiaoxuedi.entity.AddressEntity;
import com.xiaoxuedi.entity.UsersEntity;
import com.xiaoxuedi.model.ModelToEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Validated
public class AddInput implements ModelToEntity<AddressEntity>
{
	@NotNull
	@Length(min = 2)
	private String address;

    @Length(min = 2, max = 255)
    private String details;

    @NotNull
    @Length(min = 1, max = 50)
    private String name;

    @NotNull
    @Length(min = 2, max = 20)
    private String phone;


    @Override
    public AddressEntity toEntity() {
        AddressEntity address = new AddressEntity();
        address.setAddress(getAddress());
        address.setUser(UsersEntity.getUser());
        address.setName(getName());
        address.setPhone(getPhone());
        address.setDetails(getDetails());
        address.setCreateBy(UsersEntity.getUser().getId());
        address.setOpBy(UsersEntity.getUser().getId());
        address.setCreateTime(new Date());
        address.setOpTime(new Date());
        address.setDeleteTime(0L);
        return address;
	}
}

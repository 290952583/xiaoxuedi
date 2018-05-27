package com.xiaoxuedi.model.address;

import com.xiaoxuedi.entity.AddressEntity;
import com.xiaoxuedi.model.ModelFromEntityList;
import lombok.Data;

@Data
public class ListOutput implements ModelFromEntityList<AddressEntity, ListOutput>
{
	private String id;
	private String name;
	private String phone;
	private String address;
	private String details;

	@Override
    public ListOutput fromEntity(AddressEntity address) {
		id = address.getId();
		this.address = address.getAddress();
		this.name = address.getName();
		this.phone = address.getPhone();
		this.details = address.getDetails();
		return this;
	}
}

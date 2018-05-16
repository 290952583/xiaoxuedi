package com.xiaoxuedi.model.address;

import com.xiaoxuedi.entity.Address;
import com.xiaoxuedi.model.ModelFromEntityList;
import lombok.Data;

@Data
public class ListOutput implements ModelFromEntityList<Address, ListOutput>
{
	private String id;
	private String address;

	@Override
	public ListOutput fromEntity(Address address)
	{
		id = address.getId();
		this.address = address.getAddress();
		return this;
	}
}

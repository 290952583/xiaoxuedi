package com.xiaoxuedi.model.address;

import com.xiaoxuedi.entity.AddressEntity;
import com.xiaoxuedi.model.ModelFromEntityList;
import lombok.Data;

@Data
public class ListOutput implements ModelFromEntityList<AddressEntity, ListOutput>
{
	private String id;
	private String address;

	@Override
    public ListOutput fromEntity(AddressEntity address) {
		id = address.getId();
		this.address = address.getAddress();
		return this;
	}
}

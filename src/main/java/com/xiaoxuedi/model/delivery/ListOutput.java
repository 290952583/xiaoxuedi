package com.xiaoxuedi.model.delivery;

import com.xiaoxuedi.entity.DeliveryEntity;
import com.xiaoxuedi.model.ModelFromEntityList;
import lombok.Data;

@Data
public class ListOutput implements ModelFromEntityList<DeliveryEntity,ListOutput> {

    private String id;
    private String name;


    @Override
    public ListOutput fromEntity(DeliveryEntity deliveryEntity) {
        this.id = deliveryEntity.getId();
        this.name = deliveryEntity.getName();
        return this;
    }
}

package com.xiaoxuedi.model.order;

import com.xiaoxuedi.entity.OrdersEntity;
import com.xiaoxuedi.model.PageInput;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Validated
public class StatusesInput extends PageInput
{
    @NotNull
    private OrdersEntity.Status[] statuses;
}

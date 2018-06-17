package com.xiaoxuedi.model.order.wx;

import com.xiaoxuedi.entity.OrdersEntity;
import com.xiaoxuedi.model.PageInput;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Validated
public class WxStatusesInput extends PageInput
{
    @NotNull
    private String userid;
    @NotNull
    private OrdersEntity.Status[] statuses;
}

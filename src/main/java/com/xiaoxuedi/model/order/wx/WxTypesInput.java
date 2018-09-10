package com.xiaoxuedi.model.order.wx;

import com.xiaoxuedi.entity.MissionEntity;
import com.xiaoxuedi.entity.OrdersEntity;
import com.xiaoxuedi.model.PageInput;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Validated
public class WxTypesInput extends PageInput
{
    @NotNull
    private String userid;
    @NotNull
    private OrdersEntity.Type[] orderTypes;
    @NotNull
    private MissionEntity.Status[] missionStatuses;
}

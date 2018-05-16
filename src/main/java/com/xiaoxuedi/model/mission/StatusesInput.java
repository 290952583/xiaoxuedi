package com.xiaoxuedi.model.mission;

import com.xiaoxuedi.entity.Mission;
import com.xiaoxuedi.model.PageInput;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Validated
public class StatusesInput extends PageInput
{
    @NotNull
    private Mission.Status[] statuses;
}

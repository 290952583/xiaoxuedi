package com.xiaoxuedi.model.address.wx;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Validated
public class WxDeleteInput
{
	@NotNull
	private String userid;
	@NotNull
	private String id;
}

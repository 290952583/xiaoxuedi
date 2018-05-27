package com.xiaoxuedi.model.mission;

import com.xiaoxuedi.entity.MissionEntity;
import com.xiaoxuedi.model.ModelUpdateEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Validated
public class UpdateInput implements ModelUpdateEntity<MissionEntity>
{
	@NotNull
	private String id;

	@NotNull
	@Length(min = 4, max = 255)
	private String title;

	@NotNull
	@Length(min = 4, max = 5120)
	private String description;

	@NotNull
    @Length(min = 2, max = 255)
	private String address;

	@NotNull
	@Min(0)
	private BigDecimal price;

	@Override
    public void update(MissionEntity mission) {
		mission.setAddress(address);
		mission.setPrice(price);
	}
}

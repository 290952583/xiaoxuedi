package com.xiaoxuedi.model.mission;

import com.xiaoxuedi.entity.MissionEntity;
import com.xiaoxuedi.model.ModelUpdateEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Validated
public class UpdateInput implements ModelUpdateEntity<MissionEntity>
{
	@NotNull
	private String id;


	@NotNull
    @Length(min = 2, max = 255)
	private String address;


	@Override
    public void update(MissionEntity mission) {
		mission.setAddress(address);
	}
}

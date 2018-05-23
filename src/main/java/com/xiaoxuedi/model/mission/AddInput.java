package com.xiaoxuedi.model.mission;

import com.xiaoxuedi.entity.MissionEntity;
import com.xiaoxuedi.entity.UsersEntity;
import com.xiaoxuedi.model.ModelToEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Validated
public class AddInput implements ModelToEntity<MissionEntity>
{
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
	private int price;

	@Override
    public MissionEntity toEntity() {
        MissionEntity mission = new MissionEntity();
        mission.setAddress(getAddress());
		mission.setPrice(getPrice());
        mission.setUser(UsersEntity.getUser());
        return mission;
	}
}

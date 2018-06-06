package com.xiaoxuedi.model.school;

import com.xiaoxuedi.entity.SchoolEntity;
import com.xiaoxuedi.entity.SchoolRegionEntity;
import com.xiaoxuedi.model.ModelToEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class AddRegionInput implements ModelToEntity<SchoolRegionEntity>
{
	@Length(min = 4, max = 255)
	private String id;

	@Length(min = 4, max = 255)
	private String region;

	@Length(min = 4, max = 255)
	private String schoolId;

	@Override
    public SchoolRegionEntity toEntity() {
		SchoolRegionEntity schoolRegion = new SchoolRegionEntity();
		schoolRegion.setSchoolId(this.schoolId);
		schoolRegion.setRegion(this.region);
		return schoolRegion;
	}

	public void update(SchoolRegionEntity schoolRegion){
		schoolRegion.setRegion(this.region);

	}
}

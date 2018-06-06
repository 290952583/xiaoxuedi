package com.xiaoxuedi.model.school;

import com.xiaoxuedi.entity.SchoolEntity;
import com.xiaoxuedi.entity.SchoolRegionEntity;
import com.xiaoxuedi.model.ModelFromEntityList;
import lombok.Data;

@Data
public class ListRegionOutput implements ModelFromEntityList<SchoolRegionEntity, ListRegionOutput>
{
	private String id;
	private String schoolId;
	private String region;

	@Override
    public ListRegionOutput fromEntity(SchoolRegionEntity schoolRegion) {
		id = schoolRegion.getId();
		this.schoolId = schoolRegion.getSchoolId();
		this.region = schoolRegion.getRegion();
		return this;
	}
}

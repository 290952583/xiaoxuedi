package com.xiaoxuedi.model.school;

import com.xiaoxuedi.entity.SchoolEntity;
import com.xiaoxuedi.model.ModelFromEntityList;
import lombok.Data;

@Data
public class ListOutput implements ModelFromEntityList<SchoolEntity, ListOutput>
{
	private String id;
	private String school;
	private String schoolPinyin;

	@Override
    public ListOutput fromEntity(SchoolEntity school) {
		id = school.getId();
		this.school = school.getSchool();
		this.schoolPinyin=school.getPinyin();
		return this;
	}
}

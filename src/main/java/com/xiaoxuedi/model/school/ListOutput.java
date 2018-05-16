package com.xiaoxuedi.model.school;

import com.xiaoxuedi.entity.School;
import com.xiaoxuedi.model.ModelFromEntityList;
import lombok.Data;

@Data
public class ListOutput implements ModelFromEntityList<School, ListOutput>
{
	private String id;
	private String school;

	@Override
	public ListOutput fromEntity(School school)
	{
		id = school.getId();
		this.school = school.getSchool();
		return this;
	}
}

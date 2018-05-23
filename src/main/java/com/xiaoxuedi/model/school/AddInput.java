package com.xiaoxuedi.model.school;

import com.xiaoxuedi.entity.SchoolEntity;
import com.xiaoxuedi.model.ModelToEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class AddInput implements ModelToEntity<SchoolEntity>
{
	@Length(min = 4, max = 255)
	private String school;

	@Override
    public SchoolEntity toEntity() {
        SchoolEntity school = new SchoolEntity();
        school.setSchool(this.school);
		return school;
	}
}

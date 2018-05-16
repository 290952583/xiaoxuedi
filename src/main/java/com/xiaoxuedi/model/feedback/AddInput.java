package com.xiaoxuedi.model.feedback;

import com.xiaoxuedi.entity.Feedback;
import com.xiaoxuedi.entity.User;
import com.xiaoxuedi.model.ModelToEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Validated
public class AddInput implements ModelToEntity<Feedback>
{
	@NotNull
	@Length(min = 10, max = 5120)
	private String feedback;

	@Override
	public Feedback toEntity()
	{
		Feedback feedback = new Feedback();
		feedback.setFeedback(getFeedback());
		feedback.setUser(User.getUser());
		return feedback;
	}
}

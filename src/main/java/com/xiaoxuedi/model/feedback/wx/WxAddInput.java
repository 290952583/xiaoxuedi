package com.xiaoxuedi.model.feedback.wx;

import com.xiaoxuedi.entity.FeedbackEntity;
import com.xiaoxuedi.entity.UsersEntity;
import com.xiaoxuedi.model.ModelToEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Validated
public class WxAddInput implements ModelToEntity<FeedbackEntity> {

    @NotNull
    private String userid;
    @NotNull
    @Length(min = 10, max = 5120)
    private String feedback;

    @Override
    public FeedbackEntity toEntity() {
        FeedbackEntity feedback = new FeedbackEntity();
        feedback.setFeedback(getFeedback());
        feedback.setUser(UsersEntity.getUser(userid));
        return feedback;
    }
}

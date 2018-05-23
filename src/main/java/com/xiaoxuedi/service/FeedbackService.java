package com.xiaoxuedi.service;

import com.xiaoxuedi.model.feedback.AddInput;
import com.xiaoxuedi.entity.FeedbackEntity;
import com.xiaoxuedi.model.Output;
import com.xiaoxuedi.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import static com.xiaoxuedi.model.Output.outputOk;
import static com.xiaoxuedi.model.Output.outputParameterError;

@Service
@Transactional
public class FeedbackService
{
    @Resource
    private FeedbackRepository feedbackRepository;

    public Output add(AddInput input)
    {
        FeedbackEntity feedback = feedbackRepository.save(input.toEntity());
        if (feedback == null)
        {
            return outputParameterError();
        }
        return outputOk();
    }
}

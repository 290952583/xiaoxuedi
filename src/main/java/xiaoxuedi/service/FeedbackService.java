package xiaoxuedi.service;

import xiaoxuedi.entity.Feedback;
import xiaoxuedi.model.Output;
import xiaoxuedi.model.feedback.AddInput;
import xiaoxuedi.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import static xiaoxuedi.model.Output.outputOk;
import static xiaoxuedi.model.Output.outputParameterError;

@Service
@Transactional
public class FeedbackService
{
    @Resource
    private FeedbackRepository feedbackRepository;

    public Output add(AddInput input)
    {
        Feedback feedback = feedbackRepository.save(input.toEntity());
        if (feedback == null)
        {
            return outputParameterError();
        }
        return outputOk();
    }
}

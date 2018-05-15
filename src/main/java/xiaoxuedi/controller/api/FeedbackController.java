package xiaoxuedi.controller.api;

import xiaoxuedi.model.Output;
import xiaoxuedi.model.feedback.AddInput;
import xiaoxuedi.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/feedback")
public class FeedbackController extends AbstractController
{
    @Autowired
    FeedbackService feedbackService;

    @PostMapping("add")
    public Output add(@Valid @RequestBody AddInput input)
    {
        return feedbackService.add(input);
    }
}

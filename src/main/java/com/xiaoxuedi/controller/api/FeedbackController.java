package com.xiaoxuedi.controller.api;

import com.xiaoxuedi.model.feedback.AddInput;
import com.xiaoxuedi.service.FeedbackService;
import com.xiaoxuedi.model.Output;
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

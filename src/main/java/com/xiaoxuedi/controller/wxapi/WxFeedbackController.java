package com.xiaoxuedi.controller.wxapi;

import com.xiaoxuedi.controller.api.AbstractController;
import com.xiaoxuedi.model.Output;
import com.xiaoxuedi.model.feedback.AddInput;
import com.xiaoxuedi.model.feedback.wx.WxAddInput;
import com.xiaoxuedi.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("wxapi/feedback")
public class WxFeedbackController extends AbstractController
{
    @Autowired
    FeedbackService feedbackService;

    @PostMapping("add")
    public Output add(@Valid @RequestBody WxAddInput input)
    {
        return feedbackService.add(input);
    }
}

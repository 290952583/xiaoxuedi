package com.xiaoxuedi.controller.wxapi;

import com.xiaoxuedi.controller.api.AbstractController;
import com.xiaoxuedi.model.Output;
import com.xiaoxuedi.model.mission.*;
import com.xiaoxuedi.model.mission.wx.WxAddInput;
import com.xiaoxuedi.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("wxapi/mission")
public class WxMissionController extends AbstractController
{
    @Autowired
    private MissionService missionService;

    /**
     * 新增代取快递任务
     * @param input
     * @return
     */
    @PostMapping("add")
    public Output add(@Valid @RequestBody WxAddInput input)
    {
        return missionService.wxAdd(input);
    }

    @PostMapping("delete")
    public Output delete(@Valid @RequestBody DeleteInput input)
    {
        return missionService.delete(input);
    }

    @PostMapping("update")
    public Output update(@Valid @RequestBody UpdateInput input)
    {
        return missionService.update(input);
    }

    @PostMapping("finish")
    public Output finish(@Valid @RequestBody FinishInput input)
    {
        return missionService.finish(input);
    }

    @PostMapping("accept")
    public Output accept(@Valid @RequestBody AcceptInput input)
    {
        return missionService.accept(input);
    }

    @PostMapping("cancel")
    public Output cancel(@Valid @RequestBody CancelInput input)
    {
        return missionService.cancel(input);
    }

    @PostMapping("acceptCancel")
    public Output acceptCancel(@Valid @RequestBody AcceptCancelInput input)
    {
        return missionService.acceptCancel(input);
    }

    @GetMapping("myList")
    public Output myList(@Valid StatusesInput input)
    {
        return missionService.myList(input);
    }

    @GetMapping("acceptList")
    public Output acceptList(@Valid StatusesInput input)
    {
        return missionService.acceptList(input);
    }

    @GetMapping("nearby")
    public Output nearby(@Valid NearbyInput input)
    {
        return missionService.nearby(input);
    }
}

package com.xiaoxuedi.controller.api;

import com.xiaoxuedi.entity.User;
import com.xiaoxuedi.model.Output;
import com.xiaoxuedi.model.account.AuthInput;
import com.xiaoxuedi.model.account.RegisterInput;
import com.xiaoxuedi.model.account.UserInfoOutput;
import com.xiaoxuedi.service.AccountService;
import com.xiaoxuedi.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

import static com.xiaoxuedi.model.Output.*;

@RestController
@RequestMapping("api/account")
@Slf4j
public class AccountController extends AbstractController
{
    @Autowired
    private AccountService accountService;
    @Autowired
    private SmsService smsService;

    @GetMapping("login")
    public Output login(@RequestParam(value = "reason", required = false) String reason)
    {
        log.info("登录信息："+reason);
        if (reason == null || reason.isEmpty())
        {
            String mobile = smsService.getVerificationMobile();
            if (mobile != null && accountService.findUserByMobile(mobile) == null)
            {
                return outputNotRegister();
            }
            return outputNotLogin();
        }

        switch (reason.toLowerCase())
        {
            case "maxsessions":
                return outputMaxSessions();
            default:
                String mobile = smsService.getVerificationMobile();
                if (mobile != null && accountService.findUserByMobile(mobile) == null)
                {
                    return outputNotRegister();
                }
                return outputNotLogin();
        }
    }

    @PostMapping("loginSuccess")
    public Output loginSuccess()
    {
        return accountService.getUserInfo(User.getUserId());
    }

    @PostMapping("logoutSuccess")
    public Output logoutSuccess()
    {
        return outputOk();
    }

    @PostMapping("register")
    public Output register(@Valid @RequestBody RegisterInput input, HttpSession seesion)
    {
        seesion.setAttribute("verificationMobile", "18201265103");
        String mobile = smsService.getVerificationMobile();
        if (mobile == null || !mobile.equals(input.getMobile()))
        {
            return outputParameterError();
        }

        Output<UserInfoOutput> result = accountService.register(input);

        if (result.getCodeInfo() == Output.Code.OK)
        {
            autoLogin(input.getMobile());
        }

        return result;
    }

    @PostMapping("auth")
    public Output auth(@Valid AuthInput input) throws IOException
    {
        return accountService.auth(input);
    }

    @GetMapping({"getAuthStatus", "getAuthStatus/{id}"})
    public Output getAuthStatus(@PathVariable(value = "id", required = false) String id)
    {
        if (id == null)
        {
            id = User.getUserId();
        }
        return accountService.getAuthStatus(id);
    }

    @GetMapping({"getUserInfo", "getUserInfo/{id}"})
    public Output getUserInfo(@PathVariable(value = "id", required = false) String id)
    {
        if (id == null)
        {
            id = User.getUserId();
        }

        return accountService.getUserInfo(id);
    }

    @GetMapping("getInvitationCode")
    public Output getInvitationCode()
    {
        return accountService.getInvitationCode();
    }

    @GetMapping({"getUserAvatar", "getUserAvatar/{id}"})
    public void getUserAvatar(@PathVariable(value = "id", required = false) String id, ServletResponse response) throws IOException
    {
        if (id == null)
        {
            id = User.getUserId();
        }
        byte[] data = accountService.getUserAvatar(id);
        response.setContentType("image/png");
        response.getOutputStream().write(data);
    }

    @PostMapping("setUserAvatar")
    public Output setUserAvatar(@RequestParam("file") MultipartFile file) throws IOException
    {
        if (file.isEmpty())
        {
            return outputParameterError();
        }
        byte[] data = file.getBytes();
        return accountService.setUserAvatar(data);
    }
}

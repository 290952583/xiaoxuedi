package com.xiaoxuedi.controller.api;

import com.xiaoxuedi.entity.UsersEntity;
import com.xiaoxuedi.model.Output;
import com.xiaoxuedi.model.account.AuthInput;
import com.xiaoxuedi.model.account.RegisterInput;
import com.xiaoxuedi.model.account.UserInfoOutput;
import com.xiaoxuedi.service.AccountService;
import com.xiaoxuedi.service.SmsService;
import com.xiaoxuedi.util.QiniuUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
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
    @Autowired
    private QiniuUtil qiniuUtil;

    /*@GetMapping("login")
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
    }*/

    @GetMapping("login")
    public Output login(@RequestParam(value = "reason", required = false) String reason,
                        @RequestParam(value = "username")String username)
    {
        log.info("登录信息："+reason);
        if (reason == null || reason.isEmpty())
        {
            UsersEntity user = accountService.findUserByMobile(username);
            if (user == null)
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
                UsersEntity user = accountService.findUserByMobile(username);
                if (user == null)
                {
                    return outputNotRegister();
                }
                return outputNotLogin();
        }
    }

    @PostMapping("loginSuccess")
    public Output loginSuccess()
    {
        return accountService.getUserInfo(UsersEntity.getUserId());
    }

    @PostMapping("logoutSuccess")
    public Output logoutSuccess()
    {
        return outputOk();
    }

    @PostMapping("register")
    public Output register(@Valid @RequestBody RegisterInput input, HttpSession seesion)
    {
        seesion.setAttribute("verificationMobile", "18780662586");
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
            id = UsersEntity.getUserId();
        }
        return accountService.getAuthStatus(id);
    }

    @GetMapping({"getUserInfo", "getUserInfo/{id}"})
    public Output getUserInfo(@PathVariable(value = "id", required = false) String id)
    {
        if (id == null)
        {
            id = UsersEntity.getUserId();
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
            id = UsersEntity.getUserId();
        }
        String imgUrl = accountService.getUserAvatar(id);
//        response.setContentType("image/png");
//        response.getOutputStream().write(imgUrl);
        //待修改
    }

    @PostMapping("setUserAvatar")
    public Output setUserAvatar(@RequestParam("file") MultipartFile file) throws IOException
    {
        if (file.isEmpty())
        {
            return outputParameterError();
        }

        File f = null;
        try {
            f=File.createTempFile("tmp", null);
            file.transferTo(f);
            f.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        QiniuUtil qiniuUtil = new QiniuUtil();
        String imgUrl = qiniuUtil.UploadFile(f);

//        String imgUrl = "";//未设置值，待修改
        return accountService.setUserAvatar(imgUrl);
    }
    
    
    
    
}

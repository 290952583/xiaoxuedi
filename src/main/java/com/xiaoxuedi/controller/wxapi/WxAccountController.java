package com.xiaoxuedi.controller.wxapi;

import com.xiaoxuedi.Application;
import com.xiaoxuedi.controller.api.AbstractController;
import com.xiaoxuedi.entity.SchoolEntity;
import com.xiaoxuedi.entity.UsersEntity;
import com.xiaoxuedi.model.Output;
import com.xiaoxuedi.model.account.AuthInput;
import com.xiaoxuedi.model.account.RegisterInput;
import com.xiaoxuedi.model.account.UserInfoOutput;
import com.xiaoxuedi.model.account.wx.WxSessionInput;
import com.xiaoxuedi.model.account.wx.WxSessionOutput;
import com.xiaoxuedi.model.school.AddInput;
import com.xiaoxuedi.repository.SchoolRepository;
import com.xiaoxuedi.service.AccountService;
import com.xiaoxuedi.service.SchoolService;
import com.xiaoxuedi.service.SmsService;
import com.xiaoxuedi.util.QiniuUtil;
import com.xiaoxuedi.util.Wxmini;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.xiaoxuedi.model.Output.*;

@RestController
@RequestMapping("wxapi/account")
@Slf4j
public class WxAccountController extends AbstractController {
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

    @PostMapping("wxregister")
    public Output wxRegister(@Valid @RequestBody WxSessionInput wxSessionInput) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        String wxSessionurl = "https://api.weixin.qq.com/sns/jscode2session?appid=wx9f802861583f5202&secret=b84a685bcdf7a10dcf9dff66f334f6ec&grant_type=authorization_code&js_code=" + wxSessionInput.getCode();
        Request request = builder.url(wxSessionurl).get().build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {

                GsonJsonParser gsonJsonParser = new GsonJsonParser();
                RegisterInput registerInput = new RegisterInput();
                SchoolRepository schoolRepository = Application.getBean(SchoolRepository.class);

                List<SchoolEntity> list = schoolRepository.findAll();
                if (list.size() == 0)
                {
                    SchoolService schoolService = Application.getBean(SchoolService.class);
                    AddInput input = new AddInput();
                    input.setSchool("西南大学");
                    schoolService.add(input);
                    list = schoolRepository.findAll();
                }
                Map<String ,Object > sessionMap=gsonJsonParser.parseMap(response.body().string());

                registerInput.setOpenId((String) sessionMap.get("openid"));
                registerInput.setSchoolId(list.get(0).getId());
                registerInput.setSessionKey((String) sessionMap.get("session_key"));
                Map<String ,Object >   userinfoMap = gsonJsonParser.parseMap(Wxmini.getUserInfo(wxSessionInput.getUserInfoEncryptedData(),(String) sessionMap.get("session_key"), wxSessionInput.getUserInfoIv()));

                registerInput.setUsername((String) userinfoMap.get("nickName"));
                //由于手机号不能为空,这里随便填写一个手机号
                registerInput.setMobile("13011111111");
                registerInput.setSex( (Double)(userinfoMap.get("gender")) ==1?"1":(int)(userinfoMap.get("gender")) ==2?"2":"0");
                registerInput.setPassword(((String) sessionMap.get("openid")).substring(6));
                Output<WxSessionOutput> result = accountService.wxRegister(registerInput,wxSessionInput);
                return result;
            } else
                return outputParameterError();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return outputParameterError();
    }

    @PostMapping("loginSuccess")
    public Output loginSuccess() {
        return accountService.getUserInfo(UsersEntity.getUserId());
    }

    @PostMapping("logoutSuccess")
    public Output logoutSuccess() {
        return outputOk();
    }

    @PostMapping("register")
    public Output register(@Valid @RequestBody RegisterInput input, HttpSession seesion) {
//        seesion.setAttribute("verificationMobile", "18780662586");
        String mobile = smsService.getVerificationMobile();
        if (mobile == null || !mobile.equals(input.getMobile())) {
            return outputParameterError();
        }

        Output<UserInfoOutput> result = accountService.register(input);

        if (result.getCodeInfo() == Code.OK) {
            autoLogin(input.getMobile());
        }

        return result;
    }

    @PostMapping("auth")
    public Output auth(@Valid AuthInput input) throws IOException {
        return accountService.auth(input);
    }

    @GetMapping({"getAuthStatus", "getAuthStatus/{id}"})
    public Output getAuthStatus(@PathVariable(value = "id", required = false) String id) {
        if (id == null) {
            id = UsersEntity.getUserId();
        }
        return accountService.getAuthStatus(id);
    }

    @GetMapping({"getUserInfo", "getUserInfo/{id}"})
    public Output getUserInfo(@PathVariable(value = "id", required = false) String id) {
        if (id == null) {
            id = UsersEntity.getUserId();
        }

        return accountService.getUserInfo(id);
    }

    @GetMapping("getInvitationCode")
    public Output getInvitationCode() {
        return accountService.getInvitationCode();
    }

    @GetMapping({"getUserAvatar", "getUserAvatar/{id}"})
    public void getUserAvatar(@PathVariable(value = "id", required = false) String id, ServletResponse response) throws IOException {
        if (id == null) {
            id = UsersEntity.getUserId();
        }
        String imgUrl = accountService.getUserAvatar(id);
//        response.setContentType("image/png");
//        response.getOutputStream().write(imgUrl);
        //待修改
    }

    @PostMapping("setUserAvatar")
    public Output setUserAvatar(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return outputParameterError();
        }

        File f = null;
        try {
            f = File.createTempFile("tmp", null);
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

package com.xiaoxuedi.service;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.Userinfos;
import com.taobao.api.request.OpenimUsersAddRequest;
import com.taobao.api.request.OpenimUsersUpdateRequest;
import com.xiaoxuedi.config.TaobaoProperties;
import com.xiaoxuedi.entity.ImageEntity;
import com.xiaoxuedi.entity.UsersEntity;
import com.xiaoxuedi.model.Output;
import com.xiaoxuedi.model.account.*;
import com.xiaoxuedi.repository.ImageRepository;
import com.xiaoxuedi.repository.UserRepository;
import com.xiaoxuedi.util.GenerateRandomSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Collections;

import static com.xiaoxuedi.model.Output.*;

@Service
@Transactional
public class AccountService
{
    @Resource
    private UserRepository userRepository;
    @Resource
    private ImageRepository imageRepository;
    @Autowired
    private GenerateRandomSequence randomSequence;
    @Resource
    private TaobaoProperties taobaoProperties;

    public UsersEntity findUserByMobile(String mobile)
    {
        return userRepository.findByMobile(mobile);
    }

    public Output<UserInfoOutput> register(RegisterInput input)
    {
        UsersEntity user = userRepository.findByMobile(input.getMobile());
        if (user != null)
        {
            return outputUserExists();
        }

        user = userRepository.save(input.toEntity());

        //阿里云旺
        TaobaoClient client = new DefaultTaobaoClient(taobaoProperties.getUrl(), taobaoProperties.getAppKey(), taobaoProperties.getSecret());

        OpenimUsersAddRequest request = new OpenimUsersAddRequest();
        Userinfos userinfos = new Userinfos();
        userinfos.setNick(user.getUsername());
        userinfos.setIconUrl(taobaoProperties.getIconUrl() + user.getMobile());
        userinfos.setUserid(user.getMobile());
        userinfos.setPassword(taobaoProperties.getPassword());
        request.setUserinfos(Collections.singletonList(userinfos));

        try
        {
            client.execute(request);
        }
        catch (ApiException e)
        {
            e.printStackTrace();
        }

        // 修改发送邀请码者数据
        if (input.getInvitationCode() != null)
        {
            userRepository.addInvitationCount(input.getInvitationCode());
        }

        return output(new UserInfoOutput().fromEntity(userRepository.findOne(user.getId())));
    }

    public Output auth(AuthInput input) throws IOException
    {
        UsersEntity user = userRepository.getCurrentUser();
        user.setName(input.getName());
        user.setIdCard(input.getIdCard());
        // TODO 测试阶段直接通过
        // user.setAuthStatus(User.AuthStatus.WAIT);
        user.setAuthStatus(UsersEntity.AuthStatus.PASS);
        userRepository.save(user);

        ImageEntity image = new ImageEntity();

        image.setName("Auth/" + user.getId() + "/1");
        image.setImgurl(input.getImg());
        imageRepository.save(image);

        image.setName("Auth/" + user.getId() + "/2");
        image.setImgurl(input.getImg());
        imageRepository.save(image);

        return outputOk();
    }

    public Output<AuthOutput> getAuthStatus(String id)
    {
        UsersEntity user = userRepository.findOne(id);
        if (user == null)
        {
            return outputParameterError();
        }
        AuthOutput output = new AuthOutput().fromEntity(user);
        return output(output);
    }

    public Output<UserInfoOutput> getUserInfo(String id)
    {
        UsersEntity user = userRepository.getOne(id);
        if (user == null)
        {
            return outputParameterError();
        }

        return output(new UserInfoOutput().fromEntity(user));
    }

    public Output<InvitationCodeOutput> getInvitationCode()
    {
        UsersEntity user = userRepository.getCurrentUser();
        if (user.getInvitationCode() == null)
        {
            user.setInvitationCode(randomSequence.getRandomUppercaseNumber());
            userRepository.save(user);
        }
        InvitationCodeOutput output = new InvitationCodeOutput().fromEntity(user);
        return output(output);
    }

    public String getUserAvatar(String UserId)
    {
        ImageEntity image = imageRepository.findOne("UserAvatar/" + UserId);
        if (image == null)
        {
            image = imageRepository.findOne("UserAvatar");
        }
        return image.getImgurl();
    }

    public Output setUserAvatar(String imgurl)
    {
        ImageEntity image = new ImageEntity();
        image.setName("UserAvatar/" + UsersEntity.getUserId());
        image.setImgurl(imgurl);
        imageRepository.save(image);

        TaobaoClient client = new DefaultTaobaoClient(taobaoProperties.getUrl(), taobaoProperties.getAppKey(), taobaoProperties.getSecret());

        OpenimUsersUpdateRequest request = new OpenimUsersUpdateRequest();
        Userinfos userinfos = new Userinfos();
        userinfos.setIconUrl(taobaoProperties.getIconUrl() + userRepository.getCurrentUser()
                .getMobile());
        request.setUserinfos(Collections.singletonList(userinfos));

        try
        {
            client.execute(request);
        }
        catch (ApiException e)
        {
            e.printStackTrace();
        }

        return outputOk();
    }
}

package com.xiaoxuedi.model.account;

import cn.jiguang.common.utils.Nullable;
import com.xiaoxuedi.entity.SchoolEntity;
import com.xiaoxuedi.entity.UsersEntity;
import com.xiaoxuedi.model.ModelToEntity;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Data
@Validated
public class RegisterInput implements ModelToEntity<UsersEntity>
{
	//用于标记微信小程序的用户的身份id
	@Nullable
	private String openId;

	//用于标记微信小程序的用户登录的以及对用户数据进行加密签名的密钥
	@Nullable
	private String sessionKey;

	@NotNull
	private String username;

	@NotNull
	private String password;

	@NotNull
	@Pattern(regexp = "^(13[0-9]|14[579]|15[0-3,5-9]|17[0135678]|18[0-9])\\d{8}$")
	private String mobile;

	// 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	@NotNull
    private String sex;

	@NotNull
	private String schoolId;

	private String invitationCode;

	@Override
    public UsersEntity toEntity() {
        UsersEntity user = new UsersEntity();
        user.setOpenId(openId);
        user.setSessionKey(sessionKey);
        user.setThirdSessionKey(new StringBuffer(sessionKey).reverse().toString());
        user.setUsername(username);
        user.setPassword(password);
		user.setMobile(mobile);
		user.setSex(sex);
        SchoolEntity school = new SchoolEntity();
        school.setId(schoolId);
		user.setSchool(school);
		user.setBalance(new BigDecimal("0"));
		return user;
	}
}

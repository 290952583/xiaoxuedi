package com.xiaoxuedi.model.coupon.wx;

import com.xiaoxuedi.entity.AddressEntity;
import com.xiaoxuedi.entity.UsersEntity;
import com.xiaoxuedi.model.ModelToEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Validated
public class WxCouponInput {
    @NotNull
    private String userid;





}

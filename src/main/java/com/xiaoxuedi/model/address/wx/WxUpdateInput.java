package com.xiaoxuedi.model.address.wx;

import com.xiaoxuedi.entity.AddressEntity;
import com.xiaoxuedi.entity.UsersEntity;
import com.xiaoxuedi.model.ModelUpdateEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Validated
public class WxUpdateInput implements ModelUpdateEntity<AddressEntity> {
    @NotNull
    private String userid;

    @NotNull
    private String id;

    @NotNull
    @Length(min = 2)
    private String address;

    @Length(min = 2, max = 255)
    private String details;

    @NotNull
    @Length(min = 1, max = 50)
    private String name;

    @NotNull
    @Length(min = 2, max = 20)
    private String phone;

    @Override
    public void update(AddressEntity address) {
        address.setAddress(this.address);
        address.setName(this.name);
        address.setPhone(this.phone);
        address.setDetails(this.details);
        address.setOpBy(userid);
        address.setOpTime(new Date());
    }
}

package com.xiaoxuedi.model.account.wx;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
public class WxSessionOutput {

        String openid;
        String third_session_key;
        String union_id;
}

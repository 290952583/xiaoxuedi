package com.xiaoxuedi.model.account.wx;

import lombok.Data;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
@Data
@Validated
public class WxSessionInput {

        String code;
        String userInfoIv;
        String userInfoEncryptedData;
//        String phoneIv;
//        String phoneEncryptedData;

}

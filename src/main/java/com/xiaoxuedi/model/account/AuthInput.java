package com.xiaoxuedi.model.account;

import lombok.Data;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

@Data
@Validated
public class AuthInput
{
    String name;
    String idCard;
    String img;
    MultipartFile file1;
    MultipartFile file2;
}

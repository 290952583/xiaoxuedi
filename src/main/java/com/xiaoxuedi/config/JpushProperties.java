package com.xiaoxuedi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: xuxin
 * @Description: 极光推送
 * @Date: created in 10:48 2018/5/16
 * @Company:
 * @Project:
 * @Modified By:
 */

@Data
@ConfigurationProperties(prefix = "jpush")
public class JpushProperties {

    // 极光推送AppKey
    private String JpushAppKey;
    // 极光推送AppSecret
    private String JpushAppSecret;

}

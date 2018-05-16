package com.xiaoxuedi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: xuxin
 * @Description: 七牛云存储
 * @Date: created in 10:48 2018/5/16
 * @Company:
 * @Project:
 * @Modified By:
 */

@Data
@ConfigurationProperties(prefix = "qiniu")
public class QiniuProperties {

    //空间名
    private String scope;
    //域名
    private String url;
    //公钥
    private String access_key;
    //秘钥
    private String secret_key;
}

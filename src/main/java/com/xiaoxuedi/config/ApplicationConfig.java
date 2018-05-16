package com.xiaoxuedi.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({SmsProperties.class, PingxxProperties.class, TaobaoProperties.class,AlipayProperties.class,WxPayProperties.class,
                                QiniuProperties.class,JpushProperties.class})
public class ApplicationConfig
{
}

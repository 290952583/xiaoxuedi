package com.xiaoxuedi.config;

import com.github.wxpay.sdk.WXPayConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Data
@ConfigurationProperties(prefix = "wxPay")
public class WxPayProperties implements WXPayConfig {

    private byte[] certData;

    public WxPayProperties() throws Exception {
//        String certPath = ResourceUtils.CLASSPATH_URL_PREFIX+"";
//        File file = new File(certPath);
        File file = ResourceUtils.getFile("classpath:apiclient_cert.p12");
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
    }
    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }
    public String appID;

    public String mchID;

    public String key;

    public String notifyUrl;

    public int httpConnectTimeoutMs;


    public int httpReadTimeoutMs;


}


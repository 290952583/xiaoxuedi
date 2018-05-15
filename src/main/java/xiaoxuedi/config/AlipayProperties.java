package xiaoxuedi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "alipay")
public class AlipayProperties {

    private String serverUrl;
    private String notifyUrl;
    private String appId;
    private String appPrivateKey;
    private String charset;
    private String alipayPublicKey;
    private String timeout;

}

package xiaoxuedi.model.pay;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Validated
public class AppOrderInput {

    @NotNull
    private  String totalAmount;//订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
    @NotNull
    private  String body;//对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body
    @NotNull
    private  String subject;//商品的标题/交易标题/订单标题/订单关键字等。
    @NotNull
    private  String outTradeNo;//商户网站唯一订单号

    private  String storeId;//商户门店编号。该参数用于请求参数中以区分各门店，非必传项
}

package xiaoxuedi.servlet;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import org.springframework.beans.factory.annotation.Autowired;
import xiaoxuedi.Application;
import xiaoxuedi.config.AlipayProperties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@WebServlet(name="alipayNotify",urlPatterns="/servlet/alipayNotify")
public class AlipayNotifyServlet extends HttpServlet {

    private static final long serialVersionUID = 8031133938454140403L;
    @Autowired
    private AlipayProperties alipayProperties;
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpServletRequest request = Application.getRequest();

        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        //切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
        //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
        try {
            boolean flag = AlipaySignature.rsaCheckV1(params, alipayProperties.getAlipayPublicKey(), alipayProperties.getCharset(),"RSA2");

            String outTradeNo=params.get("out_trade_no").toString();//订单编号
            //更新订单状态
                    /*WAIT_BUYER_PAY	交易创建，等待买家付款
                      TRADE_CLOSED	未付款交易超时关闭，或支付完成后全额退款
                      TRADE_SUCCESS	交易支付成功
                      TRADE_FINISHED	交易结束，不可退款*/
            if("WAIT_BUYER_PAY".equals(params.get("trade_status").toString())){

            }else if("TRADE_CLOSED".equals(params.get("trade_status").toString())){

            }else if("TRADE_SUCCESS".equals(params.get("trade_status").toString())){

            }else if("TRADE_FINISHED".equals(params.get("trade_status").toString())){

            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

    }
}
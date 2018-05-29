package com.xiaoxuedi.servlet;

import com.xiaoxuedi.service.PayService;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

@WebServlet(name = "wxpayNotify", urlPatterns = "/servlet/wxpayNotify")
@Slf4j
public class WxPayNotifyServlet extends HttpServlet {

	/**
	 * 微信支付异步通知逻辑处理
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private PayService payService;

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String resXml = "";
		log.info("进入wxPay异步通知");
		String resultMsg = "";
		// HttpServletRequest req = Application.getRequest();
		try {
			// 获取wx过来反馈信息
			InputStream is = req.getInputStream();
			// 将InputStream转换成String
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();
			String line = null;
			try {
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			resXml = sb.toString();
			log.info(resXml);
			resultMsg = payService.payBack(resXml);

		} catch (Exception e) {
			log.error("手机支付回调通知失败", e);
			resultMsg = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
					+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";

		} finally {
			PrintWriter out = resp.getWriter();
			out.write(resultMsg);
			out.flush();
			out.close();
		}
	}

}

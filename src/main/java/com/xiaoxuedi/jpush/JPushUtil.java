package com.xiaoxuedi.jpush;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import com.xiaoxuedi.config.JpushProperties;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * JPush 工具类
 *
 * @author xuxin
 * @company
 * @project xiaoxuedi
 * @date 2018-01-26 11:00
 *
 **/
@Slf4j
public class JPushUtil {

    @Resource
    private JpushProperties jpushProperties;

    private final ExecutorService executor = Executors.newCachedThreadPool();

    /**
     * 根据设备标记集合推送相关消息
     *
     * @param collection
     * @param notificationMsg
     * @param message
     * @return
     */
    public static PushPayload buildPushObject_all_registrationId_alert(Collection collection, String notificationMsg, String message) {

        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.registrationId(collection))
                .setNotification(Notification.alert(notificationMsg))
                .setMessage(Message.content(message))
                .build();
    }

    /**
     * 推送接口
     *
     * @param payload
     * @return
     */
    public String sendJPush(PushPayload payload) {
        String JpushAppKey = jpushProperties.getJpushAppKey();
        String JpushAppSecret = jpushProperties.getJpushAppSecret();

        JPushClient jpushClient = new JPushClient(JpushAppSecret, JpushAppKey, null, ClientConfig.getInstance());

        // For push, all you need do is to build PushPayload object.
//        PushPayload payload = buildPushObject_all_all_alert();

        try {
            PushResult result = jpushClient.sendPush(payload);
            log.info("Got result - " + result);

        } catch (APIConnectionException e) {
            // Connection error, should retry later
            log.error("Connection error, should retry later", e);

        } catch (APIRequestException e) {
            // Should review the error, and fix the request
            log.error("Should review the error, and fix the request", e);
            log.info("HTTP Status: " + e.getStatus());
            log.info("Error Code: " + e.getErrorCode());
            log.info("Error Message: " + e.getErrorMessage());
        }

        return "调用成功";
    }

    public void sendJPushAsync(PushPayload payload) {
        sendJPushAsync(payload, JPushCallback.NOP_JPUSH_CALLBACK);
    }

    public void sendJPushAsync(PushPayload payload, JPushCallback callback) {
        executor.execute(() -> {
            try {
                String result = sendJPush(payload);
                if (callback != null) callback.call(true, result, null);
            } catch (Exception e) {
                if (callback != null) callback.call(false, null, e);
            }
        });
    }

    public void shutdown() {
        executor.shutdown();
    }

}

package com.xiaoxuedi.jpush;

/**
 * JPush 消息发送回调接口
 *
 * @author xuxin
 * @company
 * @project xiaoxuedi
 * @date 2018-01-26 11:00
 *
 **/
public interface JPushCallback {

    /**
     * None of Operation JPush Callback
     */
    JPushCallback NOP_JPUSH_CALLBACK = (success, result, exception) -> {
    };

    /**
     * 发送完成回调的函数
     *
     * @param success   是否成功
     * @param result    发送结果
     * @param exception 发送异常
     */
    void call(boolean success, String result, Exception exception);

}

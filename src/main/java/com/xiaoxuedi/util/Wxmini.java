package com.xiaoxuedi.util;

import com.google.gson.JsonParser;
import org.json.JSONObject;
import org.springframework.boot.json.GsonJsonParser;

import java.util.HashMap;
import java.util.Map;

public class Wxmini {
    public static String getUserInfo(String encryptedData, String session_key, String iv) {

        try {
            String result = AesCbcUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
            if (null != result && result.length() > 0) {

                return result;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
      return "";
    }

}

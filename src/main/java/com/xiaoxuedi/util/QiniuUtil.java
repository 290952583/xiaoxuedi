package com.xiaoxuedi.util;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.xiaoxuedi.config.QiniuProperties;

import javax.annotation.Resource;
import java.io.File;
import java.util.UUID;

/**
 * @Author: xuxin
 * @Description: 七牛云存储工具类
 * @Date: created in 23:20 2018/5/15
 * @Company:
 * @Project: xiaoxuedi
 * @Modified By:
 *
 */
public class QiniuUtil {

        @Resource
        private QiniuProperties qiniuProperties;

        private static String Scope = "";
        private static String Url = "";
        private static String ACCESS_KEY = "";
        private static String SECRET_KEY = "";

//       static {
//           Scope = qiniuProperties.getScope();
//           Url = qiniuProperties.getUrl();
//           ACCESS_KEY = qiniuProperties.getAccess_key();
//           SECRET_KEY = qiniuProperties.getSecret_key();
//       }


        /**
         * 上传附件
         *
         * @throws QiniuException
         */
    public String UploadFile(File file) throws QiniuException {
        String key = UUID.randomUUID().toString().replaceAll("-", "");
            //构造一个带指定Zone对象的配置类
            Configuration cfg = new Configuration(Zone.zone2());
            //创建上传对象
            UploadManager uploadManager = new UploadManager(cfg);
            //密钥配置
            Auth auth = Auth.create(qiniuProperties.getScope(), qiniuProperties.getUrl());
            //上传文件
            Response res = uploadManager.put(file, key, auth.uploadToken(qiniuProperties.getScope(), key));
        return key;
    }

    /**
     * 上传文件并指定文件名
     *
     * @param file 文件
     * @param name 文件名
     * @return 文件名
     * @throws QiniuException 七牛上传异常
     */
    public String upload(File file, String name) throws QiniuException {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        //创建上传对象
        UploadManager uploadManager = new UploadManager(cfg);
        //密钥配置
        Auth auth = Auth.create(qiniuProperties.getAccess_key(), qiniuProperties.getSecret_key());
        //上传文件
        uploadManager.put(file, name, auth.uploadToken(qiniuProperties.getScope(), name));
        return name;
    }

    /**
     * 获得url地址
     */
    public String GetUrl(String key) {
        return String.format("https://%s/%s", qiniuProperties.getUrl(), key);
    }


}

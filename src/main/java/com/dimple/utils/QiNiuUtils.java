package com.dimple.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import jdk.nashorn.internal.runtime.FindProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * @ClassName: QiNiuUtils
 * @Description:
 * @Auther: Owenb
 * @Date: 11/20/18 18:55
 * @Version: 1.0
 */
@PropertySource({"classpath:properties/qiniu.properties"})
public class QiNiuUtils {

    private static Logger logger = LoggerFactory.getLogger(QiNiuUtils.class);
    //设置账号的ACCESS_KEY和SECRET_KEY
    @Value("${qiniu.access_key}")
    private static final String ACCESS_KEY = "";
    @Value("${quniu.secret_key}")
    private static final String SECRET_KEY = "";
    //要上传的空间
    @Value("qiniu.bucket_name")
    private static final String BUCKET_NAME = "";
    //秘钥配置
    private static final Auth AUTH = Auth.create(ACCESS_KEY, SECRET_KEY);
    private static final Zone ZONE = Zone.autoZone();
    private static final Configuration CONFIGURATION = new Configuration(ZONE);
    //创建上传对象
    private static UploadManager uploadManager = new UploadManager(CONFIGURATION);

    //简单上传，使用默认的策略，只需要设置上传的空间名
    private static String getUpToken() {
        return AUTH.uploadToken(BUCKET_NAME);
    }

    /**
     * 七牛云上传文件
     *
     * @param fileName 上传后的文件名
     * @param filePath 需要上传的文件路径
     * @return
     */
    public static StringMap upload(String fileName, String filePath) {
        try {
            Response response = uploadManager.put(filePath, fileName, getUpToken());
            return response.jsonToMap();
        } catch (QiniuException e) {
            Response response = e.response;
            logger.error(response.toString());
            throw new RuntimeException(response.toString());
        }
    }
}

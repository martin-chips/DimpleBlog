package com.dimple.file.service.utils;

import com.dimple.file.service.config.QiNiuConfig;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Objects;

/**
 * QiNiuUtils
 *
 * @author Dimple
 * @date 2023/3/13 13:31
 */
@Slf4j
public class QiNiuUtils {

    @AllArgsConstructor
    private enum RegionEnum {
        HUA_NAM("huanan", Region.huanan()),
        HUA_BEI("huabei", Region.huabei()),
        HUA_DONG("huadong", Region.huadong()),
        ;
        private String code;
        private Region region;

        public static Region getRegion(String code) {
            return Arrays.stream(values()).filter(e -> Objects.equals(code, e.code)).findFirst().orElseThrow(() -> new RuntimeException("not support " + code)).region;
        }
    }


    private QiNiuUtils() {

    }

    public static void delete(QiNiuConfig qiNiuConfig, String fileName) {
        Configuration cfg = new Configuration(RegionEnum.getRegion(qiNiuConfig.getRegion()));
        Auth auth = Auth.create(qiNiuConfig.getAccessKey(), qiNiuConfig.getSecretKey());
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(qiNiuConfig.getBucket(), fileName);
        } catch (QiniuException ex) {
            log.error(ex.response.toString());
        }
    }

    public static String upload(MultipartFile file, QiNiuConfig qiNiuConfig, String fileName) {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(RegionEnum.getRegion(qiNiuConfig.getRegion()));
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String bucket = qiNiuConfig.getBucket();
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        try {
            byte[] uploadBytes = file.getBytes();
            Auth auth = Auth.create(qiNiuConfig.getAccessKey(), qiNiuConfig.getSecretKey());
            String upToken = auth.uploadToken(bucket);
            Response response = uploadManager.put(uploadBytes, fileName, upToken);
            log.info("upload file {} success, the response is {}.", fileName, response.bodyString());
            return qiNiuConfig.getDomain() + "/" + fileName;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return "";
    }

}

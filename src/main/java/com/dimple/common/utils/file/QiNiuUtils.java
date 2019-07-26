package com.dimple.common.utils.file;

import com.dimple.common.utils.spring.SpringUtils;
import com.dimple.framework.config.QiNiuConfig;
import com.dimple.project.common.domain.FileItemInfo;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

/**
 * @className: QiNiuUtils
 * @description: 七牛云的帮助类
 * @auther: Dimple
 * @Date: 2019/3/17
 * @Version: 1.0
 */
@Slf4j
public class QiNiuUtils {
    /**
     * 七牛云配置
     */
    private static QiNiuConfig qiNiuConfig = SpringUtils.getBean(QiNiuConfig.class);

    /**
     * 获取七牛云的BucketManager
     *
     * @return BucketManager
     */
    public static BucketManager getBucketManager() {
        Configuration configuration = new Configuration(Zone.zone0());
        Auth auth = Auth.create(qiNiuConfig.getAccessKey(), qiNiuConfig.getSecretKey());
        BucketManager bucketManager = new BucketManager(auth, configuration);
        return bucketManager;
    }

    /**
     * 获取Bucket
     *
     * @return Bucket
     */
    public static String getBucket() {
        return qiNiuConfig.getBucket();
    }

    /**
     * 上传文件到七牛云
     *
     * @param file 需要上传的文件
     */
    public static Optional<FileItemInfo> uploadFile(MultipartFile file) {
        Configuration configuration = new Configuration();
        UploadManager uploadManager = new UploadManager(configuration);
        Auth auth = Auth.create(qiNiuConfig.getAccessKey(), qiNiuConfig.getSecretKey());
        String token = auth.uploadToken(qiNiuConfig.getBucket());
        Response response = null;
        //生成文件名
        String fileName = FileUtils.generateFileName(file);
        FileItemInfo fileItemInfo = null;
        try {
            response = uploadManager.put(file.getInputStream(), fileName, token, null, null);
            DefaultPutRet defaultPutRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            String path = qiNiuConfig.getPath() + File.separator + defaultPutRet.key;
            log.info("上传文件到七牛云服务器成功{}", path);
            fileItemInfo = new FileItemInfo(fileName, defaultPutRet.hash, file.getSize(), file.getContentType(), new Date(), FileItemInfo.ServerType.QI_NIU_YUN.getServerType(), path);
        } catch (QiniuException e) {
            Response r = e.response;
            log.error(r.toString());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            response.close();
        }
        return Optional.ofNullable(fileItemInfo);
    }

    /**
     * 根据name获取其访问路径
     *
     * @return 访问路径
     */
    public static String getPathByName(String name) {
        return qiNiuConfig.getPath() + File.separator + name;
    }
}

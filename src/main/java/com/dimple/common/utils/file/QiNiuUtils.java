package com.dimple.common.utils.file;

import com.dimple.framework.config.QiNiuConfig;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.storage.model.FileListing;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @className: QiNiuUtils
 * @description:
 * @auther: Dimple
 * @Date: 2019/3/17
 * @Version: 1.0
 */
@Component
@Slf4j
public class QiNiuUtils {
    /**
     * 七牛云配置
     */
    @Autowired
    QiNiuConfig qiNiuConfig;

    /**
     * 上传图片到七牛云服务器
     *
     * @param file 需要上传的文件
     * @return 上传后的文件地址
     */
    public String uploadImgToQiNiu(MultipartFile file) {
        //检验文件大小
        FileUploadUtils.assertAllowed(file);
        String imgPath = "";
        String originalFilename = file.getOriginalFilename();
        //编码文件名
        String randomName = FileUploadUtils.encodingFilename(originalFilename);
        Configuration configuration = new Configuration();
        UploadManager uploadManager = new UploadManager(configuration);
        //create auth for qi niu
        Auth auth = Auth.create(qiNiuConfig.getAccessKey(), qiNiuConfig.getSecretKey());
        String token = auth.uploadToken(qiNiuConfig.getBucket());
        Response response = null;
        try {
            response = uploadManager.put(file.getInputStream(), randomName, token, null, null);
            DefaultPutRet defaultPutRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            imgPath = qiNiuConfig.getPath() + File.separator + defaultPutRet.key;
            log.info("upload img to qi niu success,the path of img is {}", imgPath);
        } catch (QiniuException e) {
            Response r = e.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            response.close();
        }
        return imgPath;
    }

    /**
     * 根据文件的url删除文件
     *
     * @param fileUrl 需要删除的文件的url
     * @return 删除时候成功 true 成功；false 失败
     */
    public boolean deleteImgFromQiNiu(String fileUrl) {
        if (StringUtils.isBlank(fileUrl)) {
            return false;
        }
        //获取到真实的uri
        String uri = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);

        Auth auth = Auth.create(qiNiuConfig.getAccessKey(), qiNiuConfig.getSecretKey());
        Configuration configuration = new Configuration();
        BucketManager bucketManager = new BucketManager(auth, configuration);
        Response delete = null;
        try {
            delete = bucketManager.delete(qiNiuConfig.getBucket(), uri);
        } catch (QiniuException e) {
            e.printStackTrace();
            return false;
        } finally {
            delete.close();
        }
        return true;
    }

    public List<String> selectImgList() {
        List<String> list = new LinkedList<>();
        Configuration configuration = new Configuration(Zone.zone0());
        Auth auth = Auth.create(qiNiuConfig.getAccessKey(), qiNiuConfig.getSecretKey());
        BucketManager bucketManager = new BucketManager(auth, configuration);
        try {
            FileListing fileListing = bucketManager.listFiles(qiNiuConfig.getBucket(), "", "", 3000, "");
            if (fileListing != null) {
                FileInfo[] items = fileListing.items;
                for (FileInfo item : items) {
                    list.add(qiNiuConfig.getPath() + "/" + item.key);
                }
            }
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        return list;
    }
}

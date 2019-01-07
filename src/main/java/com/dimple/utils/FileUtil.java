package com.dimple.utils;

import com.dimple.bean.FileUtilSetting;
import com.dimple.framework.exception.file.FileNameLengthOutOfLimitException;
import com.dimple.framework.exception.file.FileNotExistException;
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
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.pegdown.PegDownProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : FileUtil
 * @description : 文件上传处理类
 * @date : 01/07/19 11:02
 */
@Component
@Slf4j
public class FileUtil {

    @Autowired
    FileUtilSetting fileUtilSetting;

    /**
     * upload file to qi niu yun bucket
     *
     * @param file image input stream
     * @return the url of image in qi niu yun stream
     */
    public String uploadImgToQiNiu(MultipartFile file) throws FileNotExistException, FileNameLengthOutOfLimitException, FileUploadBase.FileSizeLimitExceededException {
        fileCheck(file);

        String imgPath = "";
        String originalFilename = file.getOriginalFilename();
        String randomName = generateFileRandomName(originalFilename);

        Configuration configuration = new Configuration();
        UploadManager uploadManager = new UploadManager(configuration);
        //create auth for qi niu
        Auth auth = Auth.create(fileUtilSetting.getAccessKey(), fileUtilSetting.getSecretKey());
        String token = auth.uploadToken(fileUtilSetting.getBucket());
        Response response = null;
        try {
            response = uploadManager.put(file.getInputStream(), randomName, token, null, null);
            DefaultPutRet defaultPutRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            imgPath = fileUtilSetting.getPath() + File.separator + defaultPutRet.key;
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
     * generate random name for file name and extension
     *
     * @param fileName
     * @return the name generated
     */
    public String generateFileRandomName(String fileName) {
        if (fileName == null) {
            return "";
        }
        String extension = FilenameUtils.getExtension(fileName);
        return Md5Util.hash(fileName) + "." + extension;
    }

    /**
     * delete img from qi niu
     *
     * @param fileName the name of file need to be deleted
     */
    public void deleteImgFromQiNiu(String fileName) {
        if (StringUtils.isBlank(fileName)) {
            return;
        }
        String trueName = getImgTrueNameFromUrl(fileName);

        Auth auth = Auth.create(fileUtilSetting.getAccessKey(), fileUtilSetting.getSecretKey());
        Configuration configuration = new Configuration();
        BucketManager bucketManager = new BucketManager(auth, configuration);
        Response delete = null;
        try {
            delete = bucketManager.delete(fileUtilSetting.getBucket(), trueName);
        } catch (QiniuException e) {
            e.printStackTrace();
        } finally {
            delete.close();
        }
    }

    /**
     * get all image from qi niu
     *
     * @return list of image url
     */
    public List<String> getAllImgFromQiNiu() {
        List<String> list = new LinkedList<>();
        Configuration configuration = new Configuration(Zone.zone0());
        Auth auth = Auth.create(fileUtilSetting.getAccessKey(), fileUtilSetting.getSecretKey());
        BucketManager bucketManager = new BucketManager(auth, configuration);
        try {
            FileListing fileListing = bucketManager.listFiles(fileUtilSetting.getBucket(), "", "", 3000, "");
            if (fileListing != null) {
                FileInfo[] items = fileListing.items;
                for (FileInfo item : items) {
                    list.add(fileUtilSetting.getPath() + "/" + item.key);
                }
            }
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * markdown file transfer to html
     *
     * @param file
     * @return
     * @throws FileNotExistException
     * @throws FileUploadBase.FileSizeLimitExceededException
     * @throws FileNameLengthOutOfLimitException
     */
    public String markdownTransferToHtml(MultipartFile file) throws FileNotExistException, FileUploadBase.FileSizeLimitExceededException, FileNameLengthOutOfLimitException {
        fileCheck(file);
        String htmlContent = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line;
            String mdContent = "";
            while ((line = bufferedReader.readLine()) != null) {
                mdContent += line + "\r\n";
            }
            PegDownProcessor pdp = new PegDownProcessor(Integer.MAX_VALUE);
            htmlContent = pdp.markdownToHtml(mdContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return htmlContent;
    }

    public String fileUpload(MultipartFile file) throws FileNotExistException {
        if (file.isEmpty()) {
            throw new FileNotExistException("file is empty");
        }
        String fileName = file.getOriginalFilename();
        log.info("upload file name {}" + fileName);
        String extension = FilenameUtils.getExtension(fileName);
        log.info("the extension {} of file {}", extension, fileName);
        String filePath = "temp/";
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            return "成功";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "失败";
    }


    /**
     * http://pkxwyzsng.bkt.clouddn.com\af425c5a8bc777f7c959a59ef54a95ee.jpg
     *
     * @param url
     * @return
     */
    private String getImgTrueNameFromUrl(String url) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        int i = url.lastIndexOf("/");
        return url.substring(i + 1);
    }

    private void fileCheck(MultipartFile file) throws FileNotExistException, FileNameLengthOutOfLimitException, FileUploadBase.FileSizeLimitExceededException {
        if (file.isEmpty()) {
            throw new FileNotExistException("file can not be null!");
        }
        int fileNameLength = file.getOriginalFilename().length();
        if (fileNameLength > fileUtilSetting.getImageMaxNameLength()) {
            throw new FileNameLengthOutOfLimitException(file.getOriginalFilename(), fileNameLength, fileUtilSetting.getImageMaxNameLength());
        }
        long size = file.getSize();
        if (size > fileUtilSetting.getImageMaxSize()) {
            throw new FileUploadBase.FileSizeLimitExceededException("file size above limit", size, fileUtilSetting.getImageMaxSize());
        }
    }


}

package com.dimple.project.common.service;

import com.dimple.project.common.domain.FileItemInfo;
import com.qiniu.common.QiniuException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @className: FileService
 * @description: 文件处理相关
 * @auther: Dimple
 * @date: 07/24/19
 * @version: 1.0
 */
public interface FileService {

    /**
     * 刷新七牛云数据到数据库保存
     *
     * @return 成功保存到数据库的条数
     */
    int syncQiNiuYunToLocalDB() throws QiniuException;

    /**
     * 删除七牛云图片
     *
     * @param name
     * @return 受影响的行数
     */
    int deleteQiNiuYunFile(String name) throws QiniuException;

    /**
     * 上传文件到七牛云
     *
     * @param file 需要上传的文件
     * @return 文件路径
     */
    String insertQiNiuYunFile(MultipartFile file);

    List<FileItemInfo> selectFileItemList(FileItemInfo fileItemInfo);

    /**
     * 上传文件到本地
     *
     * @param file 需要上传的文件
     * @return 文件路径
     */
    String insertLocalFile(MultipartFile file) throws IOException;

    /**
     * 刷新本地图片到数据库
     *
     * @return 受影响的行数
     */
    int syncLocalImageToDB();

}

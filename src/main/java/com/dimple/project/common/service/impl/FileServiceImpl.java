package com.dimple.project.common.service.impl;

import com.dimple.common.utils.file.QiNiuUtils;
import com.dimple.project.common.domain.FileItemInfo;
import com.dimple.project.common.mapper.FileItemInfoMapper;
import com.dimple.project.common.service.FileService;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.model.FileListing;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @className: FileServiceImpl
 * @description: 文件处理
 * @auther: Dimple
 * @date: 07/24/19
 * @version: 1.0
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {
    @Autowired
    FileItemInfoMapper fileItemInfoMapper;

    @Override
    @Transactional
    public int syncQiNiuYunToLocalDB() throws QiniuException {
        BucketManager bucketManager = QiNiuUtils.getBucketManager();
        FileListing fileListing = bucketManager.listFiles(QiNiuUtils.getBucket(), "", "", 10000, "");
        if (Objects.isNull(fileListing) || fileListing.items.length == 0) {
            return 0;
        }
        //将fileListing.item转换为FileItem类型
        List<FileItemInfo> fileItemsQiNiuYunInfo = Arrays.stream(fileListing.items).map(item -> new FileItemInfo(item.key, item.hash, item.fsize, item.mimeType, new Date(item.putTime), FileItemInfo.ServerType.QI_NIU_YUN.getServerType(), QiNiuUtils.getPathByName(item.key))).collect(Collectors.toList());

        //删除数据库现有的在七牛云上的记录
        fileItemInfoMapper.deleteByServerType(FileItemInfo.ServerType.QI_NIU_YUN.getServerType());
        //将新记录插入
        int count = 0;
        for (FileItemInfo fileItemInfo : fileItemsQiNiuYunInfo) {
            count += fileItemInfoMapper.insertFileItem(fileItemInfo);
        }
        return count;
    }

    @Override
    public int deleteQiNiuYunFile(String name) throws QiniuException {
        BucketManager bucketManager = QiNiuUtils.getBucketManager();
        Response response = bucketManager.delete(QiNiuUtils.getBucket(), name);
        return response.isOK() ? fileItemInfoMapper.deleteByNameAndServerType(name, FileItemInfo.ServerType.QI_NIU_YUN.getServerType()) : -1;
    }

    @Override
    public String insertQiNiuYunFile(MultipartFile file) {
        Optional<FileItemInfo> optionalS = QiNiuUtils.uploadFile(file);
        if (optionalS.isPresent()) {
            fileItemInfoMapper.insertFileItem(optionalS.get());
            return optionalS.get().getPath();
        }
        return "";
    }

    @Override
    public List<FileItemInfo> selectFileItemList(FileItemInfo fileItemInfo) {
        return fileItemInfoMapper.getFileItemList(fileItemInfo);
    }

}

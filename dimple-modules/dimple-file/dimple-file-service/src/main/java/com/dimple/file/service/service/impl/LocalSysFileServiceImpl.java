package com.dimple.file.service.service.impl;

import com.dimple.common.core.utils.file.FileUtils;
import com.dimple.file.service.config.LocalFileConfig;
import com.dimple.file.service.service.AbstractSysFileService;
import com.dimple.file.service.utils.FileUploadUtils;
import com.dimple.system.api.model.SysFileBO;
import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 本地文件存储
 *
 * @author Dimple
 */
@Service
public class LocalSysFileServiceImpl extends AbstractSysFileService {
    @Autowired
    private LocalFileConfig localFileConfig;

    /**
     * 本地文件上传接口
     *
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    @SneakyThrows
    @Override
    public SysFileBO uploadFile(MultipartFile file) {
        String filePathName = FileUploadUtils.upload(localFileConfig.getPath(), file);
        SysFileBO sysFileBO = new SysFileBO();
        String url = localFileConfig.getDomain() + localFileConfig.getPrefix() + filePathName;
        sysFileBO.setName(FileUploadUtils.getRandomFileName(file.getName()) + "." + FilenameUtils.getExtension(file.getOriginalFilename()));
        sysFileBO.setUrl(url);
        sysFileBO.setExtension(file.getContentType());
        sysFileBO.setType(FileUploadUtils.LOCAL_TYPE);
        sysFileBO.setPath(filePathName);

        return sysFileBO;
    }

    @Override
    public void deleteFile(String path) {
        FileUtils.deleteFile(path);
    }
}

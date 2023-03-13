package com.dimple.file.service.service.impl;

import com.dimple.file.service.config.QiNiuConfig;
import com.dimple.file.service.service.AbstractSysFileService;
import com.dimple.file.service.utils.FileUploadUtils;
import com.dimple.file.service.utils.QiNiuUtils;
import com.dimple.system.api.model.SysFileBO;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * QiniuSysFileServiceImpl
 *
 * @author Dimple
 * @date 2023/3/13 14:24
 */
@Service("qiNiuSysFileServiceImpl")
public class QiNiuSysFileServiceImpl extends AbstractSysFileService {
    @Autowired
    private QiNiuConfig qiNiuConfig;

    @Override
    public SysFileBO uploadFile(MultipartFile file) {
        String fileName = FileUploadUtils.getRandomFileName(file.getOriginalFilename()) + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        String url = QiNiuUtils.upload(file, qiNiuConfig, fileName);
        SysFileBO sysFileBO = new SysFileBO();
        sysFileBO.setName(fileName);
        sysFileBO.setUrl(url);
        sysFileBO.setExtension(file.getContentType());
        sysFileBO.setType(FileUploadUtils.QINIU_TYPE);
        sysFileBO.setPath(fileName);
        return sysFileBO;
    }

    @Override
    public void deleteFile(String fileName) {
        QiNiuUtils.delete(qiNiuConfig, fileName);
    }
}

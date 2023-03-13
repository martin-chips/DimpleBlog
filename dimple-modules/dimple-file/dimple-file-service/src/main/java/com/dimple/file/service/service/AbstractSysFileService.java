package com.dimple.file.service.service;

import com.dimple.system.api.model.SysFileBO;
import org.springframework.web.multipart.MultipartFile;

/**
 * AbstractSysFileService
 *
 * @author Dimple
 * @date 2023/3/13 17:42
 */
public abstract class AbstractSysFileService {
    /**
     * 文件上传接口
     *
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    public abstract SysFileBO uploadFile(MultipartFile file);

    public abstract void deleteFile(String url);
}

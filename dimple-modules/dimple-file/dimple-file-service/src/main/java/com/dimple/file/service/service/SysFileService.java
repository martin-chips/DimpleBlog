package com.dimple.file.service.service;

import com.dimple.system.api.model.SysFileBO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 文件上传接口
 *
 * @author Dimple
 */
public interface SysFileService {
    /**
     * 文件上传接口
     *
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    String uploadFile(MultipartFile file) throws Exception;

    List<SysFileBO> selectFileList(SysFileBO sysFileBO);
}

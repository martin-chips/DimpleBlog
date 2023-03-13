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


    List<SysFileBO> selectFileList(SysFileBO sysFileBO);

    int updateFileName(Long id, String name);

    SysFileBO selectSysFile(Long id);

    SysFileBO upload(MultipartFile file,String type);

    int deleteFile(Long id);
}

package com.dimple.file.service.service.impl;

import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.common.core.utils.file.FileTypeUtils;
import com.dimple.file.service.entity.SysFile;
import com.dimple.file.service.mapper.SysFileMapper;
import com.dimple.file.service.service.SysFileService;
import com.dimple.file.service.utils.FileUploadUtils;
import com.dimple.system.api.model.SysFileBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 本地文件存储
 *
 * @author Dimple
 */
@Primary
@Service
public class LocalSysFileServiceImpl implements SysFileService {
    @Autowired
    private SysFileMapper sysFileMapper;
    /**
     * 资源映射路径 前缀
     */
    @Value("${file.prefix}")
    public String localFilePrefix;

    /**
     * 域名或本机访问地址
     */
    @Value("${file.domain}")
    public String domain;

    /**
     * 上传文件存储在本地的根路径
     */
    @Value("${file.path}")
    private String localFilePath;

    /**
     * 本地文件上传接口
     *
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        String name = FileUploadUtils.upload(localFilePath, file);
        SysFileBO sysFileBO = new SysFileBO();
        String url = domain + localFilePrefix + name;
        sysFileBO.setName(name);
        sysFileBO.setUrl(url);
        sysFileBO.setExtension(FileTypeUtils.getFileType(name));
        sysFileBO.setType(FileTypeUtils.getFileType(name));
        sysFileMapper.saveFileMetaInfo(BeanMapper.convert(sysFileBO, SysFile.class));
        return url;
    }

    @Override
    public List<SysFileBO> selectFileList(SysFileBO sysFileBO) {
        List<SysFile> sysFiles = sysFileMapper.selectFileList(BeanMapper.convert(sysFileBO, SysFile.class));
        return BeanMapper.convertList(sysFiles, SysFileBO.class);
    }
}

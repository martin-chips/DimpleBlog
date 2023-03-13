package com.dimple.file.service.service.impl;

import com.dimple.common.core.utils.SpringUtils;
import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.file.service.constants.FileConstants;
import com.dimple.file.service.entity.SysFile;
import com.dimple.file.service.mapper.SysFileMapper;
import com.dimple.file.service.service.AbstractSysFileService;
import com.dimple.file.service.service.SysFileService;
import com.dimple.system.api.model.SysFileBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

/**
 * SysFileServiceImpl
 *
 * @author Dimple
 * @date 2023/3/13 14:24
 */
@Service
@Primary
public class SysFileServiceImpl implements SysFileService {
    @Autowired
    private SysFileMapper sysFileMapper;

    @Override
    public List<SysFileBO> selectFileList(SysFileBO sysFileBO) {
        List<SysFile> sysFiles = sysFileMapper.selectFileList(BeanMapper.convert(sysFileBO, SysFile.class));
        return BeanMapper.convertList(sysFiles, SysFileBO.class);
    }

    @Override
    public int updateFileName(Long id, String name) {
        return sysFileMapper.updateFileName(id, name);
    }

    @Override
    public SysFileBO selectSysFile(Long id) {
        return BeanMapper.convert(sysFileMapper.selectFileById(id), SysFileBO.class);
    }

    private AbstractSysFileService getFileHandlerInstant(String type) {
        AbstractSysFileService bean = null;
        if (FileConstants.LOCAL.equalsIgnoreCase(type)) {
            bean = SpringUtils.getBean(LocalSysFileServiceImpl.class);
        } else if (FileConstants.QiNiu.equalsIgnoreCase(type)) {
            bean = SpringUtils.getBean(QiNiuSysFileServiceImpl.class);
        }
        return bean;
    }

    @Override
    public SysFileBO upload(MultipartFile file, String type) {
        AbstractSysFileService fileHandlerInstant = getFileHandlerInstant(type);
        SysFileBO sysFileBO = fileHandlerInstant.uploadFile(file);
        sysFileMapper.saveFileMetaInfo(BeanMapper.convert(sysFileBO, SysFile.class));
        return sysFileBO;
    }

    @Override
    public int deleteFile(Long id) {
        SysFile sysFile = sysFileMapper.selectFileById(id);
        if (Objects.isNull(sysFile)) {
            return 1;
        }
        AbstractSysFileService fileHandlerInstant = getFileHandlerInstant(sysFile.getType());
        fileHandlerInstant.deleteFile(sysFile.getPath());
        return sysFileMapper.deleteFileById(id);
    }

}

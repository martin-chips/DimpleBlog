package com.dimple.file.service.mapper;

import com.dimple.file.service.entity.SysFile;

import java.util.List;

/**
 * FileMapper
 *
 * @author BianXiaofeng
 * @date 2023/3/12 15:13
 */
public interface SysFileMapper {

    void saveFileMetaInfo(SysFile sysFile);

    List<SysFile> selectFileList(SysFile sysFile);
}

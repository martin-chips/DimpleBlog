package com.dimple.file.service.mapper;

import com.dimple.file.service.entity.SysFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * FileMapper
 *
 * @author Dimple
 * @date 2023/3/12 15:13
 */
public interface SysFileMapper {

    void saveFileMetaInfo(SysFile sysFile);

    List<SysFile> selectFileList(SysFile sysFile);

    int updateFileName(@Param("id") Long id, @Param("name") String name);

    SysFile selectFileById(Long id);

    int deleteFileById(Long id);
}

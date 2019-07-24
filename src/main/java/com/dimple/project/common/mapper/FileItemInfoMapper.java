package com.dimple.project.common.mapper;

import com.dimple.project.common.domain.FileItemInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @className: FileItemMapper
 * @description: 文件处理
 * @auther: Dimple
 * @date: 07/24/19
 * @version: 1.0
 */
@Mapper
public interface FileItemInfoMapper {

    List<FileItemInfo> getFileItemList(FileItemInfo fileItemInfo);

    /**
     * 根据serverType删除数据库中的数据
     *
     * @param serverType 图片服务器类型
     * @return 受影响的行数
     */
    int deleteByServerType(Integer serverType);

    int insertFileItem(FileItemInfo fileItemInfo);

    int deleteByNameAndServerType(@Param("name") String name, @Param("serverType") Integer serverType);
}

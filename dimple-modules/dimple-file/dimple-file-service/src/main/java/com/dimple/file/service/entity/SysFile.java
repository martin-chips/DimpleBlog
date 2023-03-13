package com.dimple.file.service.entity;

import com.dimple.common.core.web.entity.BaseEntity;
import lombok.Data;

/**
 * SysFile
 *
 * @author Dimple
 * @date 2023/3/12 15:15
 */
@Data
public class SysFile extends BaseEntity {
    private Long id;
    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件地址
     */
    private String url;

    private String extension;
    /**
     * the file type,IMAGE/OTHER
     */
    private String type;

    private String path;
}

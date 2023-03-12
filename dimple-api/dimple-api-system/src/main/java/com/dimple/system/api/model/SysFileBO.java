package com.dimple.system.api.model;

import lombok.Data;

/**
 * 文件信息
 *
 * @author Dimple
 */
@Data
public class SysFileBO {
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
}

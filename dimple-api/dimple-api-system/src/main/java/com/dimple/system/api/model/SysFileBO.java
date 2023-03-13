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
     * local or qiNiu
     */
    private String type;

    /**
     * if type is qiNiu, the path value is file name.
     * if type is local, the path value is local path
     */
    private String path;
}

package com.dimple.system.api.model;

import lombok.Data;

/**
 * 文件信息
 *
 * @author Dimple
 */
@Data
public class SysFileBO {
    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件地址
     */
    private String url;
}

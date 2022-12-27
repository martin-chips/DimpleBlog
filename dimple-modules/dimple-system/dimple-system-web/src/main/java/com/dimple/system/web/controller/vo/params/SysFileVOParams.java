package com.dimple.system.web.controller.vo.params;

import lombok.Data;

/**
 * 文件信息
 *
 * @author Dimple
 */
@Data
public class SysFileVOParams {
    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件地址
     */
    private String url;
}

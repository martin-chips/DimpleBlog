package com.dimple.file.web.controller.vo;

import com.dimple.common.core.web.entity.BaseEntity;
import lombok.Data;

/**
 * SysFileVO
 *
 * @author Dimple
 * @date 2023/3/11 22:24
 */
@Data
public class SysFileVO extends BaseEntity {
    private Long id;
    private String title;
    private String url;
    private String extension;
    /**
     * the file type,IMAGE/OTHER
     */
    private String type;
}

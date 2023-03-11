package com.dimple.file.service.entity;

import com.dimple.common.core.web.entity.BaseEntity;
import lombok.Data;

/**
 * SysFile
 *
 * @author BianXiaofeng
 * @date 3/10/2023 2:01 PM
 */
@Data
public class SysFile extends BaseEntity {
    private Long id;
    private String title;
    private String url;
    private String extension;
}

package com.dimple.file.service.service.bo;

import com.dimple.common.core.web.entity.BaseEntity;
import lombok.Data;

/**
 * SysFileBO
 *
 * @author Dimple
 * @date 2023/3/11 22:22
 */
@Data
public class SysFileBO extends BaseEntity {
    private Long id;
    private String title;
    private String url;
    private String extension;
    /**
     * the file type,IMAGE/OTHER
     */
    private String type;
}

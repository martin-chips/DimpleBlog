package com.dimple.project.tool.domain;

import com.dimple.framework.web.domain.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @className: QiNiuContent
 * @description: 对应文件上传到七牛云之后保存在数据库中
 * @author: Dimple
 * @date: 11/06/19
 */
@Data
public class QiNiuContent extends BaseEntity implements Serializable {
    private Long id;
    /**
     * 文件名
     */
    private String name;
    /**
     * 空间名
     */
    private String bucket;
    /**
     * 大小,eg:5kb
     */
    private String size;
    /**
     * 访问地址
     */
    private String url;
    /**
     * 文件后缀
     */
    private String suffix;
    /**
     * 空间类型
     */
    private String type = "公开";
}

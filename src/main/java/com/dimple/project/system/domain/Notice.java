package com.dimple.project.system.domain;

import com.dimple.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @className: Notice
 * @description: 通知公告表 sys_notice
 * @author: Dimple
 * @date: 10/22/19
 */
@Data
//序列化Json的时候,如果是Null则忽略
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class Notice extends BaseEntity implements Serializable {

    /**
     * 公告ID
     */
    private Long id;

    /**
     * 公告标题
     */
    private String title;

    /**
     * 公告类型（1通知 2公告）
     */
    private String type;

    /**
     * 公告内容
     */
    private String content;
    /**
     * Html内容
     */
    private String htmlContent;

    /**
     * 公告状态（true正常 false关闭）
     */
    private Boolean status;
}

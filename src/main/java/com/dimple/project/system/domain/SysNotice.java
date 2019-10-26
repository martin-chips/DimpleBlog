package com.dimple.project.system.domain;

import com.dimple.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * @className: SysNotice
 * @description: 通知公告表 sys_notice
 * @author: Dimple
 * @date: 10/22/19
 */
@Data
public class SysNotice extends BaseEntity {

    /**
     * 公告ID
     */
    private Long noticeId;

    /**
     * 公告标题
     */
    private String noticeTitle;

    /**
     * 公告类型（1通知 2公告）
     */
    private String noticeType;

    /**
     * 公告内容
     */
    private String noticeContent;

    /**
     * 公告状态（0正常 1关闭）
     */
    private String status;
}

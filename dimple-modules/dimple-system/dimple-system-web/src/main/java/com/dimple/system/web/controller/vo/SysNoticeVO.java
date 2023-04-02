package com.dimple.system.web.controller.vo;

import com.dimple.common.core.web.entity.BaseEntity;
import lombok.Data;

/**
 * 通知公告表 sys_notice
 *
 * @author Dimple
 */
@Data
public class SysNoticeVO extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 公告ID
     */
    private Long id;

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

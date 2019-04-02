package com.dimple.project.system.notice.domain;

import com.dimple.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * @className: Notice
 * @description: 通知公告表 sys_notice
 * @auther: Dimple
 * @Date: 2019/3/13
 * @Version: 1.1
 */
@Data
public class Notice extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 公告ID
     */
    private Integer noticeId;
    /**
     * 公告标题
     */
    private String noticeTitle;
    /**
     * 显示（0不显示 1显示）
     */
    private Integer display;

}

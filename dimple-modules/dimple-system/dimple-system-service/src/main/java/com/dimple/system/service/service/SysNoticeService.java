package com.dimple.system.service.service;



import com.dimple.system.service.service.bo.SysNoticeBO;

import java.util.List;

/**
 * 公告 服务层
 *
 * @author Dimple
 */
public interface SysNoticeService {
    /**
     * 查询公告信息
     *
     * @param noticeId 公告ID
     * @return 公告信息
     */
    SysNoticeBO selectNoticeById(Long noticeId);

    /**
     * 查询公告列表
     *
     * @param notice 公告信息
     * @return 公告集合
     */
    List<SysNoticeBO> selectNoticeList(SysNoticeBO notice);

    /**
     * 新增公告
     *
     * @param notice 公告信息
     * @return affected lines
     */
    int insertNotice(SysNoticeBO notice);

    /**
     * 修改公告
     *
     * @param notice 公告信息
     * @return affected lines
     */
    int updateNotice(SysNoticeBO notice);

    /**
     * 删除公告信息
     *
     * @param noticeId 公告ID
     * @return affected lines
     */
    int deleteNoticeById(Long noticeId);

    /**
     * 批量删除公告信息
     *
     * @param noticeIds 需要删除的公告ID
     * @return affected lines
     */
    int deleteNoticeByIds(Long[] noticeIds);
}

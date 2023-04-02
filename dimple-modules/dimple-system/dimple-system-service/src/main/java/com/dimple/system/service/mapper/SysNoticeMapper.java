package com.dimple.system.service.mapper;


import com.dimple.system.service.entity.SysNotice;

import java.util.List;

/**
 * 通知公告表 数据层
 *
 * @author Dimple
 */
 public interface SysNoticeMapper {
    /**
     * 查询公告信息
     *
     * @param id 公告ID
     * @return 公告信息
     */
     SysNotice selectNoticeById(Long id);

    /**
     * 查询公告列表
     *
     * @param notice 公告信息
     * @return 公告集合
     */
     List<SysNotice> selectNoticeList(SysNotice notice);

    /**
     * 新增公告
     *
     * @param notice 公告信息
     * @return affected lines
     */
     int insertNotice(SysNotice notice);

    /**
     * 修改公告
     *
     * @param notice 公告信息
     * @return affected lines
     */
     int updateNotice(SysNotice notice);

    /**
     * 批量删除公告
     *
     * @param id 公告ID
     * @return affected lines
     */
     int deleteNoticeById(Long id);

    /**
     * 批量删除公告信息
     *
     * @param ids 需要删除的公告ID
     * @return affected lines
     */
     int deleteNoticeByIds(Long[] ids);
}
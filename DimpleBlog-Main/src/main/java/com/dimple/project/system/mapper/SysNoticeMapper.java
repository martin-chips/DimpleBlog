package com.dimple.project.system.mapper;

import com.dimple.project.system.domain.SysNotice;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @className: SysNoticeMapper
 * @description: 通知公告表 数据层
 * @author: Dimple
 * @date: 10/22/19
 */
public interface SysNoticeMapper {
    /**
     * 查询公告信息
     *
     * @param noticeId 公告ID
     * @return 公告信息
     */
    SysNotice selectNoticeById(Long noticeId);

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
     * @return 结果
     */
    int insertNotice(SysNotice notice);

    /**
     * 修改公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    int updateNotice(SysNotice notice);

    /**
     * 批量删除公告
     *
     * @param noticeId 公告ID
     * @return 结果
     */
    int deleteNoticeById(@Param("noticeId") Long noticeId, @Param("loginUsername") String loginUsername);
}

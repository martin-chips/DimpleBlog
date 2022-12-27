package com.dimple.system.service.service.impl;

import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.system.service.entity.SysNotice;
import com.dimple.system.service.mapper.SysNoticeMapper;
import com.dimple.system.service.service.ISysNoticeService;
import com.dimple.system.service.service.bo.SysNoticeBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公告 服务层实现
 *
 * @author Dimple
 */
@Service
public class SysNoticeServiceImpl implements ISysNoticeService {
    @Autowired
    private SysNoticeMapper noticeMapper;

    /**
     * 查询公告信息
     *
     * @param noticeId 公告ID
     * @return 公告信息
     */
    @Override
    public SysNoticeBO selectNoticeById(Long noticeId) {
        return BeanMapper.convert(noticeMapper.selectNoticeById(noticeId), SysNoticeBO.class);
    }

    /**
     * 查询公告列表
     *
     * @param notice 公告信息
     * @return 公告集合
     */
    @Override
    public List<SysNoticeBO> selectNoticeList(SysNoticeBO notice) {
        return BeanMapper.convertList(noticeMapper.selectNoticeList(BeanMapper.convert(notice,SysNotice.class)), SysNoticeBO.class);
    }

    /**
     * 新增公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    public int insertNotice(SysNoticeBO notice) {
        return noticeMapper.insertNotice(BeanMapper.convert(notice, SysNotice.class));
    }

    /**
     * 修改公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    public int updateNotice(SysNoticeBO notice) {
        return noticeMapper.updateNotice(BeanMapper.convert(notice, SysNotice.class));
    }

    /**
     * 删除公告对象
     *
     * @param noticeId 公告ID
     * @return 结果
     */
    @Override
    public int deleteNoticeById(Long noticeId) {
        return noticeMapper.deleteNoticeById(noticeId);
    }

    /**
     * 批量删除公告信息
     *
     * @param noticeIds 需要删除的公告ID
     * @return 结果
     */
    @Override
    public int deleteNoticeByIds(Long[] noticeIds) {
        return noticeMapper.deleteNoticeByIds(noticeIds);
    }
}

package com.dimple.project.system.service.impl;

import com.dimple.project.common.DimpleBlogContext;
import com.dimple.project.system.domain.SysNotice;
import com.dimple.project.system.mapper.SysNoticeMapper;
import com.dimple.project.system.service.SysNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: SysNoticeServiceImpl
 * @description: 公告 服务层实现
 * @author: Dimple
 * @date: 10/22/19
 */
@Service
public class SysNoticeServiceImpl implements SysNoticeService {
    @Autowired
    private SysNoticeMapper noticeMapper;

    @Override
    public SysNotice selectNoticeById(Long noticeId) {
        return noticeMapper.selectNoticeById(noticeId);
    }

    @Override
    public List<SysNotice> selectNoticeList(SysNotice notice) {
        return noticeMapper.selectNoticeList(notice);
    }

    @Override
    public int insertNotice(SysNotice notice) {
        return noticeMapper.insertNotice(notice);
    }

    @Override
    public int updateNotice(SysNotice notice) {
        return noticeMapper.updateNotice(notice);
    }

    @Override
    public int deleteNoticeById(Long noticeId) {
        String loginUsername = DimpleBlogContext.getLoginUsername();
        return noticeMapper.deleteNoticeById(noticeId, loginUsername);
    }
}

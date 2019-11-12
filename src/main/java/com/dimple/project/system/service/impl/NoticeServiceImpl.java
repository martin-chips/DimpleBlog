package com.dimple.project.system.service.impl;

import com.dimple.common.utils.ConvertUtils;
import com.dimple.common.utils.SecurityUtils;
import com.dimple.project.system.domain.Notice;
import com.dimple.project.system.mapper.NoticeMapper;
import com.dimple.project.system.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: NoticeServiceImpl
 * @description: 公告 服务层实现
 * @author: Dimple
 * @date: 10/22/19
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public Notice selectNoticeById(Long noticeId) {
        return noticeMapper.selectNoticeById(noticeId);
    }

    @Override
    public List<Notice> selectNoticeList(Notice notice) {
        return noticeMapper.selectNoticeList(notice);
    }

    @Override
    public int insertNotice(Notice notice) {
        return noticeMapper.insertNotice(notice);
    }

    @Override
    public int updateNotice(Notice notice) {
        return noticeMapper.updateNotice(notice);
    }

    @Override
    public int deleteNoticeByIds(String ids) {
        String loginUsername = SecurityUtils.getUsername();
        return noticeMapper.deleteNoticeByIds(ConvertUtils.toLongArray(ids), loginUsername);
    }
}

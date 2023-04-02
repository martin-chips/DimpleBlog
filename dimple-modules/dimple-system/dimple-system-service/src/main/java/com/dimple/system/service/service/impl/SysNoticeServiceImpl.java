package com.dimple.system.service.service.impl;

import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.system.service.entity.SysNotice;
import com.dimple.system.service.mapper.SysNoticeMapper;
import com.dimple.system.service.service.SysNoticeService;
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
public class SysNoticeServiceImpl implements SysNoticeService {
    @Autowired
    private SysNoticeMapper noticeMapper;

    /**
     * 查询公告信息
     *
     * @param id 公告ID
     * @return 公告信息
     */
    @Override
    public SysNoticeBO selectNoticeById(Long id) {
        return BeanMapper.convert(noticeMapper.selectNoticeById(id), SysNoticeBO.class);
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
     * @return affected lines
     */
    @Override
    public int insertNotice(SysNoticeBO notice) {
        return noticeMapper.insertNotice(BeanMapper.convert(notice, SysNotice.class));
    }

    /**
     * 修改公告
     *
     * @param notice 公告信息
     * @return affected lines
     */
    @Override
    public int updateNotice(SysNoticeBO notice) {
        return noticeMapper.updateNotice(BeanMapper.convert(notice, SysNotice.class));
    }

    /**
     * 删除公告对象
     *
     * @param id 公告ID
     * @return affected lines
     */
    @Override
    public int deleteNoticeById(Long id) {
        return noticeMapper.deleteNoticeById(id);
    }

    /**
     * 批量删除公告信息
     *
     * @param ids 需要删除的公告ID
     * @return affected lines
     */
    @Override
    public int deleteNoticeByIds(Long[] ids) {
        return noticeMapper.deleteNoticeByIds(ids);
    }
}

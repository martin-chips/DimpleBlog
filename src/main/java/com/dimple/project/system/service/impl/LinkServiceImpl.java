package com.dimple.project.system.service.impl;

import com.dimple.common.utils.ConvertUtils;
import com.dimple.common.utils.DateUtils;
import com.dimple.common.utils.SecurityUtils;
import com.dimple.project.system.domain.Link;
import com.dimple.project.system.mapper.LinkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: 友链Service业务层处理
 * @description:
 * @author: Dimple
 * @date: 2019-10-28
 */
@Service
public class LinkServiceImpl implements com.ruoyi.project.system.link.service.LinkService {
    @Autowired
    private LinkMapper linkMapper;

    @Override
    public Link selectLinkById(Long id) {
        return linkMapper.selectLinkById(id);
    }

    @Override
    public List<Link> selectLinkList(Link link) {
        return linkMapper.selectLinkList(link);
    }

    @Override
    public int insertLink(Link link) {
        link.setCreateTime(DateUtils.getNowDate());
        return linkMapper.insertLink(link);
    }

    @Override
    public int updateLink(Link link) {
        link.setUpdateTime(DateUtils.getNowDate());
        return linkMapper.updateLink(link);
    }

    @Override
    public int deleteLinkByIds(String ids) {
        String username = SecurityUtils.getUsername();
        return linkMapper.deleteLinkByIds(ConvertUtils.toStrArray(ids), username);
    }

    @Override
    public int deleteLinkById(Long id) {
        return linkMapper.deleteLinkById(id);
    }
}

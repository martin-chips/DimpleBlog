package com.dimple.project.system.service.impl;

import com.dimple.common.exception.CustomException;
import com.dimple.common.utils.ConvertUtils;
import com.dimple.common.utils.SecurityUtils;
import com.dimple.project.system.domain.Link;
import com.dimple.project.system.mapper.LinkMapper;
import com.dimple.project.system.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @className: Link
 * @description: 友链Service业务层处理
 * @author: Dimple
 * @date: 2019-10-29
 */
@Service
public class LinkServiceImpl implements LinkService {
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
        return linkMapper.insertLink(link);
    }

    @Override
    public int updateLink(Link link) {
        return linkMapper.updateLink(link);
    }

    @Override
    public int deleteLinkByIds(String ids) {
        String username = SecurityUtils.getUsername();
        return linkMapper.deleteLinkByIds(ConvertUtils.toLongArray(ids), username);
    }

    @Override
    public int deleteLinkById(Long id) {
        String username = SecurityUtils.getUsername();
        return linkMapper.deleteLinkById(id, username);
    }

    @Override
    public int handleLinkPass(Long id, Boolean pass) {
        Link link = selectLinkById(id);
        if (Objects.isNull(link)) {
            throw new CustomException("友链不存在");
        }
        if (!pass) {
            //todo 发送email
            return linkMapper.deleteLinkById(id, SecurityUtils.getUsername());
        }
        link.setStatus(true);
        return linkMapper.updateLink(link);
    }
}

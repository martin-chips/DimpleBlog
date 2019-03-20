package com.dimple.project.link.service.impl;

import com.dimple.common.utils.security.ShiroUtils;
import com.dimple.common.utils.text.Convert;
import com.dimple.project.link.domain.Link;
import com.dimple.project.link.mapper.LinkMapper;
import com.dimple.project.link.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: LinkServiceImpl
 * @description:
 * @auther: Dimple
 * @Date: 2019/3/17
 * @Version: 1.0
 */
@Service
public class LinkServiceImpl implements LinkService {

    @Autowired
    LinkMapper linkMapper;

    @Override
    public List<Link> selectLinkList(Link link) {
        return linkMapper.selectLinkList(link);
    }

    @Override
    public int insertLink(Link link) {
        link.setCreateBy(ShiroUtils.getLoginName());
        return linkMapper.insertLink(link);
    }

    @Override
    public Link selectLinkById(Integer linkId) {
        return linkMapper.selectLinkById(linkId);
    }

    @Override
    public int deleteLinkByIds(String ids) {
        return linkMapper.deleteLinkByIds(Convert.toIntArray(ids));
    }

    @Override
    public int updateLink(Link link) {
        link.setUpdateBy(ShiroUtils.getLoginName());
        return linkMapper.updateLink(link);
    }

    @Override
    public int changeDisplay(String ids, Integer display) {
        return linkMapper.changeDisplay(Convert.toIntArray(ids), display);
    }

    @Override
    public int passLink(String ids) {
        return linkMapper.changeProcessed(Convert.toIntArray(ids));
    }

}

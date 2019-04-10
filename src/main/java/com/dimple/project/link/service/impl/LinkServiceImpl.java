package com.dimple.project.link.service.impl;

import com.dimple.common.constant.LinkConstants;
import com.dimple.common.utils.security.ShiroUtils;
import com.dimple.common.utils.text.Convert;
import com.dimple.project.link.domain.Link;
import com.dimple.project.link.mapper.LinkMapper;
import com.dimple.project.link.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        //设置为已经处理
        link.setProcessed(LinkConstants.LINK_PROCESSED);
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
    public int changeDisplay(Integer id, Integer display) {
        return linkMapper.changeDisplay(id, display);
    }

    @Override
    public int processedLinkByIds(String ids) {
        return linkMapper.processedLinkByIds(Convert.toIntArray(ids));
    }

    @Override
    public Map<String, Integer> selectLinkCount() {
        Map<String, Integer> map = new HashMap<>();
        map.put("total", linkMapper.selectLinkCount());
        map.put("display", linkMapper.selectLinkCountByDisplay(1));
        map.put("hide", linkMapper.selectLinkCountByDisplay(0));
        map.put("die", linkMapper.selectLinkCountByAvailable(0));
        map.put("unhandled", linkMapper.selectLinkCountByProcessed(0));
        return map;
    }

    @Override
    public void incrementLinkClickById(Integer id) {
        linkMapper.incrementLinkClickById(id);
    }

    @Override
    public List<Link> selectLinkListFront() {
        return linkMapper.selectLinkListFront();
    }

    @Override
    public List<Link> selectLinkListFrontWithSummary() {
        return linkMapper.selectLinkListFrontWithSummary();
    }

    @Override
    public int applyLink(Link link) {
        link.setDisplay(0);
        link.setProcessed(0);
        return linkMapper.insertLink(link);
    }
}

package com.dimple.project.log.visitorLog.service.impl;

import com.dimple.common.constant.BlacklistConstants;
import com.dimple.common.utils.security.ShiroUtils;
import com.dimple.project.log.visitorLog.domain.Blacklist;
import com.dimple.project.log.visitorLog.domain.VisitLog;
import com.dimple.project.log.visitorLog.mapper.BlacklistMapper;
import com.dimple.project.log.visitorLog.service.BlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: BlacklistServiceImpl
 * @description:
 * @auther: Dimple
 * @date: 03/26/19
 * @version: 1.0
 */
@Service
public class BlacklistServiceImpl implements BlacklistService {
    @Autowired
    BlacklistMapper blacklistMapper;

    @Override
    public List<VisitLog> selectBlacklistList(Blacklist blacklist) {
        return blacklistMapper.selectBlacklistList(blacklist);
    }

    @Override
    public int deleteBlacklistByIds(Integer[] ids) {
        return blacklistMapper.deleteBlacklistByIds(ids);
    }

    @Override
    public Blacklist selectBlacklistById(Integer blacklistId) {
        return blacklistMapper.selectBlacklistById(blacklistId);
    }

    @Override
    public void cleanBlacklist() {
        blacklistMapper.cleanBlacklist();
    }

    @Override
    public int insertBlacklist(Blacklist blacklist) {
        blacklist.setCreateBy(ShiroUtils.getLoginName());
        return blacklistMapper.insertBlacklist(blacklist);
    }

    @Override
    public void updateNewestBlacklist(Integer id, String requestURI) {
        blacklistMapper.updateNewestBlacklist(id, requestURI);
    }

    @Override
    public boolean checkIpIsInBlacklist(String ip) {
        Blacklist blacklist = blacklistMapper.selectBlacklistByIp(ip);
        return blacklist == null ? BlacklistConstants.IP_NOT_IN_BLACKLIST : BlacklistConstants.IP_IN_BLACKLIST;
    }

    @Override
    public Blacklist selectBlacklistByIp(String ip) {
        return blacklistMapper.selectBlacklistByIp(ip);
    }

    @Override
    public int insertBlacklist(String ipAddr) {
        Blacklist blacklist = new Blacklist();
        blacklist.setIpAddr(ipAddr);
        return blacklistMapper.insertBlacklist(blacklist);
    }
}

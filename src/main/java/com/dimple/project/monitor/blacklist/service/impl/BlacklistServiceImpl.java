package com.dimple.project.monitor.blacklist.service.impl;

import com.dimple.common.constant.BlacklistConstants;
import com.dimple.common.constant.CachePrefix;
import com.dimple.common.utils.security.ShiroUtils;
import com.dimple.project.monitor.blacklist.domain.Blacklist;
import com.dimple.project.monitor.blacklist.mapper.BlacklistMapper;
import com.dimple.project.monitor.blacklist.service.BlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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
    public List<Blacklist> selectBlacklistList(Blacklist blacklist) {
        return blacklistMapper.selectBlacklistList(blacklist);
    }

    @Override
    @CacheEvict(value = CachePrefix.SYSTEM_BLACKLIST_ALL)
    public int deleteBlacklistByIds(Integer[] ids) {
        return blacklistMapper.deleteBlacklistByBlacklistIds(ids);
    }

    @Override
    public Blacklist selectBlacklistById(Integer blacklistId) {
        return blacklistMapper.selectBlacklistById(blacklistId);
    }

    @Override
    @CacheEvict(value = CachePrefix.SYSTEM_BLACKLIST_ALL)
    public void cleanBlacklist() {
        blacklistMapper.cleanBlacklist();
    }

    @Override
    @CacheEvict(value = CachePrefix.SYSTEM_BLACKLIST_ALL)
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
    @CacheEvict(value = CachePrefix.SYSTEM_BLACKLIST_ALL)
    public int insertBlacklist(String ipAddr) {
        Blacklist blacklist = new Blacklist();
        blacklist.setIpAddr(ipAddr);
        return blacklistMapper.insertBlacklist(blacklist);
    }

    @Override
    @CacheEvict(value = CachePrefix.SYSTEM_BLACKLIST_ALL)
    public int updateBlacklist(Blacklist blacklist) {
        return blacklistMapper.updateBlacklist(blacklist);
    }
}

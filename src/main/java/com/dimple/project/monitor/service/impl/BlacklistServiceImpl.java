package com.dimple.project.monitor.service.impl;

import com.dimple.common.utils.ConvertUtils;
import com.dimple.common.utils.SecurityUtils;
import com.dimple.project.monitor.domain.Blacklist;
import com.dimple.project.monitor.mapper.BlacklistMapper;
import com.dimple.project.monitor.service.BlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: BlacklistServiceImpl
 * @description:
 * @author: Dimpleb
 * @date: 10/24/19
 */
@Service
public class BlacklistServiceImpl implements BlacklistService {

    @Autowired
    BlacklistMapper blacklistMapper;

    @Override
    public int deleteBlacklistByIds(String ids) {
        return blacklistMapper.deleteBlacklistByIds(ConvertUtils.toStrArray(ids), SecurityUtils.getUsername());
    }

    @Override
    public int updateBlacklist(Blacklist blacklist) {
        return blacklistMapper.updateBlacklist(blacklist);
    }

    @Override
    public int insertBlacklist(Blacklist blacklist) {
        return blacklistMapper.insertBlacklist(blacklist);
    }

    @Override
    public Blacklist selectBlacklistById(Long id) {
        return blacklistMapper.selectBlacklistById(id);
    }

    @Override
    public List<Blacklist> selectBlacklistList(Blacklist blacklist) {
        return blacklistMapper.selectBlacklistList(blacklist);
    }
}

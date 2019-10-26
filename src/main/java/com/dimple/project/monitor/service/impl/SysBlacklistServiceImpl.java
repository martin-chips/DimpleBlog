package com.dimple.project.monitor.service.impl;

import com.dimple.common.utils.ConvertUtils;
import com.dimple.common.utils.SecurityUtils;
import com.dimple.project.monitor.domain.SysBlacklist;
import com.dimple.project.monitor.mapper.SysBlacklistMapper;
import com.dimple.project.monitor.service.SysBlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: SysBlacklistServiceImpl
 * @description:
 * @author: Dimpleb
 * @date: 10/24/19
 */
@Service
public class SysBlacklistServiceImpl implements SysBlacklistService {

    @Autowired
    SysBlacklistMapper sysBlacklistMapper;

    @Override
    public int deleteBlacklistByIds(String ids) {
        return sysBlacklistMapper.deleteBlacklistByIds(ConvertUtils.toStrArray(ids), SecurityUtils.getUsername());
    }

    @Override
    public int updateBlacklist(SysBlacklist sysBlacklist) {
        return sysBlacklistMapper.updateBlacklist(sysBlacklist);
    }

    @Override
    public int insertBlacklist(SysBlacklist sysBlacklist) {
        return sysBlacklistMapper.insertBlacklist(sysBlacklist);
    }

    @Override
    public SysBlacklist selectBlacklistById(Long id) {
        return sysBlacklistMapper.selectBlacklistById(id);
    }

    @Override
    public List<SysBlacklist> selectBlacklistList(SysBlacklist sysBlacklist) {
        return sysBlacklistMapper.selectBlacklistList(sysBlacklist);
    }
}

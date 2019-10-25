package com.dimple.project.monitor.service;

import com.dimple.project.monitor.domain.SysBlacklist;

import java.util.List;

/**
 * @className: SysBlacklistService
 * @description:
 * @author: Dimpleb
 * @date: 10/24/19
 */
public interface SysBlacklistService {
    /**
     * 删除黑名单记录
     *
     * @param ids id
     * @return 受影响的行数
     */
    int deleteBlacklistByIds(String ids);

    /**
     * 更新黑名单
     *
     * @param sysBlacklist 黑名单
     * @return 受影响的行数
     */
    int updateBlacklist(SysBlacklist sysBlacklist);

    /**
     * 新增黑名单
     *
     * @param sysBlacklist 黑名单对象
     * @return 受影响的行数
     */
    int insertBlacklist(SysBlacklist sysBlacklist);

    /**
     * 根据id查询黑名单记录
     *
     * @param id id
     * @return 黑名单对象
     */
    SysBlacklist selectBlacklistById(Long id);

    /**
     * 获取黑名单集合
     *
     * @param sysBlacklist 黑名单对象
     * @return 黑名单集合
     */
    List<SysBlacklist> selectBlacklistList(SysBlacklist sysBlacklist);
}

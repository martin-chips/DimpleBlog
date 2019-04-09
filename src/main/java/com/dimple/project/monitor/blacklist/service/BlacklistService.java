package com.dimple.project.monitor.blacklist.service;

import com.dimple.project.monitor.blacklist.domain.Blacklist;

import java.util.List;

/**
 * @className: com.dimple.project.log.visitorLog.business
 * @description:
 * @auther: Dimple
 * @date: 03/26/19
 * @version: 1.0
 */
public interface BlacklistService {
    /**
     * 获取blacklist集合
     *
     * @param blacklist 带有条件的blacklist
     * @return blacklist集合
     */
    List<Blacklist> selectBlacklistList(Blacklist blacklist);

    /**
     * 根据id删除黑名单
     *
     * @param ids 需要删除的黑名单的id
     * @return 受影响的行数
     */
    int deleteBlacklistByIds(Integer[] ids);

    /**
     * 根据id获取blacklist
     *
     * @param blacklistId 需要获取的blacklist的id
     * @return blacklist
     */
    Blacklist selectBlacklistById(Integer blacklistId);

    /**
     * 清空所有的黑名单
     */
    void cleanBlacklist();

    /**
     * 添加blacklist
     *
     * @param blacklist 需要添加的blacklist的集合
     * @return 受影响的行数
     */
    int insertBlacklist(Blacklist blacklist);

    /**
     * 更新黑名单的最近的访问记录
     *
     * @param id         黑名单的id
     * @param requestURI 黑名单的请求记录
     */
    void updateNewestBlacklist(Integer id, String requestURI);

    /**
     * 检查ip地址是不是黑名单
     *
     * @param ip 需要检查的ip地址
     * @return 是否是在黑名单
     */
    boolean checkIpIsInBlacklist(String ip);

    /**
     * 根据ip获取黑名单的信息
     *
     * @param ip IP地址
     * @return 黑名单实体类
     */
    Blacklist selectBlacklistByIp(String ip);

    /**
     * 根据ip地址插入一个新的黑名单记录
     *
     * @param ipAddr 黑名单的ip地址
     * @return 受影响的行
     */
    int insertBlacklist(String ipAddr);

    /**
     * 更新黑名单
     *
     * @param blacklist 需要更新的黑命单的实体类
     * @return 受影响的行数
     */
    int updateBlacklist(Blacklist blacklist);
}

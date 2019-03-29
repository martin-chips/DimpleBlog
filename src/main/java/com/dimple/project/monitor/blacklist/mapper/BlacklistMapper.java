package com.dimple.project.monitor.blacklist.mapper;

import com.dimple.project.monitor.blacklist.domain.Blacklist;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @className: BlacklistMapper
 * @description:
 * @auther: Dimple
 * @date: 03/26/19
 * @version: 1.0
 */
public interface BlacklistMapper {
    /**
     * 根据条件获取Blacklist
     *
     * @param blacklist 带有条件的blacklist
     * @return blacklist集合
     */
    List<Blacklist> selectBlacklistList(Blacklist blacklist);

    /**
     * 根据ids批量删除黑名单记录
     *
     * @param ids 黑名单的id
     * @return 受影响的行数
     */
    int deleteBlacklistByBlacklistIds(Integer[] ids);

    /**
     * 根据id获取blacklist
     *
     * @param blacklistId blacklist的id
     * @return blacklist实体
     */
    Blacklist selectBlacklistById(Integer blacklistId);

    /**
     * 清空blacklist
     */
    void cleanBlacklist();

    /**
     * 新增blacklist
     *
     * @param blacklist 需要新增的black实体
     * @return 受影响的行数
     */
    int insertBlacklist(Blacklist blacklist);

    /**
     * 更新黑名单访问的记录
     *
     * @param id
     * @param requestURI
     */
    void updateNewestBlacklist(@Param("id") Integer id, @Param("requestUrl") String requestURI);

    /**
     * 根据ip地址获取blacklist
     *
     * @param ip IP地址
     * @return blacklist实体
     */
    Blacklist selectBlacklistByIp(String ip);

    /**
     * 更新黑名单信息
     *
     * @param blacklist 需要更新的黑名单的信息
     * @return 受影响的行数
     */
    int updateBlacklist(Blacklist blacklist);

}

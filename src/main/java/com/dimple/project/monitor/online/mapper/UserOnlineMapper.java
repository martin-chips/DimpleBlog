package com.dimple.project.monitor.online.mapper;

import java.util.List;

import com.dimple.project.monitor.online.domain.UserOnline;

/**
 * @className: UserOnlineMapper
 * @description: 在线用户 数据层
 * @auther: Dimple
 * @Date: 2019/3/13
 * @Version: 1.1
 */
public interface UserOnlineMapper {
    /**
     * 通过会话序号查询信息
     *
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    public UserOnline selectOnlineById(String sessionId);

    /**
     * 通过会话序号删除信息
     *
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    public int deleteOnlineById(String sessionId);

    /**
     * 保存会话信息
     *
     * @param online 会话信息
     * @return 结果
     */
    public int saveOnline(UserOnline online);

    /**
     * 查询会话集合
     *
     * @param userOnline 会话参数
     * @return 会话集合
     */
    public List<UserOnline> selectUserOnlineList(UserOnline userOnline);

    /**
     * 查询过期会话集合
     *
     * @param lastAccessTime 过期时间
     * @return 会话集合
     */
    public List<UserOnline> selectOnlineByExpired(String lastAccessTime);
}

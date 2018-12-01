package com.dimple.service;

import com.dimple.bean.LoginLog;

import java.util.List;

/**
 * @ClassName: LoginLogService
 * @Description: 登录日志的Service类
 * @Auther: Owenb
 * @Date: 11/29/18 18:15
 * @Version: 1.0
 */
public interface LoginLogService {
    /**
     * 插入用户登录的日志
     *
     * @param loginLog 用户登录日志实例
     * @return 受影响的行数
     */
    Integer insertLoginLog(LoginLog loginLog);

    /**
     * 获得所有的登录日志
     *
     * @return
     */
    List<LoginLog> getAllLoginLog();
}

package com.dimple.modules.logManager.service;

import com.dimple.modules.logManager.bean.LoginLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.Map;

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
    LoginLog insertLoginLog(LoginLog loginLog);

    /**
     * 获取符合条件的所有的日志
     *
     * @param address     登录地址
     * @param loginId     登录名称
     * @param status      登录的状态
     * @param startTime   登录的开始时间
     * @param endTime     登录的截止时间
     * @param osType      操作系统类型
     * @param browserType 浏览器类型
     * @param pageable
     * @return
     */
    Page<LoginLog> getAllLoginLog(String address, String loginId, Boolean status, Date startTime, Date endTime, String osType, String browserType, Pageable pageable);

    /**
     * 删除所有的登录日志
     *
     * @return
     */
    void cleanLoginLog();

    /**
     * 删除指定的ID的登录日志
     *
     * @param ids 日志id
     * @return
     */
    Integer deleteLoginLog(Integer ids[]);

    /**
     * 获取登录日志列表显示的数据
     *
     * @return
     */
    Map<String, Integer> getDetails();
}

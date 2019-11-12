package com.dimple.project.log.service.impl;

import com.dimple.common.utils.ConvertUtils;
import com.dimple.common.utils.SecurityUtils;
import com.dimple.framework.redis.RedisCacheService;
import com.dimple.project.log.domain.LoginLog;
import com.dimple.project.log.mapper.LoginLogMapper;
import com.dimple.project.log.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: LoginLogServiceImpl
 * @description: 系统访问日志情况信息 服务层处理
 * @author: Dimple
 * @date: 10/22/19
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private LoginLogMapper loginLogMapper;

    @Autowired
    RedisCacheService redisCacheService;

    /**
     * 新增系统登录日志
     *
     * @param loginLog 访问日志对象
     */
    @Override
    public void insertLoginLog(LoginLog loginLog) {
        loginLogMapper.insertLoginLog(loginLog);
    }

    /**
     * 查询系统登录日志集合
     *
     * @param loginLog 访问日志对象
     * @return 登录记录集合
     */
    @Override
    public List<LoginLog> selectLoginLogList(LoginLog loginLog) {
        return loginLogMapper.selectLoginLogList(loginLog);
    }

    /**
     * 批量删除系统登录日志
     *
     * @param ids 需要删除的数据
     * @return
     */
    @Override
    public int deleteLoginLogByIds(String ids) {
        String username = SecurityUtils.getUsername();
        return loginLogMapper.deleteLoginLogByIds(ConvertUtils.toStrArray(ids), username);
    }

    /**
     * 清空系统登录日志
     */
    @Override
    public void cleanLoginLog() {
        loginLogMapper.cleanLoginLog(SecurityUtils.getUsername());
    }
}

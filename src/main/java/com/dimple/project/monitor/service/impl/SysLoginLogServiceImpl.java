package com.dimple.project.monitor.service.impl;

import com.dimple.common.utils.ConvertUtils;
import com.dimple.project.monitor.domain.SysLoginLog;
import com.dimple.project.monitor.mapper.SysLoginLogMapper;
import com.dimple.project.monitor.service.SysLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: SysLoginLogServiceImpl
 * @description: 系统访问日志情况信息 服务层处理
 * @author: Dimple
 * @date: 10/22/19
 */
@Service
public class SysLoginLogServiceImpl implements SysLoginLogService {

    @Autowired
    private SysLoginLogMapper sysLoginLogMapper;

    /**
     * 新增系统登录日志
     *
     * @param loginLog 访问日志对象
     */
    @Override
    public void insertLoginLog(SysLoginLog loginLog) {
        sysLoginLogMapper.insertLoginLog(loginLog);
    }

    /**
     * 查询系统登录日志集合
     *
     * @param loginLog 访问日志对象
     * @return 登录记录集合
     */
    @Override
    public List<SysLoginLog> selectLoginLogList(SysLoginLog loginLog) {
        return sysLoginLogMapper.selectLoginLogList(loginLog);
    }

    /**
     * 批量删除系统登录日志
     *
     * @param ids 需要删除的数据
     * @return
     */
    @Override
    public int deleteLoginLogByIds(String ids) {
        return sysLoginLogMapper.deleteLoginLogByIds(ConvertUtils.toStrArray(ids));
    }

    /**
     * 清空系统登录日志
     */
    @Override
    public void cleanLoginLog() {
        sysLoginLogMapper.cleanLoginLog();
    }
}

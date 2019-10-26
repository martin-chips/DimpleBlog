package com.dimple.project.monitor.service.impl;

import com.dimple.common.utils.ConvertUtils;
import com.dimple.project.monitor.domain.SysOperateLog;
import com.dimple.project.monitor.mapper.SysOperateLogMapper;
import com.dimple.project.monitor.service.SysOperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: SysOperateLogServiceImpl
 * @description: 操作日志 服务层处理
 * @author: Dimple
 * @date: 10/22/19
 */
@Service
public class SysOperateLogServiceImpl implements SysOperateLogService {
    @Autowired
    private SysOperateLogMapper operateLogMapper;

    /**
     * 新增操作日志
     *
     * @param operateLog 操作日志对象
     */
    @Override
    public void insertOperateLog(SysOperateLog operateLog) {
        operateLogMapper.insertOperateLog(operateLog);
    }

    /**
     * 查询系统操作日志集合
     *
     * @param operateLog 操作日志对象
     * @return 操作日志集合
     */
    @Override
    public List<SysOperateLog> selectOperateLogList(SysOperateLog operateLog) {
        return operateLogMapper.selectOperateLogList(operateLog);
    }

    /**
     * 批量删除系统操作日志
     *
     * @param ids 需要删除的数据
     * @return
     */
    @Override
    public int deleteOperateLogByIds(String ids) {
        return operateLogMapper.deleteOperateLogByIds(ConvertUtils.toStrArray(ids));
    }

    /**
     * 查询操作日志详细
     *
     * @param id 操作ID
     * @return 操作日志对象
     */
    @Override
    public SysOperateLog selectOperateLogById(Long id) {
        return operateLogMapper.selectOperateLogById(id);
    }

    /**
     * 清空操作日志
     */
    @Override
    public void cleanOperateLog() {
        operateLogMapper.cleanOperateLog();
    }
}

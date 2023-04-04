package com.dimple.log.service.service.impl;

import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.log.service.entity.SysOperLog;
import com.dimple.log.service.mapper.SysOperLogMapper;
import com.dimple.log.service.service.SysOperLogService;
import com.dimple.system.api.model.SysOperLogBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 操作日志 服务层处理
 *
 * @author Dimple
 */
@Service
public class SysOperLogServiceImpl implements SysOperLogService {
    @Autowired
    private SysOperLogMapper operLogMapper;

    /**
     * 新增操作日志
     *
     * @param operLog 操作日志对象
     * @return affected lines
     */
    @Override
    public int insertOperlog(SysOperLogBO operLog) {
        return operLogMapper.insertOperlog(BeanMapper.convert(operLog, SysOperLog.class));
    }

    /**
     * 查询系统操作日志集合
     *
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    @Override
    public List<SysOperLogBO> selectOperLogList(SysOperLogBO operLog) {
        List<SysOperLog> sysOperLogs = operLogMapper.selectOperLogList(BeanMapper.convert(operLog, SysOperLog.class));
        return BeanMapper.convertList(sysOperLogs, SysOperLogBO.class);
    }

    /**
     * 批量删除系统操作日志
     *
     * @param ids 需要删除的操作日志ID
     * @return affected lines
     */
    @Override
    public int deleteOperLogByIds(Long[] ids) {
        return operLogMapper.deleteOperLogByIds(ids);
    }

    /**
     * 查询操作日志详细
     *
     * @param id 操作ID
     * @return 操作日志对象
     */
    @Override
    public SysOperLogBO selectOperLogById(Long id) {
        return BeanMapper.convert(operLogMapper.selectOperLogById(id), SysOperLogBO.class);
    }

    /**
     * 清空操作日志
     */
    @Override
    public void cleanOperLog() {
        operLogMapper.cleanOperLog();
    }
}

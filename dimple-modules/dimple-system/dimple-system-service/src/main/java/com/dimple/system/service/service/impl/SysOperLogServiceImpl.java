package com.dimple.system.service.service.impl;

import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.system.api.model.SysOperLogBO;
import com.dimple.system.service.entity.SysOperLog;
import com.dimple.system.service.mapper.SysOperLogMapper;
import com.dimple.system.service.service.SysOperLogService;
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
     * @return 结果
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
        return BeanMapper.convertList(operLogMapper.selectOperLogList(BeanMapper.convert(operLog, SysOperLog.class)), SysOperLogBO.class);
    }

    /**
     * 批量删除系统操作日志
     *
     * @param operIds 需要删除的操作日志ID
     * @return 结果
     */
    @Override
    public int deleteOperLogByIds(Long[] operIds) {
        return operLogMapper.deleteOperLogByIds(operIds);
    }

    /**
     * 查询操作日志详细
     *
     * @param operId 操作ID
     * @return 操作日志对象
     */
    @Override
    public SysOperLogBO selectOperLogById(Long operId) {
        return BeanMapper.convert(operLogMapper.selectOperLogById(operId), SysOperLogBO.class);
    }

    /**
     * 清空操作日志
     */
    @Override
    public void cleanOperLog() {
        operLogMapper.cleanOperLog();
    }
}

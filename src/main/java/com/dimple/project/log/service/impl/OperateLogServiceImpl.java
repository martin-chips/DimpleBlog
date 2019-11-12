package com.dimple.project.log.service.impl;

import com.dimple.common.utils.ConvertUtils;
import com.dimple.common.utils.SecurityUtils;
import com.dimple.project.log.domain.OperateLog;
import com.dimple.project.log.mapper.OperateLogMapper;
import com.dimple.project.log.service.OperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: OperateLogServiceImpl
 * @description: 操作日志 服务层处理
 * @author: Dimple
 * @date: 10/22/19
 */
@Service
public class OperateLogServiceImpl implements OperateLogService {
    @Autowired
    private OperateLogMapper operateLogMapper;

    /**
     * 新增操作日志
     *
     * @param operateLog 操作日志对象
     */
    @Override
    public void insertOperateLog(OperateLog operateLog) {
        operateLogMapper.insertOperateLog(operateLog);
    }

    /**
     * 查询系统操作日志集合
     *
     * @param operateLog 操作日志对象
     * @return 操作日志集合
     */
    @Override
    public List<OperateLog> selectOperateLogList(OperateLog operateLog) {
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
        String username = SecurityUtils.getUsername();
        return operateLogMapper.deleteOperateLogByIds(ConvertUtils.toLongArray(ids), username);
    }

    /**
     * 查询操作日志详细
     *
     * @param id 操作ID
     * @return 操作日志对象
     */
    @Override
    public OperateLog selectOperateLogById(Long id) {
        return operateLogMapper.selectOperateLogById(id);
    }

    /**
     * 清空操作日志
     */
    @Override
    public void cleanOperateLog() {
        operateLogMapper.cleanOperateLog(SecurityUtils.getUsername());
    }
}

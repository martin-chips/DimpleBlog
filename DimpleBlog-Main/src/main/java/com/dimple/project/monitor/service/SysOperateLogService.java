package com.dimple.project.monitor.service;

import com.dimple.project.monitor.domain.SysOperateLog;

import java.util.List;

/**
 * @className: SysOperateLogService
 * @description: 操作日志 服务层
 * @author: Dimple
 * @date: 10/22/19
 */
public interface SysOperateLogService {
    /**
     * 新增操作日志
     *
     * @param operLog 操作日志对象
     */
    void insertOperateLog(SysOperateLog operLog);

    /**
     * 查询系统操作日志集合
     *
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    List<SysOperateLog> selectOperateLogList(SysOperateLog operLog);

    /**
     * 批量删除系统操作日志
     *
     * @param ids 需要删除的数据
     * @return 结果
     */
    int deleteOperateLogByIds(String ids);

    /**
     * 查询操作日志详细
     *
     * @param operId 操作ID
     * @return 操作日志对象
     */
    SysOperateLog selectOperateLogById(Long operId);

    /**
     * 清空操作日志
     */
    void cleanOperateLog();
}

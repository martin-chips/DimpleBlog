package com.dimple.project.log.service;

import com.dimple.project.log.domain.OperateLog;

import java.util.List;

/**
 * @className: OperateLogService
 * @description: 操作日志 服务层
 * @author: Dimple
 * @date: 10/22/19
 */
public interface OperateLogService {
    /**
     * 新增操作日志
     *
     * @param operLog 操作日志对象
     */
    void insertOperateLog(OperateLog operLog);

    /**
     * 查询系统操作日志集合
     *
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    List<OperateLog> selectOperateLogList(OperateLog operLog);

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
    OperateLog selectOperateLogById(Long operId);

    /**
     * 清空操作日志
     */
    void cleanOperateLog();
}

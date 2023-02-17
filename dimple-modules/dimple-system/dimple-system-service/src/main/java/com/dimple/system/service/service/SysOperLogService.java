package com.dimple.system.service.service;



import com.dimple.system.api.model.SysOperLogBO;

import java.util.List;

/**
 * 操作日志 服务层
 *
 * @author Dimple
 */
public interface SysOperLogService {
    /**
     * 新增操作日志
     *
     * @param operLog 操作日志对象
     * @return affected lines
     */
    int insertOperlog(SysOperLogBO operLog);

    /**
     * 查询系统操作日志集合
     *
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    List<SysOperLogBO> selectOperLogList(SysOperLogBO operLog);

    /**
     * 批量删除系统操作日志
     *
     * @param operIds 需要删除的操作日志ID
     * @return affected lines
     */
    int deleteOperLogByIds(Long[] operIds);

    /**
     * 查询操作日志详细
     *
     * @param operId 操作ID
     * @return 操作日志对象
     */
    SysOperLogBO selectOperLogById(Long operId);

    /**
     * 清空操作日志
     */
    void cleanOperLog();
}

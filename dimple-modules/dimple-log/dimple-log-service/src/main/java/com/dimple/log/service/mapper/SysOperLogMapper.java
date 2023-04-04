package com.dimple.log.service.mapper;



import com.dimple.log.service.entity.SysOperLog;

import java.util.List;

/**
 * 操作日志 数据层
 *
 * @author Dimple
 */
 public interface SysOperLogMapper {
    /**
     * 新增操作日志
     *
     * @param operLog 操作日志对象
     */
     int insertOperlog(SysOperLog operLog);

    /**
     * 查询系统操作日志集合
     *
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
     List<SysOperLog> selectOperLogList(SysOperLog operLog);

    /**
     * 批量删除系统操作日志
     *
     * @param ids 需要删除的操作日志ID
     * @return affected lines
     */
     int deleteOperLogByIds(Long[] ids);

    /**
     * 查询操作日志详细
     *
     * @param id 操作ID
     * @return 操作日志对象
     */
     SysOperLog selectOperLogById(Long id);

    /**
     * 清空操作日志
     */
     void cleanOperLog();
}

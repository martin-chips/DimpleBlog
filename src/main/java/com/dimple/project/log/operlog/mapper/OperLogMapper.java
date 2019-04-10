package com.dimple.project.log.operlog.mapper;

import com.dimple.project.log.operlog.domain.OperLog;

import java.util.List;

/**
 * @className: OperLogMapper
 * @description: 操作日志 数据层
 * @auther: Dimple
 * @Date: 2019/3/13
 * @Version: 1.1
 */
public interface OperLogMapper {
    /**
     * 新增操作日志
     *
     * @param operLog 操作日志对象
     */
    void insertOperlog(OperLog operLog);

    /**
     * 查询系统操作日志集合
     *
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    List<OperLog> selectOperLogList(OperLog operLog);

    /**
     * 批量删除系统操作日志
     *
     * @param ids 需要删除的数据
     * @return 结果
     */
    int deleteOperLogByIds(String[] ids);

    /**
     * 查询操作日志详细
     *
     * @param operId 操作ID
     * @return 操作日志对象
     */
    OperLog selectOperLogById(Long operId);

    /**
     * 清空操作日志
     */
    void cleanOperLog();

    /**
     * 获取dashboard页面展示的操作日志
     *
     * @return 操作日志集合
     */
    List<OperLog> selectOperLogData();
}

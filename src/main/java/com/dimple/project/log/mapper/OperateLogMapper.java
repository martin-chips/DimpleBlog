package com.dimple.project.log.mapper;

import com.dimple.project.log.domain.OperateLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @className: OperateLogMapper
 * @description: 操作日志 数据层
 * @author: Dimple
 * @date: 10/22/19
 */
public interface OperateLogMapper {
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
     * @param ids      需要删除的数据
     * @param username
     * @return 结果
     */
    int deleteOperateLogByIds(@Param("ids") Long[] ids, @Param("username") String username);

    /**
     * 查询操作日志详细
     *
     * @param id 操作ID
     * @return 操作日志对象
     */
    OperateLog selectOperateLogById(Long id);

    /**
     * 清空操作日志
     */
    void cleanOperateLog(String username);

    /**
     * update operate log
     *
     * @param operateLog operateLog
     * @return update count
     */
    int updateOperateLog(OperateLog operateLog);
}

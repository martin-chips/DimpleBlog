package com.dimple.project.log.mapper;

import com.dimple.project.log.domain.VisitLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @className: VisitLogMapper
 * @description:
 * @author: Dimple
 * @date: 2019/11/30
 */
public interface VisitLogMapper {
    /**
     * 新增系统访问日志
     *
     * @param visitLog 访问日志对象
     */
    int insertVisitLog(VisitLog visitLog);

    /**
     * 查询系统访问日志集合
     *
     * @param visitLog 访问日志对象
     * @return 记录集合
     */
    List<VisitLog> selectVisitLogList(VisitLog visitLog);

    /**
     * 批量删除访问日志
     *
     * @param ids 需要删除的数据
     * @return 结果
     */
    int deleteVisitLogByIds(@Param("ids") Long[] ids, @Param("username") String username);

    /**
     * f
     * 清空访问日志
     *
     * @param username 操作者
     * @return 结果
     */
    int cleanVisitLog(String username);

    /**
     * update visitLog
     *
     * @param visitLog VisitLog
     * @return update count
     */
    int updateVisitLog(VisitLog visitLog);
}

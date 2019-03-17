package com.dimple.project.log.visitorLog.mapper;

import com.dimple.project.log.visitorLog.domain.VisitLog;

import java.util.List;

/**
 * @className: VisitLogMapper
 * @description:
 * @auther: Dimple
 * @Date: 2019/3/16
 * @Version: 1.0
 */
public interface VisitLogMapper {
    /**
     * 根据条件获得VisitLog记录
     *
     * @param visitLog 需要查询的条件
     * @return 符合条件的记录
     */
    List<VisitLog> selectVisitLogList(VisitLog visitLog);

    /**
     * 清空访客日志
     */
    void cleanVisitLog();

    /**
     * 根据id清除访客日志
     *
     * @param ids 需要清除的访客日志的id
     * @return 受影响的额行数
     */
    int deleteVisitLogByIds(Integer[] ids);

    /**
     * 根据id获取访客日志
     *
     * @param visitId 需要获取的日志的id
     * @return 访客日志
     */
    VisitLog selectVisitLogById(Integer visitId);
}

package com.dimple.project.log.visitorLog.service;

import com.dimple.project.log.visitorLog.domain.VisitLog;

import java.util.List;

/**
 * @className: VisitLogService
 * @description:
 * @auther: Dimple
 * @Date: 2019/3/16
 * @Version: 1.0
 */
public interface VisitLogService {
    /**
     * 根据条件获取访客日志
     *
     * @param visitLog 带有条件的访客日志信息
     * @return 符合条件的访客日志的集合
     */
    List<VisitLog> selectVisitLogList(VisitLog visitLog);

    /**
     * 根据id批量删除日志
     *
     * @param ids 需要删除的日志的id
     * @return 受影响的行数
     */
    int deleteVisitLogByIds(Integer[] ids);

    /**
     * 根据id获取单个VisitLog记录
     *
     * @param visitId 需要获得Log的id
     * @return 实体类
     */
    VisitLog selectVisitLogById(Integer visitId);

    /**
     * 清空访问日志
     */
    void cleanVisitLog();

    /**
     * 新增访问记录
     *
     * @param visitLog
     */
    void insertVisitLog(VisitLog visitLog);
}

package com.dimple.project.log.service;

import com.dimple.project.log.domain.VisitLog;

import java.util.List;

/**
 * @className: VisitLogService
 * @description:
 * @author: Dimple
 * @date: 2019/11/30
 */
public interface VisitLogService {
    /**
     * 根据查询条件获取List
     *
     * @param visitLog 查询条件
     * @return list
     */
    List<VisitLog> selectVisitLogList(VisitLog visitLog);

    /**
     * 根据Id删除所有的log
     *
     * @param ids id集合
     * @return 受影响的行数
     */
    int deleteVisitLogByIds(String ids);

    /**
     * 清除所有的log
     */
    void cleanVisitLog();

    /**
     * 插入log
     *
     * @param visitLog log
     * @return 受影响的行数
     */
    int insertVisitLog(VisitLog visitLog);
}

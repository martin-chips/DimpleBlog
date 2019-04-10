package com.dimple.project.log.visitorLog.mapper;

import com.dimple.project.dashboard.domain.BusinessCommonData;
import com.dimple.project.log.visitorLog.domain.VisitLog;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 新增VisitLog记录
     *
     * @param visitLog 需要新增的VisitLog
     * @return 受影响的行数
     */
    int insertVisitLog(VisitLog visitLog);

    /**
     * 获取爬虫访问数据
     *
     * @return
     */
    List<BusinessCommonData> selectSpiderData();

    /**
     * 根据时间获取当天的访问量
     *
     * @param date
     * @return
     */
    Integer selectVisitCountByCreateTime(String date);

    /**
     * 获取所有数据
     *
     * @return 所有数据的数量
     */
    Integer selectVisitLogTotalCount();

    /**
     * 获取今天的访客数量
     *
     * @return 今天的访客数量
     */
    Integer selectVisitLogTodayCount();

    /**
     * 获取图表展示的visit记录
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 数据
     */
    List<BusinessCommonData> selectVisitLogData(@Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 获取dashboard界面展示的数据
     *
     * @return visitlog集合
     */
    List<VisitLog> selectVisitData();
}

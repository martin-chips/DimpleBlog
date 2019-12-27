package com.dimple.project.home.service;

import com.dimple.project.home.domain.LineChartData;

import java.util.List;
import java.util.Map;

/**
 * @className: DashboardService
 * @description:
 * @author: Dimple
 * @date: 12/20/19
 */
public interface DashboardService {
    /**
     * 获取首页Panel Group的数据
     *
     * @return 数据map
     */
    Map<String, Long> getPanelGroupData();

    /**
     * 根据type获取折线图的数据
     *
     * @param type 类型
     * @return 折线图数据
     */
    LineChartData<Long> getLineChartData(String type);

    /**
     * 获取爬虫访问数据
     *
     * @return list
     */
    List<Map<String, Long>> getSpiderData();

    /**
     * 获取VisitLog List
     *
     * @return VisitLog
     */
    List<String> getVisitLogStringList();

    /**
     * 获取Login Log的String List
     *
     * @return loginLog
     */
    List<String> getLoginLogStringList();

    /**
     * Operate Log的String List
     *
     * @return operateLog
     */
    List<String> getOperateLogStringList();

    /**
     * 获取Task Log的String List
     *
     * @return taskLog
     */
    List<String> getTaskLogStringList();
}

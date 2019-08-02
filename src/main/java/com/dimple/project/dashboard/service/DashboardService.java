package com.dimple.project.dashboard.service;

import com.dimple.project.dashboard.domain.BusinessCommonData;
import com.dimple.project.dashboard.domain.LogMessage;
import com.dimple.project.dashboard.domain.VisitCount;

import java.util.List;

/**
 * @className: com.dimple.project.dashboard.business
 * @description:
 * @auther: Dimple
 * @date: 04/02/19
 * @version: 1.0
 */
public interface DashboardService {
    /**
     * 获取爬虫访问记录
     *
     * @return 爬虫数据实体类
     */
    List<BusinessCommonData> selectSpiderData();

    /**
     * 获取访问量的数据
     *
     * @return VisitCount实体类
     */
    List<VisitCount> getVisitData();

    /**
     * 获取最近动态
     *
     * @return 最近动态log
     */
    List<LogMessage> selectLogMessage();
}

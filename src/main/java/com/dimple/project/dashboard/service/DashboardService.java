package com.dimple.project.dashboard.service;

import com.dimple.project.dashboard.domain.SpiderData;
import com.dimple.project.dashboard.domain.VisitCount;

import java.util.List;

/**
 * @className: com.dimple.project.dashboard.service
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
    List<SpiderData> selectSpiderData();

    /**
     * 获取访问量的数据
     *
     * @return VisitCount实体类
     */
    List<VisitCount> getVisitData();
}

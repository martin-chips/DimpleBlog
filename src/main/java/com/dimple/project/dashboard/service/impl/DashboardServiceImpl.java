package com.dimple.project.dashboard.service.impl;

import com.dimple.common.utils.DateUtils;
import com.dimple.project.dashboard.domain.SpiderData;
import com.dimple.project.dashboard.domain.VisitCount;
import com.dimple.project.dashboard.service.DashboardService;
import com.dimple.project.log.visitorLog.mapper.VisitLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: DashboardServiceImpl
 * @description:
 * @auther: Dimple
 * @date: 04/02/19
 * @version: 1.0
 */
@Service
public class DashboardServiceImpl implements DashboardService {
    @Autowired
    VisitLogMapper visitLogMapper;


    @Override
    public List<SpiderData> selectSpiderData() {
        return visitLogMapper.selectSpiderData();
    }

    @Override
    public List<VisitCount> getVisitData() {
        List<String> pastDaysList = DateUtils.getPastDaysList(7);
        List<VisitCount> visitCounts = new ArrayList<>();
        for (String date : pastDaysList) {
            Integer visitorCountByVisitTime = visitLogMapper.selectVisitCountByCreateTime(date);
            VisitCount visitCount = new VisitCount();
            visitCount.setDate(date);
            visitCount.setValue(visitorCountByVisitTime);
            visitCounts.add(visitCount);
        }
        return visitCounts;
    }

}

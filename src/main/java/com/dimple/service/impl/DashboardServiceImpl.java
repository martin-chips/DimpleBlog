package com.dimple.service.impl;

import com.dimple.repository.VisitorLogRepository;
import com.dimple.service.DashboardService;
import com.dimple.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : IndexServiceImpl
 * @description :
 * @date : 01/10/19 14:06
 */
@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    VisitorLogRepository visitorLogRepository;

    @Override
    public List<Map<String, Integer>> getSpiderPieData() {
        List<Map<String, Integer>> spiderCount = visitorLogRepository.getSpiderCount();
        return spiderCount;
    }

    @Override
    public List<Map<String, Object>> getVisitorData() {
        List<String> pastDaysList = DateUtil.getPastDaysList(7);
        List<Map<String, Object>> result = new ArrayList<>();
        for (String date : pastDaysList) {
            Integer visitorCountByVisitTime = visitorLogRepository.getVisitorCountByVisitTime(date);
            Map<String, Object> item = new HashMap<>();
            item.put("name", date);
            item.put("value", visitorCountByVisitTime);
            result.add(item);
        }
        return result;
    }
}

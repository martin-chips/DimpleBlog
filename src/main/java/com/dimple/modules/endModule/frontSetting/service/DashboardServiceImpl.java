package com.dimple.modules.endModule.frontSetting.service;

import com.dimple.modules.endModule.visitorManager.repository.VisitorRepository;
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
    VisitorRepository visitorRepository;

    @Override
    public List<Map<String, Integer>> getSpiderPieData() {
        List<Map<String, Integer>> spiderCount = visitorRepository.getSpiderCount();
        return spiderCount;
    }

    @Override
    public List<Map<String, Object>> getVisitorData() {
        List<String> pastDaysList = DateUtil.getPastDaysList(7);
        List<Map<String, Object>> result = new ArrayList<>();
        for (String date : pastDaysList) {
            Integer visitorCountByVisitTime = visitorRepository.getVisitorCountByVisitTime(date);
            Map<String, Object> item = new HashMap<>();
            item.put("name", date);
            item.put("value", visitorCountByVisitTime);
            result.add(item);
        }
        return result;
    }
}

package com.dimple.modules.BackStageModule.FrontSetting.service;

import java.util.List;
import java.util.Map;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : DashboardService
 * @description :
 * @date : 01/10/19 14:05
 */
public interface DashboardService {


    List<Map<String, Integer>> getSpiderPieData();

    List<Map<String, Object>> getVisitorData();
}

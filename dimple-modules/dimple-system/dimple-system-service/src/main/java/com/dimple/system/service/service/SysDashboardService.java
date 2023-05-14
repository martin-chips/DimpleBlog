package com.dimple.system.service.service;

import com.dimple.system.service.entity.dashboard.Dashboard;
import com.dimple.system.service.entity.dashboard.DashboardCountItem;
import com.dimple.system.service.entity.dashboard.DashboardKeyValue;

import java.util.List;

/**
 * SysDashboardService
 *
 * @author Dimple
 */
public interface SysDashboardService {
    Dashboard getDashboardWeeklyCount();

    DashboardCountItem getDashboardCountItems();

    List<DashboardKeyValue> getSpiderCount();


    List<DashboardKeyValue> getRefererCount();
}

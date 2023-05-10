package com.dimple.system.service.entity.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardItem {
    private List<Long> lastWeekData;
    private List<Long> currentWeekData;
}

package com.dimple.system.service.entity.dashboard;

import lombok.Data;

import java.util.List;

@Data
public class Dashboard {
    private List<String> weeks;
    private DashboardItem visitor;
    private DashboardItem comment;
    private DashboardItem article;
    private DashboardItem login;
}

package com.dimple.system.service.entity.dashboard;

import lombok.Data;

/**
 * DashboardCountItem
 *
 * @author Dimple
 */
@Data
public class DashboardCountItem {
    public Long visitor;
    private Long comment;
    private Long login;
    private Long article;
}

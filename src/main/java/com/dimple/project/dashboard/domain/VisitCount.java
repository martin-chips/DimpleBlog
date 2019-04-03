package com.dimple.project.dashboard.domain;

import lombok.Data;

/**
 * @className: VisitCount
 * @description:
 * @auther: Dimple
 * @date: 04/02/19
 * @version: 1.0
 */
@Data

public class VisitCount {
    /**
     * 访问日期
     */
    private String date;
    /**
     * 访问量
     */
    private Integer value;
}

package com.dimple.project.dashboard.domain;

import lombok.Data;

/**
 * @className: SpiderData
 * @description:
 * @auther: Dimple
 * @date: 04/02/19
 * @version: 1.0
 */
@Data
public class SpiderData {
    /**
     * 爬虫名称
     */
    private String name;
    /**
     * 访问数
     */
    private Integer value;
}

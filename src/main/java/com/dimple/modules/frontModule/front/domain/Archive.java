package com.dimple.modules.frontModule.front.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @className: Archive
 * @description:
 * @auther: Owenb
 * @date: 01/25/19
 * @version: 1.0
 */
@Data
public class Archive {

    /**
     * 归档的日期
     */
    private String date;
    /**
     * 该日期下的博文的数量
     */
    private Integer count;
    /**
     * 博客的分类信息
     */
    private List<Map<String, Object>> blogs;

}

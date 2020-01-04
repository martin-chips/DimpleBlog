package com.dimple.project.home.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @className: LineChartData
 * @description: 前端Dashboard折线图数据
 * @author: Dimple
 * @date: 12/20/19
 */
@Data
public class LineChartData<T> implements Serializable {
    public static final String BLOG_LINE = "blog";
    public static final String NOTE_LINE = "note";
    public static final String BOOK_LINE = "book";
    public static final String VISITOR_LINE = "visitor";
    /**
     * 期望值,即前一段时间的值
     */
    List<T> expectedData;
    /**
     * 实际值,即当前时间的值
     */
    List<T> actualData;
    /**
     * X轴数据
     */
    List<String> axisData;
}

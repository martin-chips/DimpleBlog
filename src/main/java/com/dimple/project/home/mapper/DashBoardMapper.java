package com.dimple.project.home.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @className: DashBoardMapper
 * @description:
 * @author: Dimple
 * @date: 12/20/19
 */
public interface DashBoardMapper {

    /**
     * 获取访问数量
     *
     * @return 访问数量
     */
    Long getVisitorCount();

    /**
     * 获取博客数量
     *
     * @return 博客数量
     */
    Long getBlogCount();

    /**
     * 获取图书数量
     *
     * @return 图书数量
     */
    Long getBookCount();

    /**
     * 获取笔记数量
     *
     * @return 笔记数量
     */
    Long getNoteCount();

    /**
     * get visit log count by createTime .
     *
     * @param date current day string. eg:2019-08-08
     * @return count
     */
    Long getVisitorCountByCreateTime(@Param("date") String date);

    /**
     * get note count by createTime
     *
     * @param day current day string. eg:2019-08-08
     * @return count
     */
    Long getNoteCountByCreateTime(String day);

    /**
     * get blog count by createTime
     *
     * @param day current day string. eg:2019-08-08
     * @return count
     */
    Long getBlogCountByCreateTime(String day);

    /**
     * get book count by createTime
     *
     * @param day current day string. eg:2019-08-08
     * @return count
     */
    Long getBookCountByCreateTime(String day);

    List<Map<String, Long>> getSpiderData();

}

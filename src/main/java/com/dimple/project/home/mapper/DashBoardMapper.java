package com.dimple.project.home.mapper;

import com.dimple.project.log.domain.LoginLog;
import com.dimple.project.log.domain.OperateLog;
import com.dimple.project.log.domain.QuartzJobLog;
import com.dimple.project.log.domain.VisitLog;
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

    /**
     * 获取visitLog
     *
     * @return visitLog
     */
    List<VisitLog> getVisitLog();

    /**
     * 根据Id获取blog的title
     *
     * @param pageId blog的id
     * @return blog的title
     */
    String getBlogNameByPageId(Long pageId);

    /**
     * 获取登录日志
     *
     * @return 登录日志
     */
    List<LoginLog> getLoginLogList();

    /**
     * 获取操作日志
     *
     * @return 操作日志
     */
    List<OperateLog> getOperateLogList();

    /**
     * 获取任务日志
     *
     * @return 任务日志
     */
    List<QuartzJobLog> getQuartzJobLogList();
}

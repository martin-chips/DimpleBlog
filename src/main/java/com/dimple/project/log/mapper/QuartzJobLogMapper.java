package com.dimple.project.log.mapper;

import com.dimple.project.log.domain.QuartzJobLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @className: QuartzJobLogMapper
 * @description:
 * @author: Dimple
 * @date: 11/07/19
 */
public interface QuartzJobLogMapper {
    /**
     * 插入Job log
     *
     * @param quartzJobLog log
     * @return 受影响的行数
     */
    int insertQuartzJobLog(QuartzJobLog quartzJobLog);

    /**
     * 根据条件查询Log
     *
     * @param quartzJobLog 条件
     * @return list
     */
    List<QuartzJobLog> selectQuartzJobLogList(QuartzJobLog quartzJobLog);

    /**
     * 根据id删除数据
     *
     * @param ids      id数组
     * @param username 操作者
     * @return 受影响的行数
     */
    int deleteQuartzJobLogByIds(@Param("ids") Long[] ids, @Param("username") String username);

    /**
     * 清空日志
     *
     * @param username 操作者
     */
    void cleanQuartzJobLog(String username);

    /**
     * select quartz log by id
     *
     * @param id id
     * @return quartzLog
     */
    QuartzJobLog selectQuartzJobLogById(Long id);
}

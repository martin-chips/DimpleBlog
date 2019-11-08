package com.dimple.project.tool.mapper;

import com.dimple.project.tool.domain.QuartzJob;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @className: QuartzJobMapper
 * @description:
 * @author: Dimple
 * @date: 11/07/19
 */
public interface QuartzJobMapper {
    /**
     * 添加Job
     *
     * @param quartzJob job
     * @return 受影响的行数
     */
    int insertQuartzJob(QuartzJob quartzJob);

    /**
     * 更新Job
     *
     * @param quartzJob Job实体
     * @return 受影响的行数
     */
    int updateQuartzJob(QuartzJob quartzJob);

    /**
     * 根据Id查询Job
     *
     * @param id id
     * @return Job
     */
    QuartzJob selectQuartzJobById(Long id);

    /**
     * 根据Id删除Job
     *
     * @param id id
     * @return 受影响的行数
     */
    int deleteQuartzJobById(@Param("id") Long id, @Param("username") String username);

    /**
     * 查询Job list
     *
     * @param quartzJob job
     * @return list
     */
    List<QuartzJob> selectQuartzJobList(QuartzJob quartzJob);

    /**
     * 获取运行状态的job
     *
     * @return list
     */
    List<QuartzJob> selectRunningQuartzJobList();


}

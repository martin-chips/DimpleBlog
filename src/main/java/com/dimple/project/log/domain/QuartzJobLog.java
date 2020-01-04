package com.dimple.project.log.domain;

import com.dimple.framework.web.domain.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @className: QuartzJobLog
 * @description:
 * @author: Dimple
 * @date: 11/07/19
 */
@Data
public class QuartzJobLog extends BaseEntity implements Serializable {

    private Long id;
    /**
     * 任务名
     */
    private String jobName;
    /**
     * Bean名
     */
    private String beanName;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 方法参数
     */
    private String methodParams;
    /**
     * Cron表达式
     */
    private String cronExpression;
    /**
     * 异常信息
     */
    private String exception;
    /**
     * 是否成功,成功为true,失败为false
     */
    private Boolean status;
    /**
     * 花费的毫秒数
     */
    private Long cost;
    /**
     * 返回值
     */
    private String result;
}

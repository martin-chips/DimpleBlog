package com.dimple.modules.endModule.systemMonitor.bean;

import com.dimple.framework.constant.ScheduleConstants;
import com.dimple.modules.endModule.systemMonitor.util.CronUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * @className: Job
 * @description:
 * @auther: Owenb
 * @date: 01/29/19
 * @version: 1.0
 */
@Entity
@Table(name = "job", schema = "dimple_blog")
@Data
public class Job implements Serializable {
    @Id
    @Column(name = "job_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;
    private String jobName;
    private String jobGroup;
    private String methodName;
    private String methodParams;
    private String cronExpression;
    //Cron 计划策略 0表示默认，1表示立即触发执行，2表示马上执行，3表示不执行
    private String misfirePolicy = ScheduleConstants.MISFIRE_DEFAULT;
    //任务状态 （1表示暂停，0表示执行）
    private Integer status;
    private Date createTime;
    private String description;

    @Transient
    //下一次执行的时间
    private Date nextExecution;

    /**
     * 获取下一次的执行时间
     *
     * @return
     */
    public Date getNextValidTime() {
        if (StringUtils.isNotBlank(cronExpression)) {
            return CronUtil.getNextExecution(cronExpression);
        }
        return null;
    }
}

package com.dimple.modules.endModule.logManager.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @className: JobLog
 * @description:
 * @auther: Owenb
 * @date: 01/30/19
 * @version: 1.0
 */
@Data
@Entity
@Table(name = "job_log", schema = "dimple_blog")
public class JobLog {
    @Id
    @Column(name = "job_log_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobLogId;
    private String jobName;
    private String jobGroup;
    private String methodName;
    private String methodParams;
    private String jobMessage;
    //执行状态，true表示成功，false表示失败
    private Boolean status;
    private Date createTime;
    //异常信息
    private String exceptionInfo;
}

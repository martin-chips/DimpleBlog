package com.dimple.project.tool.domain;

import com.dimple.framework.web.domain.BaseEntity;
import lombok.Data;
import net.sf.jsqlparser.statement.update.Update;

import javax.validation.constraints.NotNull;

/**
 * @className: QuartzJob
 * @description:
 * @author: Dimple
 * @date: 11/07/19
 */
@Data
public class QuartzJob extends BaseEntity {
    public static final String JOB_KEY = "JOB_KEY";

    @NotNull(groups = {Update.class})
    private Long id;
    /**
     * 任务名
     */
    private String jobName;
    /**
     * Bean的名字
     */
    private String beanName;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 参数
     */
    private String methodParams;
    /**
     * corn表达式
     */
    private String cronExpression;
    /**
     * 状态:false表示暂停,true表示运行
     */
    private Boolean status;
    /**
     * 备注
     */
    private String remark;

}

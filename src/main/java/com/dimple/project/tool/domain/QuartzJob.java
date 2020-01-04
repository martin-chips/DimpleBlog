package com.dimple.project.tool.domain;

import com.dimple.framework.web.domain.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @className: QuartzJob
 * @description:
 * @author: Dimple
 * @date: 11/07/19
 */
@Data
public class QuartzJob extends BaseEntity implements Serializable {
    public static final String JOB_KEY = "JOB_KEY";

    @NotNull(groups = Update.class)
    private Long id;
    /**
     * 任务名
     */
    @NotNull(message = "任务名不能为空")
    private String jobName;
    /**
     * Bean的名字
     */
    @NotNull(message = "Bean名不能为空")
    private String beanName;
    /**
     * 方法名
     */
    @NotNull(message = "方法名不能为空")
    private String methodName;
    /**
     * 参数
     */
    private String methodParams;
    /**
     * corn表达式
     */
    @NotNull(message = "corn表达式不能为空")
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

package com.dimple.project.log.domain;

import com.dimple.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @className: OperateLog
 * @description: 操作日志记录表 SysOperateLog
 * @author: Dimple
 * @date: 10/22/19
 */
@Data
//序列化Json的时候,如果是Null则忽略
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OperateLog extends BaseEntity implements Serializable {
    /**
     * 日志主键
     */
    private Long id;

    /**
     * 操作模块
     */
    private String title;

    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     */
    private Integer businessType;

    /**
     * 业务类型数组
     */
    private Integer[] businessTypes;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 操作类别（0其它 1后台用户 2手机端用户）
     */
    private Integer operatorType;

    /**
     * 操作人员
     */
    private String operateName;


    /**
     * 请求url
     */
    private String url;

    /**
     * 操作地址
     */
    private String ip;

    /**
     * 操作地点
     */
    private String location;

    /**
     * 请求参数
     */
    private String param;

    /**
     * 返回参数
     */
    private String jsonResult;

    /**
     * 操作状态（true正常 false异常）
     */
    private Boolean status;

    /**
     * 错误消息
     */
    private String errorMsg;

    /**
     * 耗时
     */
    private Long cost;
}

package com.dimple.bean;

import java.io.Serializable;
import java.util.Date;

public class OperateLog implements Serializable {
    /**
     * 操作序号

     */
    private Integer operateId;

    /**
     * 操作模块名称
     */
    private String title;

    /**
     * 操作类型

     */
    private Integer operateType;

    /**
     * 请求的方法
     */
    private String method;

    /**
     * 请求的URL
     */
    private String operateUrl;

    /**
     * 操作人员
     */
    private String operatorName;

    /**
     * 操作的IP地址
     */
    private String operateIp;

    /**
     * 操作的地点
     */
    private String operateLocation;

    /**
     * 请求的参数
     */
    private String operateParam;

    /**
     * 状态（1、true表示正常，0，false表示异常）
     */
    private Boolean operateStatus;

    /**
     * 错误消息
     */
    private String errorMsg;

    /**
     * 操作时间
     */
    private Date operateTime;

    /**
     * operate_log
     */
    private static final long serialVersionUID = 1L;

    /**
     * 操作序号

     * @return operate_id 操作序号

     */
    public Integer getOperateId() {
        return operateId;
    }

    /**
     * 操作序号

     * @param operateId 操作序号

     */
    public void setOperateId(Integer operateId) {
        this.operateId = operateId;
    }

    /**
     * 操作模块名称
     * @return title 操作模块名称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 操作模块名称
     * @param title 操作模块名称
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 操作类型

     * @return operate_type 操作类型

     */
    public Integer getOperateType() {
        return operateType;
    }

    /**
     * 操作类型

     * @param operateType 操作类型

     */
    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    /**
     * 请求的方法
     * @return method 请求的方法
     */
    public String getMethod() {
        return method;
    }

    /**
     * 请求的方法
     * @param method 请求的方法
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * 请求的URL
     * @return operate_url 请求的URL
     */
    public String getOperateUrl() {
        return operateUrl;
    }

    /**
     * 请求的URL
     * @param operateUrl 请求的URL
     */
    public void setOperateUrl(String operateUrl) {
        this.operateUrl = operateUrl;
    }

    /**
     * 操作人员
     * @return operator_name 操作人员
     */
    public String getOperatorName() {
        return operatorName;
    }

    /**
     * 操作人员
     * @param operatorName 操作人员
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    /**
     * 操作的IP地址
     * @return operate_ip 操作的IP地址
     */
    public String getOperateIp() {
        return operateIp;
    }

    /**
     * 操作的IP地址
     * @param operateIp 操作的IP地址
     */
    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp;
    }

    /**
     * 操作的地点
     * @return operate_location 操作的地点
     */
    public String getOperateLocation() {
        return operateLocation;
    }

    /**
     * 操作的地点
     * @param operateLocation 操作的地点
     */
    public void setOperateLocation(String operateLocation) {
        this.operateLocation = operateLocation;
    }

    /**
     * 请求的参数
     * @return operate_param 请求的参数
     */
    public String getOperateParam() {
        return operateParam;
    }

    /**
     * 请求的参数
     * @param operateParam 请求的参数
     */
    public void setOperateParam(String operateParam) {
        this.operateParam = operateParam;
    }

    /**
     * 状态（1、true表示正常，0，false表示异常）
     * @return operate_status 状态（1、true表示正常，0，false表示异常）
     */
    public Boolean getOperateStatus() {
        return operateStatus;
    }

    /**
     * 状态（1、true表示正常，0，false表示异常）
     * @param operateStatus 状态（1、true表示正常，0，false表示异常）
     */
    public void setOperateStatus(Boolean operateStatus) {
        this.operateStatus = operateStatus;
    }

    /**
     * 错误消息
     * @return error_msg 错误消息
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * 错误消息
     * @param errorMsg 错误消息
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * 操作时间
     * @return operate_time 操作时间
     */
    public Date getOperateTime() {
        return operateTime;
    }

    /**
     * 操作时间
     * @param operateTime 操作时间
     */
    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }
}
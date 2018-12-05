package com.dimple.bean;

import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@ToString
public class OperatorLog implements Serializable {
    /**
     * 操作序号

     */
    private Integer operid;

    /**
     * 操作模块名称
     */
    private String title;

    /**
     * 操作类型

     */
    private String operatorType;

    /**
     * 请求的方法
     */
    private String method;

    /**
     * 来源渠道
     */
    private String channel;

    /**
     * 请求的URL
     */
    private String operatorUrl;

    /**
     * 操作人员
     */
    private String operatorName;

    /**
     * 操作的IP地址
     */
    private String operatorIp;

    /**
     * 操作的地点
     */
    private String operatorLocation;

    /**
     * 请求的参数
     */
    private String operatorParam;

    /**
     * 状态（1、true表示正常，0，false表示异常）
     */
    private Boolean status;

    /**
     * 错误消息
     */
    private String errorMsg;

    /**
     * 操作时间
     */
    private Date operatorTime;

    /**
     * operator_log
     */
    private static final long serialVersionUID = 1L;

    /**
     * 操作序号

     * @return operId 操作序号

     */
    public Integer getOperid() {
        return operid;
    }

    /**
     * 操作序号

     * @param operid 操作序号

     */
    public void setOperid(Integer operid) {
        this.operid = operid;
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

     * @return operator_type 操作类型

     */
    public String getOperatorType() {
        return operatorType;
    }

    /**
     * 操作类型

     * @param operatorType 操作类型

     */
    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType;
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
     * 来源渠道
     * @return channel 来源渠道
     */
    public String getChannel() {
        return channel;
    }

    /**
     * 来源渠道
     * @param channel 来源渠道
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     * 请求的URL
     * @return operator_url 请求的URL
     */
    public String getOperatorUrl() {
        return operatorUrl;
    }

    /**
     * 请求的URL
     * @param operatorUrl 请求的URL
     */
    public void setOperatorUrl(String operatorUrl) {
        this.operatorUrl = operatorUrl;
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
     * @return operator_ip 操作的IP地址
     */
    public String getOperatorIp() {
        return operatorIp;
    }

    /**
     * 操作的IP地址
     * @param operatorIp 操作的IP地址
     */
    public void setOperatorIp(String operatorIp) {
        this.operatorIp = operatorIp;
    }

    /**
     * 操作的地点
     * @return operator_location 操作的地点
     */
    public String getOperatorLocation() {
        return operatorLocation;
    }

    /**
     * 操作的地点
     * @param operatorLocation 操作的地点
     */
    public void setOperatorLocation(String operatorLocation) {
        this.operatorLocation = operatorLocation;
    }

    /**
     * 请求的参数
     * @return operator_param 请求的参数
     */
    public String getOperatorParam() {
        return operatorParam;
    }

    /**
     * 请求的参数
     * @param operatorParam 请求的参数
     */
    public void setOperatorParam(String operatorParam) {
        this.operatorParam = operatorParam;
    }

    /**
     * 状态（1、true表示正常，0，false表示异常）
     * @return status 状态（1、true表示正常，0，false表示异常）
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * 状态（1、true表示正常，0，false表示异常）
     * @param status 状态（1、true表示正常，0，false表示异常）
     */
    public void setStatus(Boolean status) {
        this.status = status;
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
     * @return operator_time 操作时间
     */
    public Date getOperatorTime() {
        return operatorTime;
    }

    /**
     * 操作时间
     * @param operatorTime 操作时间
     */
    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }
}
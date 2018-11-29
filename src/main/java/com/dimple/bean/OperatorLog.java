package com.dimple.bean;

import java.io.Serializable;
import java.util.Date;

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
    private String action;

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
    private String operUrl;

    /**
     * 操作人员
     */
    private String operName;

    /**
     * 操作的IP地址
     */
    private String operIp;

    /**
     * 操作的地点
     */
    private String operLocation;

    /**
     * 请求的参数
     */
    private String operParam;

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
    private Date operTime;

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

     * @return action 操作类型

     */
    public String getAction() {
        return action;
    }

    /**
     * 操作类型

     * @param action 操作类型

     */
    public void setAction(String action) {
        this.action = action;
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
     * @return oper_url 请求的URL
     */
    public String getOperUrl() {
        return operUrl;
    }

    /**
     * 请求的URL
     * @param operUrl 请求的URL
     */
    public void setOperUrl(String operUrl) {
        this.operUrl = operUrl;
    }

    /**
     * 操作人员
     * @return oper_name 操作人员
     */
    public String getOperName() {
        return operName;
    }

    /**
     * 操作人员
     * @param operName 操作人员
     */
    public void setOperName(String operName) {
        this.operName = operName;
    }

    /**
     * 操作的IP地址
     * @return oper_ip 操作的IP地址
     */
    public String getOperIp() {
        return operIp;
    }

    /**
     * 操作的IP地址
     * @param operIp 操作的IP地址
     */
    public void setOperIp(String operIp) {
        this.operIp = operIp;
    }

    /**
     * 操作的地点
     * @return oper_location 操作的地点
     */
    public String getOperLocation() {
        return operLocation;
    }

    /**
     * 操作的地点
     * @param operLocation 操作的地点
     */
    public void setOperLocation(String operLocation) {
        this.operLocation = operLocation;
    }

    /**
     * 请求的参数
     * @return oper_param 请求的参数
     */
    public String getOperParam() {
        return operParam;
    }

    /**
     * 请求的参数
     * @param operParam 请求的参数
     */
    public void setOperParam(String operParam) {
        this.operParam = operParam;
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
     * @return oper_time 操作时间
     */
    public Date getOperTime() {
        return operTime;
    }

    /**
     * 操作时间
     * @param operTime 操作时间
     */
    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }
}
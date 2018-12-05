package com.dimple.bean;

import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@ToString
public class LoginLog implements Serializable {
    /**
     * 登录日志记录的主键
     */
    private Integer logId;

    /**
     * 登录的用户名称	
     */
    private String loginName;

    /**
     * 登录的状态（1表示登录成功，0表示失败，2表示退出成功
     */
    private Boolean status;

    /**
     * 登录的IP地址
     */
    private String ipAddress;

    /**
     * 登录的地理位置

     */
    private String loginLocation;

    /**
     * 登录的浏览器类型
     */
    private String browser;

    /**
     * 登录的设备的类型
     */
    private String os;

    /**
     * 登录的信息

     */
    private String msg;

    /**
     * 登录的时间
     */
    private Date loginTime;

    /**
     * login_log
     */
    private static final long serialVersionUID = 1L;

    /**
     * 登录日志记录的主键
     * @return log_id 登录日志记录的主键
     */
    public Integer getLogId() {
        return logId;
    }

    /**
     * 登录日志记录的主键
     * @param logId 登录日志记录的主键
     */
    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    /**
     * 登录的用户名称	
     * @return login_name 登录的用户名称	
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 登录的用户名称	
     * @param loginName 登录的用户名称	
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * 登录的状态（1表示登录成功，0表示失败，2表示退出成功
     * @return status 登录的状态（1表示登录成功，0表示失败，2表示退出成功
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * 登录的状态（1表示登录成功，0表示失败，2表示退出成功
     * @param status 登录的状态（1表示登录成功，0表示失败，2表示退出成功
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * 登录的IP地址
     * @return ip_address 登录的IP地址
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * 登录的IP地址
     * @param ipAddress 登录的IP地址
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * 登录的地理位置

     * @return login_location 登录的地理位置

     */
    public String getLoginLocation() {
        return loginLocation;
    }

    /**
     * 登录的地理位置

     * @param loginLocation 登录的地理位置

     */
    public void setLoginLocation(String loginLocation) {
        this.loginLocation = loginLocation;
    }

    /**
     * 登录的浏览器类型
     * @return browser 登录的浏览器类型
     */
    public String getBrowser() {
        return browser;
    }

    /**
     * 登录的浏览器类型
     * @param browser 登录的浏览器类型
     */
    public void setBrowser(String browser) {
        this.browser = browser;
    }

    /**
     * 登录的设备的类型
     * @return os 登录的设备的类型
     */
    public String getOs() {
        return os;
    }

    /**
     * 登录的设备的类型
     * @param os 登录的设备的类型
     */
    public void setOs(String os) {
        this.os = os;
    }

    /**
     * 登录的信息

     * @return msg 登录的信息

     */
    public String getMsg() {
        return msg;
    }

    /**
     * 登录的信息

     * @param msg 登录的信息

     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 登录的时间
     * @return login_time 登录的时间
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 登录的时间
     * @param loginTime 登录的时间
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
}
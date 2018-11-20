package com.dimple.bean;

import java.io.Serializable;
import java.util.Date;

public class Visitor implements Serializable {
    /**
     * 游客的id
     */
    private Integer visitorId;

    /**
     * 游客的登录地址
     */
    private String address;

    /**
     * 游客登录的浏览器
     */
    private String browser;

    /**
     * 游客登录的ip
     */
    private String ip;

    /**
     * 游客账户是否被锁定
     */
    private Byte locked;

    /**
     * 游客访问时间
     */
    private Date visitTime;

    /**
     * visitor
     */
    private static final long serialVersionUID = 1L;

    /**
     * 游客的id
     * @return visitor_id 游客的id
     */
    public Integer getVisitorId() {
        return visitorId;
    }

    /**
     * 游客的id
     * @param visitorId 游客的id
     */
    public void setVisitorId(Integer visitorId) {
        this.visitorId = visitorId;
    }

    /**
     * 游客的登录地址
     * @return address 游客的登录地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 游客的登录地址
     * @param address 游客的登录地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 游客登录的浏览器
     * @return browser 游客登录的浏览器
     */
    public String getBrowser() {
        return browser;
    }

    /**
     * 游客登录的浏览器
     * @param browser 游客登录的浏览器
     */
    public void setBrowser(String browser) {
        this.browser = browser;
    }

    /**
     * 游客登录的ip
     * @return ip 游客登录的ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * 游客登录的ip
     * @param ip 游客登录的ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 游客账户是否被锁定
     * @return locked 游客账户是否被锁定
     */
    public Byte getLocked() {
        return locked;
    }

    /**
     * 游客账户是否被锁定
     * @param locked 游客账户是否被锁定
     */
    public void setLocked(Byte locked) {
        this.locked = locked;
    }

    /**
     * 游客访问时间
     * @return visit_time 游客访问时间
     */
    public Date getVisitTime() {
        return visitTime;
    }

    /**
     * 游客访问时间
     * @param visitTime 游客访问时间
     */
    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }
}
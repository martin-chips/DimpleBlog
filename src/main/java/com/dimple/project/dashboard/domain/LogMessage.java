package com.dimple.project.dashboard.domain;

import lombok.Data;

import java.util.Date;

/**
 * @className: LogMessage
 * @description: dashboard展示的最新消息
 * @auther: Dimple
 * @date: 04/10/19
 * @version: 1.0
 */
@Data
public class LogMessage {
    /**
     * Date String ,如“刚刚”等
     */
    String dateStr;
    /**
     * 具体时间
     */
    Date date;
    /**
     * 消息
     */
    String message;
}

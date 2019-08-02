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
    private String dateStr;
    /**
     * 具体时间
     */
    private Date date;
    /**
     * 消息
     */
    private String message;
    /**
     * 展示样式
     */
    private String style;

    private LogMessage(Builder builder) {
        this.date = builder.date;
        this.message = builder.message;
        this.style = builder.style;
        this.dateStr = builder.dateStr;
    }

    public static class Builder {
        private String dateStr;
        private Date date;
        private String message;
        private String style;

        public Builder dateStr(String dateStr) {
            this.dateStr = dateStr;
            return this;
        }

        public Builder date(Date date) {
            this.date = date;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder style(String style) {
            this.style = style;
            return this;
        }

        public LogMessage builder() {
            return new LogMessage(this);
        }
    }


}

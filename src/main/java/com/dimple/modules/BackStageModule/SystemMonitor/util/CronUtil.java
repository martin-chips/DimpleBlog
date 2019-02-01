package com.dimple.modules.BackStageModule.SystemMonitor.util;

import org.quartz.CronExpression;

import java.text.ParseException;
import java.util.Date;

/**
 * @className: CronUtil
 * @description:
 * @auther: Owenb
 * @date: 01/29/19
 * @version: 1.0
 */
public class CronUtil {
    /**
     * 检查Cron表达式是否有效
     *
     * @param expression
     * @return
     */
    public static Boolean checkCronExpression(String expression) {
        return CronExpression.isValidExpression(expression);
    }


    /**
     * 返回下一个执行时间根据给定的Cron表达式
     *
     * @param cronExpression Cron表达式
     * @return Date 下次Cron表达式执行时间
     */
    public static Date getNextExecution(String cronExpression) {
        try {
            CronExpression cron = new CronExpression(cronExpression);
            return cron.getNextValidTimeAfter(new Date(System.currentTimeMillis()));
        } catch (ParseException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}

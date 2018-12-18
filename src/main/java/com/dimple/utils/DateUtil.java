package com.dimple.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.lang.management.ManagementFactory;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : DateUtilNew
 * @description : 时间操作类
 * @date : 12/18/18 19:48
 */
public class DateUtil extends org.apache.commons.lang3.time.DateUtils {

    public static final String SIMPLE_DATETIME_FORMAT = "yyyy-MM-dd";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final long MILLISECONDS_FOR_ONE_MINUTE = 60 * 1000;
    public static final long MILLISECONDS_FOR_ONE_HOUR = 60 * MILLISECONDS_FOR_ONE_MINUTE;
    public static final long MILLISECONDS_FOR_ONE_DAY = 24 * MILLISECONDS_FOR_ONE_HOUR;

    /**
     * 获取当前日期，只包年月日
     */
    public static Date getCurrentDate() {
        Calendar c = Calendar.getInstance();
        return stringToDate(dateToShortDateString(c.getTime()));
    }

    /**
     * 获取当前的时间
     *
     * @return
     */
    public static Date getDateNow() {
        return new Date();
    }

    /**
     * 判断两个时间是不是同一天
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDay(Date date1, Date date2) {
        if (calcIntervalDays(date1, date2) == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static Calendar toCalendar(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c;
    }

    /**
     * 计算两个时间的间隔天数
     */
    public static int calcIntervalDays(Date date1, Date date2) {
        if (date2.after(date1)) {
            return Long.valueOf((date2.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24)).intValue();
        } else if (date2.before(date1)) {
            return Long.valueOf((date1.getTime() - date2.getTime()) / (1000 * 60 * 60 * 24)).intValue();
        } else {
            return 0;
        }
    }

    /**
     * 返回对应的时间是星期几
     *
     * @param date
     * @return
     */
    public static int dayOfWeek(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int dayofWeek;
        if (ca.get(Calendar.DAY_OF_WEEK) == 1) {
            dayofWeek = 7;
        } else {
            dayofWeek = ca.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayofWeek;
    }

    /**
     * 获取今天的分钟数，如今天18:05，则返回1805
     */
    public static int getTodayMinutes() {
        Calendar ca = Calendar.getInstance();
        int hours = ca.get(Calendar.HOUR_OF_DAY);
        int minutes = ca.get(Calendar.MINUTE);
        return hours * 60 + minutes;
    }

    /**
     * 获取指定间隔天数的日期
     */
    public static Date getIntervalDate(Date time, int days) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(time);
        ca.add(Calendar.DATE, days);
        return stringToDate(dateToShortDateString(ca.getTime()));
    }

    public static String dateToShortDateString(Date date) {
        return dateToString(date, "yyyy-MM-dd");
    }

    public static String dateToString(Date date, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        try {
            return dateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date stringToDate(String dateStr) {
        SimpleDateFormat format = null;
        if (dateStr.contains("/")) {
            if (dateStr.contains(":") && dateStr.contains(" ")) {
                format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            } else {
                format = new SimpleDateFormat("yyyy/MM/dd");
            }
        } else if (dateStr.contains("-")) {
            if (dateStr.contains(":") && dateStr.contains(" ")) {
                format = new SimpleDateFormat(DATETIME_FORMAT);
            } else {
                format = new SimpleDateFormat(SIMPLE_DATETIME_FORMAT);
            }
        }
        if (format == null) {
            return null;
        }
        format.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        try {
            return format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

    /**
     * 全站时间展示规范
     * 1分钟内：刚刚
     * 超过1分钟并在1小时内：某分钟前 （1分钟前）
     * 超过1小时并在当日内：某小时前（1小时前）
     * 昨天：昨天 + 小时分钟（昨天 08:30）
     * 昨天之前并在当年内：某月某日 + 小时分钟（1月1日 08:30）
     * 隔年：某年某月某日 + 小时分钟（2015年1月1日 08:30）
     */
    public static String dateToVoString(Date date) {
        Date now = new Date();
        long deltaMilliSeconds = now.getTime() - date.getTime();
        Calendar dateCalendar = toCalendar(date);
        Calendar nowCalendar = toCalendar(now);

        if (nowCalendar.get(Calendar.YEAR) == dateCalendar.get(Calendar.YEAR)) {
            if (isSameDay(date, now)) {
                if (deltaMilliSeconds < MILLISECONDS_FOR_ONE_MINUTE) {
                    return "刚刚";
                } else if (deltaMilliSeconds < MILLISECONDS_FOR_ONE_HOUR) {
                    return String.format("%d分钟前", deltaMilliSeconds / MILLISECONDS_FOR_ONE_MINUTE);
                } else if (deltaMilliSeconds < MILLISECONDS_FOR_ONE_DAY) {
                    return String.format("%d小时前", deltaMilliSeconds / MILLISECONDS_FOR_ONE_HOUR);
                }
            }

            if (isSameDay(date, getIntervalDate(now, -1))) {
                return String.format("昨天 %d:%02d", dateCalendar.get(Calendar.HOUR_OF_DAY),
                        dateCalendar.get(Calendar.MINUTE));
            } else {
                return String.format("%d月%d日 %d:%02d", dateCalendar.get(Calendar.MONTH) + 1,
                        dateCalendar.get(Calendar.DAY_OF_MONTH),
                        dateCalendar.get(Calendar.HOUR_OF_DAY), dateCalendar.get(Calendar.MINUTE));
            }
        } else {
            return String.format("%d年%d月%d日 %d:%02d", dateCalendar.get(Calendar.YEAR),
                    dateCalendar.get(Calendar.MONTH) + 1,
                    dateCalendar.get(Calendar.DAY_OF_MONTH), dateCalendar.get(Calendar.HOUR_OF_DAY),
                    dateCalendar.get(Calendar.MINUTE));
        }
    }

    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

}

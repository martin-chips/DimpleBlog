package com.dimple.bean.server.item;

import com.dimple.utils.Arith;
import com.dimple.utils.DateUtil;
import lombok.Data;

import java.lang.management.ManagementFactory;

/**
 * @ClassName: JVM
 * @Description: JVM使用情况
 * @Auther: Owenb
 * @Date: 12/07/18 9:53
 * @Version: 1.0
 */
@Data
public class JVM {
    /**
     * 当前JVM占用的内存总数(M)
     */
    private double total;

    /**
     * JVM最大可用内存总数(M)
     */
    private double max;

    /**
     * JVM空闲内存(M)
     */
    private double free;

    /**
     * JDK版本
     */
    private String version;

    /**
     * JDK路径
     */
    private String home;

    /**
     * JDK启动时间
     */
    public String getStartTime() {
        return DateUtil.parseDateToStr(DateUtil.YYYY_MM_DD_HH_MM_SS, DateUtil.getServerStartDate());
    }

    /**
     * JDK运行时间
     */
    public String getRunTime() {
        return DateUtil.getDatePoor(DateUtil.getNowDate(), DateUtil.getServerStartDate());
    }

    /**
     * 获取JDK名称
     */
    public String getName() {
        return ManagementFactory.getRuntimeMXBean().getVmName();
    }

    public double getUsed() {
        return Arith.div(total - free, (1024 * 1024), 2);
    }

    public double getUsage() {
        return Arith.mul(Arith.div(total - free, total, 4), 100);
    }

    public double getFree() {
        return Arith.div(free, (1024 * 1024), 2);
    }

    public double getMax() {
        return Arith.div(max, (1024 * 1024), 2);
    }

    public double getTotal() {
        return Arith.div(total, (1024 * 1024), 2);
    }
}

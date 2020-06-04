package com.dimple.project.monitor.service.impl;

import com.dimple.common.utils.DateUtils;
import com.dimple.common.utils.file.FileUtils;
import com.dimple.common.utils.ip.IpUtils;
import com.dimple.project.monitor.service.ServerService;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;
import oshi.util.Util;

import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * description
 * </p>
 *
 * @author Dimple
 * @date 06/04/20 16:52
 */
@Service
public class ServerServiceImpl implements ServerService {
    private final DecimalFormat df = new DecimalFormat("0.00");

    @Override
    public Map<String, Object> getServers() {
        Map<String, Object> resultMap = new LinkedHashMap<>(8);
        try {
            SystemInfo si = new SystemInfo();
            OperatingSystem os = si.getOperatingSystem();
            HardwareAbstractionLayer hal = si.getHardware();
            // 系统信息
            resultMap.put("sys", getSystemInfo(os));
            // cpu 信息
            resultMap.put("cpu", getCpuInfo(hal.getProcessor()));
            // 内存信息
            resultMap.put("memory", getMemoryInfo(hal.getMemory()));
            // 交换区信息
            resultMap.put("swap", getSwapInfo(hal.getMemory()));
            // 磁盘
            resultMap.put("disk", getDiskInfo(os));
            resultMap.put("time", DateUtils.parseDateToStr("HH:mm:ss", new Date()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 获取磁盘信息
     *
     * @return /
     */
    private Map<String, Object> getDiskInfo(OperatingSystem os) {
        Map<String, Object> diskInfo = new LinkedHashMap<>();
        FileSystem fileSystem = os.getFileSystem();
        List<OSFileStore> fsArray = Arrays.asList(fileSystem.getFileStores());
        for (OSFileStore fs : fsArray) {
            diskInfo.put("total", fs.getTotalSpace() > 0 ? FileUtils.getSizeString(fs.getTotalSpace()) : "?");
            long used = fs.getTotalSpace() - fs.getUsableSpace();
            diskInfo.put("available", FileUtils.getSizeString(fs.getUsableSpace()));
            diskInfo.put("used", FileUtils.getSizeString(used));
            diskInfo.put("usageRate", df.format(used / (double) fs.getTotalSpace() * 100));
        }
        return diskInfo;
    }

    /**
     * 获取交换区信息
     *
     * @param memory /
     * @return /
     */
    private Map<String, Object> getSwapInfo(GlobalMemory memory) {
        Map<String, Object> swapInfo = new LinkedHashMap<>();
        swapInfo.put("total", FormatUtil.formatBytes(memory.getVirtualMemory().getSwapTotal()));
        swapInfo.put("used", FormatUtil.formatBytes(memory.getVirtualMemory().getSwapUsed()));
        swapInfo.put("available", FormatUtil.formatBytes(memory.getVirtualMemory().getSwapTotal() - memory.getVirtualMemory().getSwapUsed()));
        swapInfo.put("usageRate", df.format(memory.getVirtualMemory().getSwapUsed() / (double) memory.getVirtualMemory().getSwapTotal() * 100));
        return swapInfo;
    }

    /**
     * 获取内存信息
     *
     * @param memory /
     * @return /
     */
    private Map<String, Object> getMemoryInfo(GlobalMemory memory) {
        Map<String, Object> memoryInfo = new LinkedHashMap<>();
        memoryInfo.put("total", FormatUtil.formatBytes(memory.getTotal()));
        memoryInfo.put("available", FormatUtil.formatBytes(memory.getAvailable()));
        memoryInfo.put("used", FormatUtil.formatBytes(memory.getTotal() - memory.getAvailable()));
        memoryInfo.put("usageRate", df.format((memory.getTotal() - memory.getAvailable()) / (double) memory.getTotal() * 100));
        return memoryInfo;
    }

    /**
     * 获取Cpu相关信息
     *
     * @param processor /
     * @return /
     */
    private Map<String, Object> getCpuInfo(CentralProcessor processor) {
        Map<String, Object> cpuInfo = new LinkedHashMap<>();
        cpuInfo.put("name", processor.getProcessorIdentifier().getName());
        cpuInfo.put("package", processor.getPhysicalPackageCount() + "个物理CPU");
        cpuInfo.put("core", processor.getPhysicalProcessorCount() + "个物理核心");
        cpuInfo.put("coreNumber", processor.getPhysicalProcessorCount());
        cpuInfo.put("logic", processor.getLogicalProcessorCount() + "个逻辑CPU");
        // CPU信息
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        // 等待1秒...
        Util.sleep(1000);
        long[] ticks = processor.getSystemCpuLoadTicks();
        long user = ticks[CentralProcessor.TickType.USER.getIndex()] - prevTicks[CentralProcessor.TickType.USER.getIndex()];
        long nice = ticks[CentralProcessor.TickType.NICE.getIndex()] - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
        long sys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()] - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
        long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()] - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
        long iowait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()] - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
        long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()] - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
        long softirq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()] - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
        long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()] - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
        long totalCpu = user + nice + sys + idle + iowait + irq + softirq + steal;
        cpuInfo.put("used", df.format(100d * user / totalCpu + 100d * sys / totalCpu));
        cpuInfo.put("idle", df.format(100d * idle / totalCpu));
        return cpuInfo;
    }

    /**
     * 获取系统相关信息,系统、运行天数、系统IP
     *
     * @param os /
     * @return /
     */
    private Map<String, Object> getSystemInfo(OperatingSystem os) {
        Map<String, Object> systemInfo = new LinkedHashMap<>();
        // 计算项目运行时间
        String formatBetween = DateUtils.getDatePoor(DateUtils.getServerStartDate(), new Date());
        // 系统信息
        systemInfo.put("os", os.toString());
        systemInfo.put("day", formatBetween);
        systemInfo.put("ip", IpUtils.getHostIp());
        return systemInfo;
    }
}

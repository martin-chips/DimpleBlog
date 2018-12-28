package com.dimple.bean.server;

import com.dimple.bean.server.item.*;
import com.dimple.bean.server.item.System;
import com.dimple.utils.IpUtil;
import lombok.Data;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;
import oshi.util.Util;

import java.util.List;
import java.util.Properties;

/**
 * @ClassName: Server
 * @Description:
 * @Auther: Owenb
 * @Date: 12/07/18 9:52
 * @Version: 1.0
 */
@Data
public class Server {
    private static final int OSHI_WAIT_SECOND = 1000;

    private CPU cpu;
    private Memory memory;
    private JVM jvm;
    private System system;
    private List<SystemFile> systemFiles;

    /**
     * 初始化Server信息
     */
    public void init() {
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardwareAbstractionLayer = systemInfo.getHardware();


        this.cpu = setCPUInfo(hardwareAbstractionLayer.getProcessor());
        this.memory = setMemoryInfo(hardwareAbstractionLayer.getMemory());

        this.system = setSystemInfo();
        this.jvm = setJVMInfo();
        this.systemFiles = setSystemFileInfo(systemInfo.getOperatingSystem());
    }

    private List<SystemFile> setSystemFileInfo(OperatingSystem operatingSystem) {
//        FileSystem fileSystem = operatingSystem.getFileSystem();
//        List<SystemFile> systemFiles = new ArrayList<>();
//        OSFileStore[] fsArray = fileSystem.getFileStores();
//        for (OSFileStore fs : fsArray) {
//            long free = fs.getUsableSpace();
//            long total = fs.getTotalSpace();
//            long used = total - free;
//            SystemFile systemFile = new SystemFile();
//            systemFile.setDirName(fs.getMount());
//            systemFile.setSysTypeName(fs.getType());
//            systemFile.setTypeName(fs.getName());
//            systemFile.setTotal(convertFileSize(total));
//            systemFile.setFree(convertFileSize(free));
//            systemFile.setUsed(convertFileSize(used));
//            systemFile.setUsage(used / total);
//            systemFiles.add(systemFile);
//        }
//        return systemFiles;
        return null;
    }

    /**
     * 设置JVM信息
     *
     * @return
     */
    private JVM setJVMInfo() {
        JVM jvm = new JVM();
        Properties properties = java.lang.System.getProperties();
        jvm.setTotal(Runtime.getRuntime().totalMemory());
        jvm.setMax(Runtime.getRuntime().maxMemory());
        jvm.setFree(Runtime.getRuntime().freeMemory());
        jvm.setVersion(properties.getProperty("java.version"));
        jvm.setHome(properties.getProperty("java.home"));
        return jvm;
    }

    /**
     * 设置服务器信息
     *
     * @return
     */
    private System setSystemInfo() {
        System system = new System();
        Properties properties = java.lang.System.getProperties();
        system.setComputerIp(IpUtil.getLocalHostAddress());
        system.setComputerName(IpUtil.getLocalHostName());
        system.setOsName(properties.getProperty("os.name"));
        system.setOsArch(properties.getProperty("os.arch"));
        system.setProjectDir(properties.getProperty("user.dir"));
        return system;
    }

    /**
     * 设置内存相关的信息
     *
     * @param memory
     * @return
     */
    private Memory setMemoryInfo(GlobalMemory memory) {
        Memory mem = new Memory();
        mem.setTotal(memory.getTotal());
        mem.setFree(memory.getAvailable());
        mem.setUsed(memory.getTotal() - memory.getAvailable());
        return mem;
    }

    /**
     * 设置CPU的信息
     *
     * @param processor
     * @return
     */
    private CPU setCPUInfo(CentralProcessor processor) {
        CPU cpu = new CPU();
        // CPU信息
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        Util.sleep(OSHI_WAIT_SECOND);
        long[] ticks = processor.getSystemCpuLoadTicks();
        long nice = ticks[CentralProcessor.TickType.NICE.getIndex()] - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
        long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()] - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
        long softirq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()] - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
        long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()] - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
        long cSys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()] - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
        long user = ticks[CentralProcessor.TickType.USER.getIndex()] - prevTicks[CentralProcessor.TickType.USER.getIndex()];
        long iowait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()] - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
        long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()] - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
        long totalCpu = user + nice + cSys + idle + iowait + irq + softirq + steal;
        cpu.setCpuNum(processor.getLogicalProcessorCount());
        cpu.setTotal(totalCpu);
        cpu.setSys(cSys);
        cpu.setUsed(user);
        cpu.setWait(iowait);
        cpu.setFree(idle);
        return cpu;
    }

    /**
     * 字节转换
     *
     * @param size 字节大小
     * @return 转换后值
     */
    public String convertFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;
        if (size >= gb) {
            return String.format("%.1f GB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        } else {
            return String.format("%d B", size);
        }
    }
}

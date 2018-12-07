package com.dimple.bean.server.item;

import com.dimple.utils.Arith;
import lombok.Data;

/**
 * @ClassName: Memory
 * @Description: 系统内存信息
 * @Auther: Owenb
 * @Date: 12/07/18 9:54
 * @Version: 1.0
 */
@Data
public class Memory {
    /**
     * 内存总量
     */
    private double total;

    /**
     * 已用内存
     */
    private double used;

    /**
     * 剩余内存
     */
    private double free;

    public double getTotal() {
        return Arith.div(total, (1024 * 1024 * 1024), 2);
    }

    public double getUsed() {
        return Arith.div(used, (1024 * 1024 * 1024), 2);
    }

    public double getFree() {
        return Arith.div(free, (1024 * 1024 * 1024), 2);
    }

    public double getUsage() {
        return Arith.mul(Arith.div(used, total, 4), 100);
    }
}

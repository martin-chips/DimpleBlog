package com.dimple.bean.server.item;

import lombok.Data;

/**
 * @ClassName: SystemFile
 * @Description: 系统文件信息
 * @Auther: Owenb
 * @Date: 12/07/18 9:58
 * @Version: 1.0
 */
@Data
public class SystemFile {
    /**
     * 盘符路径
     */
    private String dirName;

    /**
     * 盘符类型
     */
    private String sysTypeName;

    /**
     * 文件类型
     */
    private String typeName;

    /**
     * 总大小
     */
    private String total;

    /**
     * 剩余大小
     */
    private String free;

    /**
     * 已经使用量
     */
    private String used;

    /**
     * 资源的使用率
     */
    private double usage;
}

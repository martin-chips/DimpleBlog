package com.dimple.bean.server.item;

import lombok.Data;

/**
 * @ClassName: System
 * @Description: 服务器相关信息
 * @Auther: Owenb
 * @Date: 12/07/18 9:57
 * @Version: 1.0
 */
@Data
public class System {
    /**
     * 服务器名称
     */
    private String computerName;

    /**
     * 服务器Ip
     */
    private String computerIp;

    /**
     * 项目路径
     */
    private String projectDir;

    /**
     * 操作系统
     */
    private String osName;

    /**
     * 系统架构
     */
    private String osArch;
}

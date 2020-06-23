package com.dimple.modules.system.service;

import java.util.Map;


/**
 * @className: MonitorService
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
public interface MonitorService {

    /**
     * 查询数据分页
     *
     * @return Map<String, Object>
     */
    Map<String, Object> getServers();
}

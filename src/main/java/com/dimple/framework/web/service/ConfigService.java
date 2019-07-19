package com.dimple.framework.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dimple.project.system.config.service.IConfigService;

/**
 * @className: ConfigService
 * @description:  html调用 thymeleaf 实现参数管理
 * @auther: Dimple
 * @Date: 2019/3/13
 * @Version: 1.1
 */
@Service("config")
public class ConfigService {
    @Autowired
    private IConfigService configService;

    /**
     * 根据键名查询参数配置信息
     *
     * @param configKey 参数名称
     * @return 参数键值
     */
    public String getKey(String configKey) {
        return configService.selectConfigByKey(configKey);
    }

}

package com.dimple.project.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.dimple.common.constant.UserConstants;
import com.dimple.common.enums.CacheConstants;
import com.dimple.common.exception.CustomException;
import com.dimple.common.utils.SecurityUtils;
import com.dimple.common.utils.StringUtils;
import com.dimple.framework.config.redis.CacheExpire;
import com.dimple.framework.config.redis.TimeType;
import com.dimple.project.system.domain.Config;
import com.dimple.project.system.mapper.ConfigMapper;
import com.dimple.project.system.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @className: ConfigServiceImpl
 * @description: 参数配置 服务层实现
 * @author: Dimple
 * @date: 10/22/19
 */
@Service
public class ConfigServiceImpl implements ConfigService {
    @Autowired
    private ConfigMapper configMapper;

    @Override
    public Config selectConfigById(Long configId) {
        Config config = new Config();
        config.setId(configId);
        return configMapper.selectConfig(config);
    }

    @Override
    public Config selectConfigByKey(String configKey) {
        Config config = new Config();
        config.setConfigKey(configKey);
        Config retConfig = configMapper.selectConfig(config);
        return Objects.isNull(retConfig) ? null : retConfig;
    }

    @Override
    public List<Config> selectConfigList(Config config) {
        return configMapper.selectConfigList(config);
    }

    @Override
    public int insertConfig(Config config) {
        return configMapper.insertConfig(config);
    }

    @Override
    public int updateConfig(Config config) {
        return configMapper.updateConfig(config);
    }

    @Override
    public int updateConfigByConfigKey(Config config) {
        //校验是否存在当前配置
        Config configDB = configMapper.selectConfig(config);
        if (Objects.isNull(configDB)) {
            return insertConfig(config);
        }
        return configMapper.updateConfigByConfigKey(config);
    }

    @Override
    public int deleteConfigById(Long id) {
        String loginUsername = SecurityUtils.getUsername();
        return configMapper.deleteConfigById(id, loginUsername);
    }

    @Override
    public String checkConfigKeyUnique(Config config) {
        Long id = StringUtils.isNull(config.getId()) ? -1L : config.getId();
        Config info = configMapper.checkConfigKeyUnique(config.getConfigKey());
        if (StringUtils.isNotNull(info) && info.getId().longValue() != id.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    @Cacheable(value = CacheConstants.CACHE_NAME_BACKEND_CONFIG, key = "#key")
    @CacheExpire(expire = 5, type = TimeType.HOURS)
    public <T> T selectConfigByConfigKey(String key, Class<T> tClass) {
        Config config = new Config();
        config.setConfigKey(key);
        Config retConfig = configMapper.selectConfig(config);
        if (retConfig == null) {
            throw new CustomException("Can not get config by key  " + key);
        }
        return JSON.parseObject(retConfig.getConfigValue(), tClass);
    }
}

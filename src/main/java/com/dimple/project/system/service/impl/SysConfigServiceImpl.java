package com.dimple.project.system.service.impl;

import com.dimple.common.constant.UserConstants;
import com.dimple.common.utils.StringUtils;
import com.dimple.project.common.DimpleBlogContext;
import com.dimple.project.system.domain.SysConfig;
import com.dimple.project.system.mapper.SysConfigMapper;
import com.dimple.project.system.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: SysConfigServiceImpl
 * @description: 参数配置 服务层实现
 * @author: Dimple
 * @date: 10/22/19
 */
@Service
public class SysConfigServiceImpl implements SysConfigService {
    @Autowired
    private SysConfigMapper configMapper;

    @Override
    public SysConfig selectConfigById(Long configId) {
        SysConfig config = new SysConfig();
        config.setConfigId(configId);
        return configMapper.selectConfig(config);
    }

    @Override
    public Object selectConfigByKey(String configKey) {
        SysConfig config = new SysConfig();
        config.setConfigKey(configKey);
        SysConfig retConfig = configMapper.selectConfig(config);
        return StringUtils.isNotNull(retConfig) ? retConfig.getConfigValue() : "";
    }

    @Override
    public List<SysConfig> selectConfigList(SysConfig config) {
        return configMapper.selectConfigList(config);
    }

    @Override
    public int insertConfig(SysConfig config) {
        return configMapper.insertConfig(config);
    }

    @Override
    public int updateConfig(SysConfig config) {
        return configMapper.updateConfig(config);
    }

    @Override
    public int deleteConfigById(Long configId) {
        String loginUsername = DimpleBlogContext.getLoginUsername();
        return configMapper.deleteConfigById(configId, loginUsername);
    }

    @Override
    public String checkConfigKeyUnique(SysConfig config) {
        Long configId = StringUtils.isNull(config.getConfigId()) ? -1L : config.getConfigId();
        SysConfig info = configMapper.checkConfigKeyUnique(config.getConfigKey());
        if (StringUtils.isNotNull(info) && info.getConfigId().longValue() != configId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }
}

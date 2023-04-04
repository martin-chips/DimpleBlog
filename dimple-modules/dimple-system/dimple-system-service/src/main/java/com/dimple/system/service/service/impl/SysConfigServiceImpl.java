package com.dimple.system.service.service.impl;

import com.dimple.common.core.constant.UserConstants;
import com.dimple.common.core.exception.ServiceException;
import com.dimple.common.core.text.Convert;
import com.dimple.common.core.utils.StringUtils;
import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.common.redis.constants.CacheConstants;
import com.dimple.common.redis.service.RedisService;
import com.dimple.system.service.entity.SysConfig;
import com.dimple.system.service.mapper.SysConfigMapper;
import com.dimple.system.service.service.SysConfigService;
import com.dimple.system.service.service.bo.SysConfigBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;

/**
 * 参数配置 服务层实现
 *
 * @author Dimple
 */
@Service
public class SysConfigServiceImpl implements SysConfigService {
    @Autowired
    private SysConfigMapper configMapper;

    @Autowired
    private RedisService redisService;

    /**
     * 项目启动时，初始化参数到缓存
     */
    @PostConstruct
    public void init() {
        loadingConfigCache();
    }

    /**
     * 查询参数配置信息
     *
     * @param id 参数配置ID
     * @return 参数配置信息
     */
    @Override
    public SysConfigBO selectConfigById(Long id) {
        SysConfig config = new SysConfig();
        config.setId(id);
        return BeanMapper.convert(configMapper.selectConfig(config), SysConfigBO.class);
    }

    /**
     * 根据键名查询参数配置信息
     *
     * @param configKey 参数key
     * @return 参数键值
     */
    @Override
    public String selectConfigByKey(String configKey) {
        String configValue = Convert.toStr(redisService.getCacheObject(getCacheKey(configKey)));
        if (StringUtils.isNotEmpty(configValue)) {
            return configValue;
        }
        SysConfig config = new SysConfig();
        config.setConfigKey(configKey);
        SysConfig retConfig = configMapper.selectConfig(config);
        if (StringUtils.isNotNull(retConfig)) {
            redisService.setCacheObject(getCacheKey(configKey), retConfig.getConfigValue());
            return retConfig.getConfigValue();
        }
        return StringUtils.EMPTY;
    }

    /**
     * 查询参数配置列表
     *
     * @param config 参数配置信息
     * @return 参数配置集合
     */
    @Override
    public List<SysConfigBO> selectConfigList(SysConfigBO config) {
        return BeanMapper.convertList(configMapper.selectConfigList(BeanMapper.convert(config, SysConfig.class)), SysConfigBO.class);
    }

    /**
     * 新增参数配置
     *
     * @param config 参数配置信息
     * @return affected lines
     */
    @Override
    public int insertConfig(SysConfigBO config) {
        SysConfig sysConfig = BeanMapper.convert(config, SysConfig.class);
        int row = configMapper.insertConfig(sysConfig);
        if (row > 0) {
            redisService.setCacheObject(getCacheKey(config.getConfigKey()), config.getConfigValue());
        }
        return row;
    }

    /**
     * 修改参数配置
     *
     * @param config 参数配置信息
     * @return affected lines
     */
    @Override
    public int updateConfig(SysConfigBO config) {
        SysConfig temp = configMapper.selectConfigById(config.getId());
        if (!StringUtils.equals(temp.getConfigKey(), config.getConfigKey())) {
            redisService.deleteObject(getCacheKey(temp.getConfigKey()));
        }
        SysConfig sysConfig = BeanMapper.convert(config, SysConfig.class);
        int row = configMapper.updateConfig(sysConfig);
        if (row > 0) {
            redisService.setCacheObject(getCacheKey(config.getConfigKey()), config.getConfigValue());
        }
        return row;
    }

    /**
     * 批量删除参数信息
     *
     * @param ids 需要删除的参数ID
     */
    @Override
    public void deleteConfigByIds(Long[] ids) {
        for (Long id : ids) {
            SysConfigBO config = selectConfigById(id);
            if (StringUtils.equals(UserConstants.YES, config.getConfigType())) {
                throw new ServiceException(String.format("内置参数【%1$s】不能删除 ", config.getConfigKey()));
            }
            configMapper.deleteConfigById(id);
            redisService.deleteObject(getCacheKey(config.getConfigKey()));
        }
    }

    /**
     * 加载参数缓存数据
     */
    @Override
    public void loadingConfigCache() {
        List<SysConfig> configsList = configMapper.selectConfigList(new SysConfig());
        for (SysConfig config : configsList) {
            redisService.setCacheObject(getCacheKey(config.getConfigKey()), config.getConfigValue());
        }
    }

    /**
     * 清空参数缓存数据
     */
    @Override
    public void clearConfigCache() {
        Collection<String> keys = redisService.keys(CacheConstants.SYS_CONFIG_KEY_DEFINE.formatKey("*"));
        redisService.deleteObject(keys);
    }

    /**
     * 重置参数缓存数据
     */
    @Override
    public void resetConfigCache() {
        clearConfigCache();
        loadingConfigCache();
    }

    /**
     * 校验参数键名是否唯一
     *
     * @param config 参数配置信息
     * @return affected lines
     */
    @Override
    public String checkConfigKeyUnique(SysConfigBO config) {
        Long id = StringUtils.isNull(config.getId()) ? -1L : config.getId();
        SysConfig info = configMapper.checkConfigKeyUnique(config.getConfigKey());
        if (StringUtils.isNotNull(info) && info.getId().longValue() != id.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 设置cache key
     *
     * @param configKey 参数键
     * @return 缓存键key
     */
    private String getCacheKey(String configKey) {
        return CacheConstants.SYS_CONFIG_KEY_DEFINE.formatKey(configKey);
    }
}

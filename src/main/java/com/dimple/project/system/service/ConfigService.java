package com.dimple.project.system.service;

import com.dimple.project.system.domain.Config;

import java.util.List;

/**
 * @className: ConfigService
 * @description: 参数配置 服务层
 * @author: Dimple
 * @date: 10/22/19
 */
public interface ConfigService {
    /**
     * 查询参数配置信息
     *
     * @param configId 参数配置ID
     * @return 参数配置信息
     */
    Config selectConfigById(Long configId);

    /**
     * 根据键名查询参数配置信息
     *
     * @param configKey 参数键名
     * @return 参数键值
     */
    Config selectConfigByKey(String configKey);

    /**
     * 查询参数配置列表
     *
     * @param config 参数配置信息
     * @return 参数配置集合
     */
    List<Config> selectConfigList(Config config);

    /**
     * 新增参数配置
     *
     * @param config 参数配置信息
     * @return 结果
     */
    int insertConfig(Config config);

    /**
     * 修改参数配置
     *
     * @param config 参数配置信息
     * @return 结果
     */
    int updateConfig(Config config);

    /**
     * 根据ConfigKey 更新Config
     *
     * @param config config Key
     * @return 受影响的行数
     */
    int updateConfigByConfigKey(Config config);

    /**
     * 删除参数配置信息
     *
     * @param configId 需要删除的数据ID
     * @return 结果
     */
    int deleteConfigById(Long configId);

    /**
     * 校验参数键名是否唯一
     *
     * @param config 参数信息
     * @return 结果
     */
    String checkConfigKeyUnique(Config config);

    /**
     * get config and cast to class
     *
     * @param key    config key
     * @param tClass class
     * @param <T>    class type
     * @return T
     */
    <T> T selectConfigByConfigKey(String key, Class<T> tClass);
}

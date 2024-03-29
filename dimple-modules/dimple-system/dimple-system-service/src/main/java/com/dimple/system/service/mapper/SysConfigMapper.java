package com.dimple.system.service.mapper;


import com.dimple.system.service.entity.SysConfig;

import java.util.List;

/**
 * 参数配置 数据层
 *
 * @author Dimple
 */
public interface SysConfigMapper {
    /**
     * 查询参数配置信息
     *
     * @param config 参数配置信息
     * @return 参数配置信息
     */
    SysConfig selectConfig(SysConfig config);

    /**
     * 通过ID查询配置
     *
     * @param id 参数ID
     * @return 参数配置信息
     */
    SysConfig selectConfigById(Long id);

    /**
     * 查询参数配置列表
     *
     * @param config 参数配置信息
     * @return 参数配置集合
     */
    List<SysConfig> selectConfigList(SysConfig config);

    /**
     * 根据键名查询参数配置信息
     *
     * @param configKey 参数键名
     * @return 参数配置信息
     */
    SysConfig checkConfigKeyUnique(String configKey);

    /**
     * 新增参数配置
     *
     * @param config 参数配置信息
     * @return affected lines
     */
    int insertConfig(SysConfig config);

    /**
     * 修改参数配置
     *
     * @param config 参数配置信息
     * @return affected lines
     */
    int updateConfig(SysConfig config);

    /**
     * 删除参数配置
     *
     * @param id 参数ID
     * @return affected lines
     */
    int deleteConfigById(Long id);

    /**
     * 批量删除参数信息
     *
     * @param ids 需要删除的参数ID
     * @return affected lines
     */
    int deleteConfigByIds(Long[] ids);
}
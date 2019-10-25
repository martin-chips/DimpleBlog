package com.dimple.project.system.mapper;

import com.dimple.project.system.domain.SysConfig;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @className: SysConfigMapper
 * @description: 参数配置 数据层
 * @author: Dimple
 * @date: 10/22/19
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
     * @return 结果
     */
    int insertConfig(SysConfig config);

    /**
     * 修改参数配置
     *
     * @param config 参数配置信息
     * @return 结果
     */
    int updateConfig(SysConfig config);

    /**
     * 删除参数配置
     *
     * @param configId      需要删除的数据ID
     * @param loginUsername
     * @return 结果
     */
    int deleteConfigById(@Param("configId") Long configId, @Param("loginUsername") String loginUsername);
}

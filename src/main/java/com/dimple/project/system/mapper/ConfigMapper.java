package com.dimple.project.system.mapper;

import com.dimple.project.system.domain.Config;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @className: ConfigMapper
 * @description: 参数配置 数据层
 * @author: Dimple
 * @date: 10/22/19
 */
public interface ConfigMapper {
    /**
     * 查询参数配置信息
     *
     * @param config 参数配置信息
     * @return 参数配置信息
     */
    Config selectConfig(Config config);

    /**
     * 查询参数配置列表
     *
     * @param config 参数配置信息
     * @return 参数配置集合
     */
    List<Config> selectConfigList(Config config);

    /**
     * 根据键名查询参数配置信息
     *
     * @param configKey 参数键名
     * @return 参数配置信息
     */
    Config checkConfigKeyUnique(String configKey);

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
     * 根据Config Key 进行修改
     *
     * @return 受影响的行数
     */
    int updateConfigByConfigKey(Config config);

    /**
     * 删除参数配置
     *
     * @param id            需要删除的数据ID
     * @param loginUsername 操作者
     * @return 结果
     */
    int deleteConfigById(@Param("id") Long id, @Param("loginUsername") String loginUsername);
}

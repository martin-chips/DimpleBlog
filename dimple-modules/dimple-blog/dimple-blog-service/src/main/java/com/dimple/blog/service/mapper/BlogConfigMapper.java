package com.dimple.blog.service.mapper;

/**
 * BlogConfigMapper
 *
 * @author Dimple
 * @date 3/30/2023
 */
public interface BlogConfigMapper {
    int updateConfig(String configValue);

    String getConfig();

}

package com.dimple.repository;

import com.dimple.domain.GenConfig;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @className: GenConfigRepository
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
public interface GenConfigRepository extends JpaRepository<GenConfig,Long> {

    /**
     * 查询表配置
     * @param tableName 表名
     * @return /
     */
    GenConfig findByTableName(String tableName);
}

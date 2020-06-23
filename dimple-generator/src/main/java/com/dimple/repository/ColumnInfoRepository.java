package com.dimple.repository;

import com.dimple.domain.ColumnInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * @className: ColumnInfoRepository
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
public interface ColumnInfoRepository extends JpaRepository<ColumnInfo,Long> {

    /**
     * 查询表信息
     * @param tableName 表格名
     * @return 表信息
     */
    List<ColumnInfo> findByTableNameOrderByIdAsc(String tableName);
}

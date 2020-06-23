package com.dimple.modules.system.repository;

import com.dimple.modules.system.domain.DictDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


/**
 * @className: DictDetailRepository
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
public interface DictDetailRepository extends JpaRepository<DictDetail, Long>, JpaSpecificationExecutor<DictDetail> {

    /**
     * 根据字典名称查询
     */
    List<DictDetail> findByDictName(String name);
}

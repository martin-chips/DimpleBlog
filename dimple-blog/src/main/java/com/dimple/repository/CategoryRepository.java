package com.dimple.repository;

import com.dimple.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @className: CategoryRepository
 * @description:
 * @author: Dimple
 * @date: 06/18/20
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {
    void deleteAllByIdIn(Set<Long> ids);
}

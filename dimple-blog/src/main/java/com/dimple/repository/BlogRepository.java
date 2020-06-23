package com.dimple.repository;

import com.dimple.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @className: BlogRepository
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Repository
public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {

    void deleteAllByIdIn(Set<Long> ids);

    List<Blog> findAllByCategoryIdIn(Set<Long> ids);

}

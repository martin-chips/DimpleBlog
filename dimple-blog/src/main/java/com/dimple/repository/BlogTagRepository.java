package com.dimple.repository;

import com.dimple.domain.BlogTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @className: BlogTagRepository
 * @description:
 * @author: Dimple
 * @date: 06/19/20
 */
@Repository
public interface BlogTagRepository extends JpaRepository<BlogTag, Long>, JpaSpecificationExecutor<BlogTag> {

}

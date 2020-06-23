package com.dimple.repository;

import com.dimple.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @className: TagRepository
 * @description:
 * @author: Dimple
 * @date: 06/19/20
 */
@Repository
public interface TagRepository extends JpaRepository<Tag, Long>, JpaSpecificationExecutor<Tag> {

    void deleteAllByIdIn(Set<Long> ids);
}

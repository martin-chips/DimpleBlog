package com.dimple.repository;

import com.dimple.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Set;

/**
 * @className: CommentRepository
 * @description:
 * @author: Dimple
 * @date: 06/19/20
 */
public interface CommentRepository extends JpaRepository<Comment, Long>, JpaSpecificationExecutor<Comment> {
    void deleteAllByIdIn(Set<Long> ids);
}

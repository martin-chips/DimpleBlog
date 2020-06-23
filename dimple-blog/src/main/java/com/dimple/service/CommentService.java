package com.dimple.service;

import com.dimple.domain.Comment;
import com.dimple.service.Dto.CommentCriteria;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.Set;

/**
 * @className: CommentService
 * @description:
 * @author: Dimple
 * @date: 06/19/20
 */
public interface CommentService {
    Map<String, Object> queryAll(CommentCriteria criteria, Pageable pageable);

    Comment findById(Long id);

    void create(Comment comment);

    void update(Comment comment);

    void delete(Set<Long> ids);
}

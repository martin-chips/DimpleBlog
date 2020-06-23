package com.dimple.service;

import com.dimple.domain.Blog;
import com.dimple.service.Dto.BlogCriteria;
import com.dimple.service.Dto.BlogDTO;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.Set;

/**
 * @className: BlogService
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
public interface BlogService {

    Map<String, Object> queryAll(BlogCriteria criteria, Pageable pageable);

    BlogDTO findById(Long id);

    void create(Blog blog);

    void update(Blog blog);

    void delete(Set<Long> ids);
}

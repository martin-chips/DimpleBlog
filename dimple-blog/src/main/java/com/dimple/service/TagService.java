package com.dimple.service;

import com.dimple.domain.Tag;
import com.dimple.service.Dto.TagCriteria;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.Set;

/**
 * @className: TagService
 * @description:
 * @author: Dimple
 * @date: 06/19/20
 */
public interface TagService {

    Map<String, Object> queryAll(TagCriteria criteria, Pageable pageable);

    Tag findById(Long id);

    void create(Tag tag);

    void update(Tag tag);

    void delete(Set<Long> ids);
}

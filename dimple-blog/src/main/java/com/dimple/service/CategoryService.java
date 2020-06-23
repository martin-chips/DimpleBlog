package com.dimple.service;

import com.dimple.domain.Category;
import com.dimple.service.Dto.CategoryCriteria;
import com.dimple.service.Dto.CategoryDTO;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.Set;

/**
 * @className: CategoryService
 * @description:
 * @author: Dimple
 * @date: 06/18/20
 */
public interface CategoryService {
    Map<String, Object> queryAll(CategoryCriteria criteria, Pageable pageable);

    CategoryDTO findById(Long id);

    void create(Category category);

    void update(Category category);

    void delete(Set<Long> ids);
}

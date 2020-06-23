package com.dimple.service.impl;

import com.dimple.domain.Blog;
import com.dimple.domain.Category;
import com.dimple.mapstruct.CategoryMapper;
import com.dimple.repository.BlogRepository;
import com.dimple.repository.CategoryRepository;
import com.dimple.service.CategoryService;
import com.dimple.service.Dto.CategoryCriteria;
import com.dimple.service.Dto.CategoryDTO;
import com.dimple.exception.BadRequestException;
import com.dimple.utils.PageUtil;
import com.dimple.utils.QueryHelp;
import com.dimple.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @className: CategoryServiceImpl
 * @description:
 * @author: Dimple
 * @date: 06/18/20
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final BlogRepository blogRepository;

    @Override
    public Map<String, Object> queryAll(CategoryCriteria criteria, Pageable pageable) {
        Page<Category> repositoryPage = categoryRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(repositoryPage.map(categoryMapper::toDto));
    }

    @Override
    public CategoryDTO findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("category 不存在,Id 为" + id));
        return categoryMapper.toDto(category);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Category category) {
        categoryRepository.save(category);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Category category) {
        categoryRepository.save(category);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Set<Long> ids) {
        String blogTitleString = blogRepository.findAllByCategoryIdIn(ids).stream().map(Blog::getTitle).collect(Collectors.joining(","));
        if (StringUtils.isNotEmpty(blogTitleString)) {
            throw new BadRequestException("分类和以下博客存在关联，请解除关联再试！");
        }
        categoryRepository.deleteAllByIdIn(ids);
    }
}

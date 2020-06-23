package com.dimple.service.impl;

import com.dimple.domain.Blog;
import com.dimple.mapstruct.BlogMapper;
import com.dimple.mapstruct.BlogSmallMapper;
import com.dimple.repository.BlogRepository;
import com.dimple.service.BlogService;
import com.dimple.service.Dto.BlogCriteria;
import com.dimple.service.Dto.BlogDTO;
import com.dimple.exception.BadRequestException;
import com.dimple.utils.PageUtil;
import com.dimple.utils.QueryHelp;
import com.dimple.utils.RedisUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Set;

/**
 * @className: BlogServiceImpl
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "blog")
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final BlogSmallMapper blogSmallMapper;
    private final BlogMapper blogMapper;
    private final RedisUtils redisUtils;

    @Override
    public Map<String, Object> queryAll(BlogCriteria criteria, Pageable pageable) {
        Page<Blog> blogPage = blogRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(blogPage.map(blogSmallMapper::toDto));
    }

    @Override
    @Cacheable(key = "'id:' + #p0")
    public BlogDTO findById(Long id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("blog 不存在，id为" + id));
        return blogMapper.toDto(blog);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Blog blog) {
        blogRepository.save(blog);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Blog blog) {
        blogRepository.save(blog);
        redisUtils.del("blog::id:" + blog.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Set<Long> ids) {
        for (Long id : ids) {
            redisUtils.del("blog::id:" + id);
        }
        blogRepository.deleteAllByIdIn(ids);
    }


}

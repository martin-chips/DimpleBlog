package com.dimple.modules.endModule.blogManager.service;

import com.dimple.modules.endModule.blogManager.bean.BlogTag;
import com.dimple.modules.endModule.blogManager.repository.BlogTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @className: BlogTagServiceImpl
 * @description:
 * @auther: Owenb
 * @date: 01/28/19
 * @version: 1.0
 */
@Service
@Transactional
public class BlogTagServiceImpl implements BlogTagService {

    @Autowired
    BlogTagRepository blogTagRepository;

    @Override
    public BlogTag insertBlogTag(int blogId, int tagId) {
        BlogTag blogTag = new BlogTag();
        blogTag.setBlogId(blogId);
        blogTag.setTagId(tagId);
        return blogTagRepository.save(blogTag);
    }

    @Override
    public void deleteByBlogId(Integer blogId) {
        blogTagRepository.deleteByBlogId(blogId);
    }
}

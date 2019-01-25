package com.dimple.modules.frontModule.front.service;

import com.dimple.modules.endModule.blogManager.bean.Blog;
import com.dimple.modules.endModule.blogManager.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @className: TechnologyServiceImpl
 * @description:
 * @auther: Owenb
 * @date: 01/25/19
 * @version: 1.0
 */
@Service
@Transactional
public class TechnologyServiceImpl implements TechnologyService {

    @Autowired
    BlogRepository blogRepository;

    @Override
    public Object getNewestBlog(Pageable pageable) {

        Page<Blog> blogPage = blogRepository.findAll(pageable);
        List<Blog> content = blogPage.getContent();


        return null;
    }
}

package com.dimple.repository;

import com.dimple.modules.blogManager.bean.Blog;
import com.dimple.modules.blogManager.repository.BlogRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : BlogRepositoryTest
 * @description :
 * @date : 01/02/19 17:15
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BlogRepositoryTest {

    @Autowired
    BlogRepository blogRepository;

    /**
     * 测试获取下一条博客的信息
     */
    @Test
    public void testGetNextBlogById() {
        Blog nextBlog = blogRepository.getNextBlog(23);
        System.out.println(nextBlog);
    }

    /**
     * 测试获取上一条博客的信息
     */
    @Test
    public void testGetPreviousBlogById() {
        Blog nextBlog = blogRepository.getNextBlog(null);
        System.out.println(nextBlog);
    }
}

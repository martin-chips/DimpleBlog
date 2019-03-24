package com.dimple.test.mapper;

import com.dimple.project.blog.blog.domain.Blog;
import com.dimple.project.blog.blog.mapper.BlogMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @className: BlogMapperTest
 * @description: Blog的Mapper接口测试类
 * @auther: Dimple
 * @Date: 2019/3/24
 * @Version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogMapperTest {
    @Autowired
    BlogMapper blogMapper;

    @Test
    public void testInsertBlog() {
        Blog blog = new Blog();
        blog.setContent("this is a content");
        blog.setTitle("this is a title");
        blogMapper.insertBlog(blog);
    }


    @Test
    public void testUpdateBlogStatusByIds() {
        Integer ids[] = {31, 32, 33, 34};
        blogMapper.updateBlogStatusByIds(ids, "5");
    }

}

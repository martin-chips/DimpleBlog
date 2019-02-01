package com.dimple.repository;

import com.dimple.modules.BackStageModule.BlogManager.bean.Blog;
import com.dimple.modules.BackStageModule.BlogManager.repository.BlogRepository;
import com.dimple.modules.FrontDeskModule.domain.BlogDomain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

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

    /**
     * 测试是否能够获取到BlogDomain的数据
     */
    @Test
    public void testGetBlogDomain() {
        Page<BlogDomain> allBlogDomain = blogRepository.getAllBlogVo(PageRequest.of(0, 10));
        Assert.notNull(allBlogDomain, "失败");
    }

    /**
     * 获取归档下的博客的日期和数量
     */
    @Test
    public void testGetCountAndDate() {
        List<Map<String, Object>> countAndDate = blogRepository.getDateAndCount();
        Assert.notEmpty(countAndDate, "数据不能为空");
        for (Map<String, Object> dateIntegerMap : countAndDate) {
            String date = (String) dateIntegerMap.get("date");
            //如果不使用这种方式转换会出现java.math.BigInteger cannot be cast to java.lang.Integer
            Integer count = Integer.valueOf(dateIntegerMap.get("count").toString());
            System.out.println(date);
            System.out.println(count);

        }
    }
}

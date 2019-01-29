package com.dimple.repository;

import com.dimple.modules.endModule.blogManager.repository.TagRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @className: TagRepositoryTest
 * @description: 标签的测试
 * @auther: Owenb
 * @date: 01/29/19
 * @version: 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class TagRepositoryTest {

    @Autowired
    TagRepository tagRepository;

    @Test
    public void getTagTitleByBlogId() {
        String[] tagByBlogId = tagRepository.getTagByBlogId(69);
        for (String s : tagByBlogId) {
            log.info(s);
        }
    }
}

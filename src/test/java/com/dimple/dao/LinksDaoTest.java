package com.dimple.dao;

import com.dimple.bean.Links;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: LinksDaoTest
 * @Description:
 * @Auther: Owenb
 * @Date: 11/21/18 17:38
 * @Version: 1.0
 */
@RunWith(SpringRunner.class)
@MybatisTest
//用@MybatisTest 默认会使用虚拟的数据源替代配置的，如果想使用你配置的数据源操作真正的数据库则使用以下配置
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LinksDaoTest {

    Logger logger = LoggerFactory.getLogger(LinksDaoTest.class);

    @Autowired
    LinksMapper linksMapper;

    /**
     * 测试添加Links
     */
    @Test
    public void insertLinks() {
        Links links = new Links();
        links.setTitle("测试title");
        links.setCreateDate(new Date());
        links.setDescription("测试一个description ");
        links.setDisplay(true);
        links.setUrl("www.bianxiaofeng.com");
        for (int i = 0; i < 100; i++) {
            linksMapper.insert(links);
        }
    }

    /**
     * 测试查询所有的Links
     */
    @Test
    public void findAllLinks() {
        List<Links> links = linksMapper.selectByExample(null);
        logger.info(links.toString());
    }

    @Test
    public void deleteAllLinks() {
        linksMapper.deleteByExample(null);
    }
}

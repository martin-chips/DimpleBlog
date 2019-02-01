package com.dimple.repository;

import com.dimple.modules.BackStageModule.BlogManager.repository.BlogInfoRepository;
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
 * @date : 12/29/18 13:03
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BlogInfoRepositoryTest {

    @Autowired
    BlogInfoRepository blogInfoRepository;

    @Test
    public void blogInfoFindOneTest() {

    }

    @Test
    public void blogInfoGetOneTest() {

    }
}

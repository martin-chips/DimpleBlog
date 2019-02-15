package com.dimple.repository;

import com.dimple.modules.BackStageModule.BlogManager.repository.CategoryRepository;
import com.dimple.modules.FrontDeskModule.domain.CategoryDomain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @className: CategoryRepositoryTest
 * @description:
 * @auther: Owenb
 * @date: 01/21/19 20:29
 * @version: 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void testGetSupportCategory() {
        List<CategoryDomain> allBySupport = categoryRepository.getAllBySupportOrderByWeightDesc(true);
        Assert.notNull(allBySupport, "allBySupport is null");
    }
}

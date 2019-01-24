package com.dimple.repository;

import com.dimple.modules.frontModule.front.domain.LinkDomain;
import com.dimple.modules.endModule.linkManager.repository.LinkRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @ClassName: LinksRepositoryTest
 * @Description:
 * @Auther: Owenb
 * @Date: 11/21/18 17:38
 * @Version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LinksRepositoryTest {

    @Autowired
    LinkRepository linkRepository;

    @Test
    public void getAllLinks() {
        List<LinkDomain> allLinkHanded = linkRepository.getAllLinkHanded();
        Assert.notNull(allLinkHanded, "all link null");
    }


}

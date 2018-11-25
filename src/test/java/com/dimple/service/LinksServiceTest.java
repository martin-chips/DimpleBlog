package com.dimple.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @ClassName: LinksServiceTest
 * @Description:
 * @Auther: Owenb
 * @Date: 11/21/18 17:32
 * @Version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LinksServiceTest {
    @Autowired
    LinksService linksService;

    @Test
    public void testGetAllLinksMethod() {
        System.out.println(linksService.getAllLinksHandled("", new Date(), new Date(), true).toString());

    }

}

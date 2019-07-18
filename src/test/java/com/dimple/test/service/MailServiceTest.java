package com.dimple.test.service;

import com.dimple.DimpleBlogApplication;
import com.dimple.project.common.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @className: MailServiceTest
 * @description:
 * @auther: Dimple
 * @date: 07/18/19
 * @version: 1.0
 */
@SpringBootTest(classes = DimpleBlogApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class MailServiceTest {
    @Autowired
    MailService mailService;

    @Test
    public void testSendSimpleMail() {
        String to = "bianxiaofeng@sohu.com";
        String subject = "[Testing email]Please ignore it";
        String content = "Hello";
        mailService.sendSimpleMail(to, subject, content);
    }
}

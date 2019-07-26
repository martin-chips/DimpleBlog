package com.dimple.test.service;

import com.dimple.project.common.service.FileService;
import com.qiniu.common.QiniuException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @className: FileServiceTest
 * @description: 文件测试
 * @auther: Dimple
 * @date: 07/24/19
 * @version: 1.0
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class FileServiceTest {
    @Autowired
    FileService fileService;

    @Test
    public void testSyncQiNiuYunToLocalDB() throws QiniuException {
        fileService.syncQiNiuYunImage();
    }
}

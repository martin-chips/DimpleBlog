package com.dimple.repository;

import com.dimple.bean.Permission;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : PermissionRepositoryTest
 * @description :
 * @date : 12/29/18 12:56
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PermissionRepositoryTest {

    @Autowired
    PermissionRepository permissionRepository;

    /**
     * Jpa的连接测试
     */
    @Test
    public void jpaRepositoryTest() {
        Permission one = permissionRepository.getOne(1);
        Assert.notNull(one, "当前为null");
    }


}

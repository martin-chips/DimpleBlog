package com.dimple.repository;

import com.dimple.modules.BackStageModule.SystemManager.repository.UserRoleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.Set;

/**
 * @className: UserRoleRepositoryTest
 * @description:
 * @auther: Owenb
 * @date: 01/31/19
 * @version: 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRoleRepositoryTest {

    @Autowired
    UserRoleRepository userRoleRepository;

    @Test
    public void testGetRoleNameByUserId() {
        Set<String> roleNamesByUserId = userRoleRepository.getRoleNamesByUserId(1);
        Assert.notNull(roleNamesByUserId, "异常");
    }

}

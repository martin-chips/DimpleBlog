package com.dimple.repository;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName: LinksDaoTest
 * @Description:
 * @Auther: Owenb
 * @Date: 11/21/18 17:38
 * @Version: 1.0
 */
@RunWith(SpringRunner.class)
//用@MybatisTest 默认会使用虚拟的数据源替代配置的，如果想使用你配置的数据源操作真正的数据库则使用以下配置
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LinksDaoTest {


}

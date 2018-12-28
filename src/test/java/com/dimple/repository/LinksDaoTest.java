package com.dimple.repository;

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
import java.util.UUID;

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


}

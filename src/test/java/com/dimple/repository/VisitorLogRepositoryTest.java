package com.dimple.repository;

import com.dimple.utils.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * @className: VisitorLogRepositoryTest
 * @description:
 * @auther: Dimple
 * @date: 01/14/19 21:18
 * @version: 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class VisitorLogRepositoryTest {

    @Autowired
    VisitorLogRepository visitorLogRepository;

    @Test
    public void testSpiderCount() {
        List<Map<String, Integer>> spiderCount = visitorLogRepository.getSpiderCount();
        System.out.println(spiderCount);
    }

    @Test
    public void testVisitorCount() {
        String pastDate = DateUtil.getPastDate(1);
        Integer visitorCountByVisitTime = visitorLogRepository.getVisitorCountByVisitTime(pastDate);
        System.out.println(visitorCountByVisitTime);
    }
}

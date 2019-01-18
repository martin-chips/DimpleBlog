package com.dimple.repository;

import com.dimple.utils.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * @className: VisitorRepositoryTest
 * @description:
 * @auther: Dimple
 * @date: 01/14/19 21:18
 * @version: 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class VisitorRepositoryTest {

    @Autowired
    VisitorRepository visitorRepository;

    @Test
    public void testSpiderCount() {
        List<Map<String, Integer>> spiderCount = visitorRepository.getSpiderCount();
        System.out.println(spiderCount);
    }

    @Test
    public void testVisitorCount() {
        String pastDate = DateUtil.getPastDate(1);
        Integer visitorCountByVisitTime = visitorRepository.getVisitorCountByVisitTime(pastDate);
        System.out.println(visitorCountByVisitTime);
    }

    @Test
    public void testCountHistory() {
        long i = visitorRepository.count();
        System.out.println(i);
    }

    @Test
    public void testCountLocalMonth() {
        System.out.println(visitorRepository.getVisitorCountByVisitTime("2019-01-15"));
    }

    @Test
    public void testCountAll() {
        Pageable pageable = PageRequest.of(0, 10);

        Page<List<Map<String, String>>> allVisitorCounttest = visitorRepository.getAllVisitorCount(pageable);
        System.out.println(allVisitorCounttest
        );
    }
}

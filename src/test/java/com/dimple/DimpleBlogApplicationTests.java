package com.dimple;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

// @RunWith(SpringRunner.class)
// @SpringBootTest
public class DimpleBlogApplicationTests {

    @Test
    public void contextLoads() {
    }

    public static void main(String[] args) {
        String url = "http://localhost:8080/images/sssssss.jpa";
        int i = url.lastIndexOf("/images");
        System.out.println(url.substring(i));
    }
}

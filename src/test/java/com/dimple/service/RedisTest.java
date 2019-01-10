package com.dimple.service;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisStringCommands;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RedisTest {

    @Test
    public void test() {

        RedisClient redisClient = RedisClient.create("redis://47.112.14.207");


        // connection, 线程安全的长连接，连接丢失时会自动重连，直到调用close关闭连接。
        StatefulRedisConnection<String, String> connection = redisClient.connect();

        // sync, 默认超时时间为60s.
        RedisStringCommands<String, String> sync = connection.sync();
        sync.set("host", "note.abeffect.com");
        String value = sync.get("host");
        System.out.println(value);

        // close connection
        connection.close();

        // shutdown
        redisClient.shutdown();


    }
}

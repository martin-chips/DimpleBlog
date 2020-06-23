package com.dimple.modules.system.rest;

import com.dimple.annotation.Limit;
import com.dimple.annotation.rest.AnonymousGetMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * @className: LimitController
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@RestController
@RequestMapping("/api/limit")
@Api(tags = "系统：限流测试管理")
public class LimitController {

    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger();

    /**
     * 测试限流注解，下面配置说明该接口 60秒内最多只能访问 10次，保存到redis的键名为 limit_test，
     */
    @AnonymousGetMapping
    @ApiOperation("测试")
    @Limit(key = "test", period = 60, count = 10, name = "testLimit", prefix = "limit")
    public int test() {
        return ATOMIC_INTEGER.incrementAndGet();
    }
}

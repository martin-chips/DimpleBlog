package com.dimple.project.monitor.controller;

import com.dimple.framework.redis.RedisCacheService;
import com.dimple.framework.web.domain.AjaxResult;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @className: RedisController
 * @description:
 * @author: Dimple
 * @date: 11/6/19
 */
@RestController
@RequestMapping("/monitor/redis")
public class RedisController {
    final RedisCacheService redisCacheService;

    public RedisController(RedisCacheService redisCacheService) {
        this.redisCacheService = redisCacheService;
    }

    @PreAuthorize("@permissionService.hasPermission('monitor:redis:list')")
    @GetMapping("/list")
    public AjaxResult getRedisInfoList() {
        return AjaxResult.success(redisCacheService.getRedisInfoList());
    }

    @PreAuthorize("@permissionService.hasPermission('monitor:redis:add')")
    @PostMapping
    public AjaxResult add(@RequestParam String key, @RequestParam String value, @RequestParam Integer time) {
        redisCacheService.setCacheObject(key, value, time, TimeUnit.SECONDS);
        return AjaxResult.success();
    }

    @PreAuthorize("@permissionService.hasPermission('monitor:redis:edit')")
    @PutMapping
    public AjaxResult edit(@RequestParam String key, @RequestParam String value, @RequestParam Integer time) {
        redisCacheService.setCacheObject(key, value, time, TimeUnit.SECONDS);
        return AjaxResult.success();
    }

    /**
     * 获取实时Key的大小
     */
    @GetMapping("keySize")
    @PreAuthorize("@permissionService.hasPermission('monitor:redis:query')")
    public AjaxResult getKeySize() {
        RedisTemplate redisTemplate = redisCacheService.getRedisTemplate();
        Map<String, Object> map = new HashMap<>();
        Long size = redisTemplate.getConnectionFactory().getConnection().dbSize();
        map.put("keySize", size);
        map.put("time", new Date());
        return AjaxResult.success(map);
    }

    @GetMapping("memory")
    @PreAuthorize("@permissionService.hasPermission('monitor:redis:query')")
    public AjaxResult getMemory() {
        RedisTemplate redisTemplate = redisCacheService.getRedisTemplate();
        Map<String, Object> map = new HashMap<>();
        Properties memory = redisTemplate.getConnectionFactory().getConnection().info("memory");
        map.put("memory", memory.get("used_memory"));
        map.put("time", new Date());
        return AjaxResult.success(map);
    }

    @DeleteMapping()
    @PreAuthorize("@permissionService.hasPermission('monitor:redis:remove')")
    public AjaxResult removeAll() {
        redisCacheService.deleteObject("*");
        return AjaxResult.success();
    }

    @DeleteMapping("/{key}")
    @PreAuthorize("@permissionService.hasPermission('monitor:redis:remove')")
    public AjaxResult remove(@PathVariable String key) {
        redisCacheService.deleteObject(key);
        return AjaxResult.success();
    }
}

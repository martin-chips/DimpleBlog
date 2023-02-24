package com.dimple.monitor.web.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.common.core.web.controller.BaseController;
import com.dimple.common.core.web.vo.params.AjaxResult;
import com.dimple.common.redis.core.RedisKeyDefine;
import com.dimple.common.redis.core.RedisKeyRegistry;
import com.dimple.common.security.annotation.RequiresPermissions;
import com.dimple.monitor.web.controller.vo.redis.RedisCommandVO;
import com.dimple.monitor.web.controller.vo.redis.RedisKeyDefineVO;
import com.dimple.monitor.web.controller.vo.redis.RedisKeyValueVO;
import com.dimple.monitor.web.controller.vo.redis.RedisMonitorInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * SysRedisMonitorController
 *
 * @author Dimple
 * @date 2/22/2023 2:31 PM
 */
@RestController
@RequestMapping("/redis")
public class SysRedisMonitorController extends BaseController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/monitorInfo")
    @RequiresPermissions("monitor:redis:list")
    public AjaxResult getRedisMonitorInfo() {
        // 获得 Redis 统计信息
        Properties info = stringRedisTemplate.execute((RedisCallback<Properties>) RedisServerCommands::info);
        Long dbSize = stringRedisTemplate.execute(RedisServerCommands::dbSize);
        Properties commandStats = stringRedisTemplate.execute((RedisCallback<Properties>) connection -> connection.info("commandstats"));
        assert commandStats != null; // 断言，避免警告
        // 拼接结果返回
        RedisMonitorInfoVO redisMonitorInfoVO = new RedisMonitorInfoVO();
        redisMonitorInfoVO.setInfo(info);
        List<RedisCommandVO> redisCommandVOList = new ArrayList<>();
        commandStats.forEach((key, value) -> {
            redisCommandVOList.add(new RedisCommandVO(StrUtil.subAfter((String) key, "cmdstat_", false), Long.valueOf(StrUtil.subBetween((String) value, "calls=", ",")), Long.valueOf(StrUtil.subBetween((String) value, "usec=", ","))));
        });
        redisMonitorInfoVO.setCommandStats(redisCommandVOList);
        redisMonitorInfoVO.setDbSize(dbSize);
        return success(redisMonitorInfoVO);
    }

    @GetMapping("/keyDefines")
    @RequiresPermissions("monitor:redis:list")
    public AjaxResult getKeyDefineList() {
        List<RedisKeyDefine> keyDefines = RedisKeyRegistry.list();
        return success(BeanMapper.convertList(keyDefines, RedisKeyDefineVO.class));
    }


    @GetMapping("keys")
    @RequiresPermissions("monitor:redis:list")
    public AjaxResult getKeys(@RequestParam("keyTemplate") String keyTemplate) {
        return success(getRedisKeys(keyTemplate));
    }

    private Set<String> getRedisKeys(String keyTemplate) {
        // key 格式化
        String key = StrUtil.replace(keyTemplate, "%[s|c|b|d|x|o|f|a|e|g]", parameter -> "*");
        // scan 扫描 key
        Set<String> keys = new LinkedHashSet<>();
        stringRedisTemplate.execute((RedisCallback<Set<String>>) connection -> {
            try (Cursor<byte[]> cursor = connection.scan(ScanOptions.scanOptions().match(key).count(100).build())) {
                cursor.forEachRemaining(value -> keys.add(StrUtil.utf8Str(value)));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return keys;
        });
        return keys;
    }

    @GetMapping("/key/{key}")
    @RequiresPermissions("monitor:redis:list")
    public AjaxResult getKeyValue(@PathVariable("key") String key) {
        String value = stringRedisTemplate.opsForValue().get(key);
        return success(new RedisKeyValueVO(key, value));
    }

    @DeleteMapping("/key/{key}")
    @RequiresPermissions("monitor:redis:list")
    public AjaxResult deleteKey(@PathVariable("key") String key) {
        stringRedisTemplate.delete(key);
        return success(Boolean.TRUE);
    }

    @DeleteMapping("/key/template")
    @RequiresPermissions("monitor:redis:list")
    public AjaxResult deleteKeys(@RequestParam("keyTemplate") String keyTemplate) {
        Set<String> keys = getRedisKeys(keyTemplate);
        if (CollUtil.isNotEmpty(keys)) {
            stringRedisTemplate.delete(keys);
        }
        return success(Boolean.TRUE);
    }
}

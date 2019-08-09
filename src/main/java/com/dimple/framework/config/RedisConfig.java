package com.dimple.framework.config;

import com.dimple.common.constant.CacheConstant;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @className: RedisConfig
 * @description: Redis配置
 * @auther: Dimple
 * @date: 03/26/19
 * @version: 1.0
 */
@Configuration
@EnableCaching
@Slf4j
public class RedisConfig {

    private static Set<String> cacheNames = new HashSet<>();
    private static Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();

    public static final Duration BUSINESS_TIME_TO_LIVE = Duration.ofMinutes(30);
    public static final Duration SYSTEM_CONFIG_TIME_TO_LIVE = Duration.ofDays(10);

    // 缓存管理器 使用Lettuce
    @Bean
    public CacheManager cacheManager(LettuceConnectionFactory lettuceConnectionFactory) {
        initCacheConfigurations();
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.RedisCacheManagerBuilder
                .fromConnectionFactory(lettuceConnectionFactory)
                .initialCacheNames(cacheNames)
                .withInitialCacheConfigurations(cacheConfigurations)
                .transactionAware();
        return builder.build();
    }

    /**
     * 初始化CacheNames和CacheConfigurations
     */
    private void initCacheConfigurations() {
        //spring cache的注解使用的序列化都从这来，没有这个配置的话使用的jdk自己的序列化，实际上不影响使用，只是打印出来不适合人眼识别
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer()))//key序列化方式
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer()))//value序列化方式
                .disableCachingNullValues()//不缓存空值
                .entryTtl(BUSINESS_TIME_TO_LIVE);//缓存过期时间
        CacheConstant cacheConstant = new CacheConstant();
        try {
            //通过反射获取所有的静态常量
            for (Field declaredField : CacheConstant.class.getDeclaredFields()) {
                if (!(declaredField.getName().startsWith("BUSINESS")) && !(declaredField.getName().startsWith("SYSTEM_CONFIG"))) {
                    continue;
                }
                //只需要静态常量
                if (Modifier.isStatic(declaredField.getModifiers())) {
                    String name = declaredField.getName();
                    String value = (String) declaredField.get(cacheConstant);
                    log.info("get cacheName  {}", value);
                    cacheNames.add(value);
                    cacheConfigurations.put(value, config.entryTtl(name.startsWith("SYSTEM") ? SYSTEM_CONFIG_TIME_TO_LIVE : BUSINESS_TIME_TO_LIVE));
                }
            }
        } catch (IllegalAccessException e) {
            log.error(e.getMessage(), e);
        }
    }


    /**
     * RedisTemplate配置 在单独使用redisTemplate的时候 重新定义序列化方式
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        // 配置redisTemplate
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        RedisSerializer<?> stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);// key序列化
        redisTemplate.setValueSerializer(valueSerializer());// value序列化
        redisTemplate.setHashKeySerializer(stringSerializer);// Hash key序列化
        redisTemplate.setHashValueSerializer(valueSerializer());// Hash value序列化
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    private RedisSerializer<String> keySerializer() {
        return new StringRedisSerializer();
    }

    private RedisSerializer<Object> valueSerializer() {
        // 设置序列化
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        return jackson2JsonRedisSerializer;
    }
}

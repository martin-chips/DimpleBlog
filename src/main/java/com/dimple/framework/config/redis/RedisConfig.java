package com.dimple.framework.config.redis;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @className: RedisConfig
 * @description: redis配置
 * @author: Dimple
 * @date: 10/22/19
 */
@Slf4j
@Configuration
@EnableCaching
@ConditionalOnClass(RedisOperations.class)
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig extends CachingConfigurerSupport {

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        // 初始化一个RedisCacheWriter
        RedisCacheWriter cacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);

        //设置默认缓存过期时间
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(1))
                .serializeKeysWith(MyRedisCacheManager.STRING_PAIR)
                .serializeValuesWith(MyRedisCacheManager.FASTJSON_PAIR);
        return new MyRedisCacheManager(cacheWriter, defaultCacheConfig);
    }

    @Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        // set key serializer
        StringRedisSerializer serializer = MyRedisCacheManager.STRING_SERIALIZER;
        // 设置key序列化类，否则key前面会多了一些乱码
        template.setKeySerializer(serializer);
        template.setHashKeySerializer(serializer);

        // fastjson serializer
        GenericFastJsonRedisSerializer fastSerializer = MyRedisCacheManager.FASTJSON_SERIALIZER;
        template.setValueSerializer(fastSerializer);
        template.setHashValueSerializer(fastSerializer);
        // 如果 KeySerializer 或者 ValueSerializer 没有配置，则对应的 KeySerializer、ValueSerializer 才使用这个 Serializer
        template.setDefaultSerializer(fastSerializer);

        // factory
        template.setConnectionFactory(connectionFactory);
        template.afterPropertiesSet();
        return template;
    }

}

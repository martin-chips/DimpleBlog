package com.dimple.framework.config.redis;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.ReflectionUtils;

import java.time.Duration;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @className: MyRedisCacheManager
 * @description: 自定义CacheManager实现注解设置失效时间
 * @author: Dimple
 * @date: 2020/1/1
 */
@Slf4j
public class MyRedisCacheManager extends RedisCacheManager implements ApplicationContextAware, InitializingBean {

    private ApplicationContext applicationContext;

    private Map<String, RedisCacheConfiguration> initialCacheConfiguration = new LinkedHashMap<>();

    /**
     * key serializer
     */
    public static final StringRedisSerializer STRING_SERIALIZER = new StringRedisSerializer();

    /**
     * value serializer
     * <pre>
     *     使用 FastJsonRedisSerializer 会报错：java.lang.ClassCastException
     *     FastJsonRedisSerializer<Object> fastSerializer = new FastJsonRedisSerializer<>(Object.class);
     * </pre>
     */

    public static final GenericFastJsonRedisSerializer FASTJSON_SERIALIZER = new GenericFastJsonRedisSerializer();

    /**
     * key serializer pair
     */
    public static final RedisSerializationContext.SerializationPair<String> STRING_PAIR = RedisSerializationContext
            .SerializationPair.fromSerializer(STRING_SERIALIZER);
    /**
     * value serializer pair
     */
    public static final RedisSerializationContext.SerializationPair<Object> FASTJSON_PAIR = RedisSerializationContext
            .SerializationPair.fromSerializer(FASTJSON_SERIALIZER);

    public MyRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);
    }

    @Override
    public Cache getCache(String name) {
        Cache cache = super.getCache(name);
        return new RedisCacheWrapper(cache);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() {
        String[] beanNames = applicationContext.getBeanNamesForType(Object.class);
        for (String beanName : beanNames) {
            final Class clazz = applicationContext.getType(beanName);
            add(clazz);
        }
        super.afterPropertiesSet();
    }

    @Override
    protected Collection<RedisCache> loadCaches() {
        List<RedisCache> caches = new LinkedList<>();
        for (Map.Entry<String, RedisCacheConfiguration> entry : initialCacheConfiguration.entrySet()) {
            caches.add(super.createRedisCache(entry.getKey(), entry.getValue()));
        }
        return caches;
    }

    private void add(final Class clazz) {
        ReflectionUtils.doWithMethods(clazz, method -> {
            ReflectionUtils.makeAccessible(method);
            CacheExpire cacheExpire = AnnotationUtils.findAnnotation(method, CacheExpire.class);
            if (cacheExpire == null) {
                return;
            }
            Cacheable cacheable = AnnotationUtils.findAnnotation(method, Cacheable.class);
            if (cacheable != null) {
                add(cacheable.cacheNames(), cacheExpire);
                return;
            }
            Caching caching = AnnotationUtils.findAnnotation(method, Caching.class);
            if (caching != null) {
                Cacheable[] cs = caching.cacheable();
                if (cs.length > 0) {
                    for (Cacheable c : cs) {
                        if (cacheExpire != null && c != null) {
                            add(c.cacheNames(), cacheExpire);
                        }
                    }
                }
            } else {
                CacheConfig cacheConfig = AnnotationUtils.findAnnotation(clazz, CacheConfig.class);
                if (cacheConfig != null) {
                    add(cacheConfig.cacheNames(), cacheExpire);
                }
            }
        }, method -> null != AnnotationUtils.findAnnotation(method, CacheExpire.class));
    }

    private void add(String[] cacheNames, CacheExpire cacheExpire) {
        for (String cacheName : cacheNames) {
            if (cacheName == null || "".equals(cacheName.trim())) {
                continue;
            }
            long expire = cacheExpire.expire();
            Duration duration;
            switch (cacheExpire.type()) {
                case SECONDS:
                    duration = Duration.ofSeconds(expire);
                    break;
                case MINUTES:
                    duration = Duration.ofMinutes(expire);
                    break;
                case HOURS:
                    duration = Duration.ofHours(expire);
                    break;
                case DAY:
                    duration = Duration.ofDays(expire);
                    break;
                default:
                    duration = Duration.ofSeconds(expire);
                    break;
            }
            log.info("cacheName: {}, expire: {},duration: {}", cacheName, expire, duration);
            if (expire >= 0) {
                // 缓存配置
                RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(duration)
                        .disableCachingNullValues()
                        .serializeKeysWith(STRING_PAIR)
                        .serializeValuesWith(FASTJSON_PAIR);
                initialCacheConfiguration.put(cacheName, config);
            } else {
                log.warn("{} use default expiration.", cacheName);
            }
        }
    }

    protected static class RedisCacheWrapper implements Cache {
        private final Cache cache;

        RedisCacheWrapper(Cache cache) {
            this.cache = cache;
        }

        @Override
        public String getName() {
            log.info("name: {}", cache.getName());
            try {
                return cache.getName();
            } catch (Exception e) {
                log.error("getName ---> errMsg: {}", e.getMessage(), e);
                return null;
            }
        }

        @Override
        public Object getNativeCache() {
            log.info("nativeCache: {}", cache.getNativeCache());
            try {
                return cache.getNativeCache();
            } catch (Exception e) {
                log.error("getNativeCache ---> errMsg: {}", e.getMessage(), e);
                return null;
            }
        }

        @Override
        public ValueWrapper get(Object o) {
            log.info("get ---> o: {}", o);
            try {
                return cache.get(o);
            } catch (Exception e) {
                log.error("get ---> o: {}, errMsg: {}", o, e.getMessage(), e);
                return null;
            }
        }

        @Override
        public <T> T get(Object o, Class<T> aClass) {
            log.info("get ---> o: {}, clazz: {}", o, aClass);
            try {
                return cache.get(o, aClass);
            } catch (Exception e) {
                log.error("get ---> o: {}, clazz: {}, errMsg: {}", o, aClass, e.getMessage(), e);
                return null;
            }
        }


        @Override
        public <T> T get(Object o, Callable<T> callable) {
            log.info("get ---> o: {}", o);
            try {
                return cache.get(o, callable);
            } catch (Exception e) {
                log.error("get ---> o: {}, errMsg: {}", o, e.getMessage(), e);
                return null;
            }
        }

        @Override
        public void put(Object o, Object o1) {
            log.info("put ---> o: {}, o1: {}", o, o1);
            try {
                cache.put(o, o1);
            } catch (Exception e) {
                log.error("put ---> o: {}, o1: {}, errMsg: {}", o, o1, e.getMessage(), e);
            }
        }

        @Override
        public ValueWrapper putIfAbsent(Object o, Object o1) {
            log.info("putIfAbsent ---> o: {}, o1: {}", o, o1);
            try {
                return cache.putIfAbsent(o, o1);
            } catch (Exception e) {
                log.error("putIfAbsent ---> o: {}, o1: {}, errMsg: {}", o, o1, e.getMessage(), e);
                return null;
            }
        }

        @Override
        public void evict(Object o) {
            log.info("evict ---> o: {}", o);
            try {
                cache.evict(o);
            } catch (Exception e) {
                log.error("evict ---> o: {}, errMsg: {}", o, e.getMessage(), e);
            }
        }

        @Override
        public void clear() {
            log.info("clear");
            try {
                cache.clear();
            } catch (Exception e) {
                log.error("clear ---> errMsg: {}", e.getMessage(), e);
            }
        }
    }

}

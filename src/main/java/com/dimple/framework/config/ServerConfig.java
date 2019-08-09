package com.dimple.framework.config;

import com.dimple.common.constant.CacheConstant;
import com.dimple.common.utils.ServletUtils;
import com.dimple.common.utils.baidu.BaiduUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @className: ServerConfig
 * @description: 服务相关配置
 * @auther: Dimple
 * @Date: 2019/3/13
 * @Version: 1.1
 */
@Component
public class ServerConfig {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Value("${dimple.baidu.clientId}")
    private String clientId;
    @Value("${dimple.baidu.clientSecret}")
    private String clientSecret;

    /**
     * 获取完整的请求路径，包括：域名，端口，上下文访问路径
     *
     * @return 服务地址
     */
    public String getUrl() {
        HttpServletRequest request = ServletUtils.getRequest();
        return getDomain(request);
    }

    public static String getDomain(HttpServletRequest request) {
        StringBuffer url = request.getRequestURL();
        String contextPath = request.getServletContext().getContextPath();
        return url.delete(url.length() - request.getRequestURI().length(), url.length()).append(contextPath).toString();
    }

    /**
     * 获取百度AI的Token
     *
     * @return token
     */
    @Cacheable(value = CacheConstant.SYSTEM_CONFIG_CACHE_BAIDU_AI_ACCESS_TOKEN)
    public String getBaiduAIAccessToken() {
        String auth = BaiduUtils.getAuth(clientId, clientSecret);
        return auth;
    }

    /**
     * 删除AI Token
     */
    public void deleteBaiduAIAccessToken() {
        redisTemplate.delete(CacheConstant.SYSTEM_CONFIG_CACHE_BAIDU_AI_ACCESS_TOKEN);
    }
}

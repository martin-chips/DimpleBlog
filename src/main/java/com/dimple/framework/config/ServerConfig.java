package com.dimple.framework.config;

import com.dimple.common.constant.CachePrefix;
import com.dimple.common.utils.ServletUtils;
import com.dimple.common.utils.StringUtils;
import com.dimple.common.utils.baidu.BaiduUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

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
    public String getBaiduAIAccessToken() {
        String token = redisTemplate.opsForValue().get(CachePrefix.SYSTEM_BAIDU_ACCESS_TOKEN);
        if (StringUtils.isEmpty(token)) {
            String auth = BaiduUtils.getAuth(clientId, clientSecret);
            redisTemplate.opsForValue().set(CachePrefix.SYSTEM_BAIDU_ACCESS_TOKEN, auth, 10, TimeUnit.DAYS);
        }
        return token;
    }

    /**
     * 删除AI Token
     */
    public void deleteBaiduAIAccessToken() {
        redisTemplate.delete(CachePrefix.SYSTEM_BAIDU_ACCESS_TOKEN);
    }
}

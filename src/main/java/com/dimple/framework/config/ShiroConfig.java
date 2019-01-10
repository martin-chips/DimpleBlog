package com.dimple.framework.config;

import com.dimple.framework.filter.LogoutFilter;
import com.dimple.framework.listener.ShiroSessionListener;
import com.dimple.framework.realm.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.IRedisManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @ClassName: ShiroConfig
 * @Description: Shiro配置信息
 * @Auther: Owenb
 * @Date: 11/19/18 19:21
 * @Version: 1.0
 */
@Configuration
public class ShiroConfig {


    @Bean("shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/page/login.html");
        shiroFilterFactoryBean.setSuccessUrl("/page/index.html");
        shiroFilterFactoryBean.setUnauthorizedUrl("/page/403");
        //拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/front/**", "anon");

        //加入验证码不拦截
        filterChainDefinitionMap.put("/api/kaptcha", "anon");
        filterChainDefinitionMap.put("/html/**", "anon");
        //加入plugin下面的不拦截
        filterChainDefinitionMap.put("/plugin/**", "anon");
        //前端界面不拦截
        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/front/**", "anon");
        filterChainDefinitionMap.put("/about.html", "anon");
        filterChainDefinitionMap.put("/technology.html", "anon");
        //前台调用API不拦截
        filterChainDefinitionMap.put("/public/api/**", "anon");
        filterChainDefinitionMap.put("/view/**", "anon");
        //退出
        filterChainDefinitionMap.put("/logout", "logout");
        //授权验证不拦截
        filterChainDefinitionMap.put("/auth", "anon");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        Map<String, Filter> filters = new LinkedHashMap<>();

        // 注销成功，则跳转到指定页面
        filters.put("logout", logoutFilter());
        shiroFilterFactoryBean.setFilters(filters);

        return shiroFilterFactoryBean;
    }

    /**
     * 设置凭证管理器
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher getHashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    /**
     * 自定义的Realm
     *
     * @return
     */
    @Bean
    public UserRealm getUserRealm() {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(getHashedCredentialsMatcher());
        return userRealm;
    }

    /**
     * 安全管理器
     *
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(getUserRealm());
        securityManager.setRememberMeManager(rememberMeManager());
        // set Session manager
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    /**
     * 开启Shiro AOP注解支持
     *
     * @param securityManager
     * @return
     */

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public SimpleMappingExceptionResolver getSimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
        Properties properties = new Properties();
        //数据库异常
        properties.setProperty("DatabaseException", "databaseError");
        properties.setProperty("UnauthorizedException", "/user/403");
        simpleMappingExceptionResolver.setExceptionMappings(properties);
        simpleMappingExceptionResolver.setDefaultErrorView("error");    // No default
        simpleMappingExceptionResolver.setExceptionAttribute("exception");     // Default is "exception"
        //r.setWarnLogCategory("example.MvcLogger");     // No default
        return simpleMappingExceptionResolver;
    }

    /**
     * 配置RedisSessionDao
     *
     * @return
     */
    @Bean
    public RedisSessionDAO sessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        Collection<SessionListener> listeners = new ArrayList<>();
        listeners.add(new ShiroSessionListener());
        defaultWebSessionManager.setSessionListeners(listeners);
        defaultWebSessionManager.setSessionDAO(sessionDAO());
        return defaultWebSessionManager;
    }


    private IRedisManager redisManager() {
        return new RedisManager();
    }


    /**
     * 记住我
     *
     * @return
     */
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey(Base64.decode("fCq+/xW488hMTCD+cmJ3aQ=="));
        return cookieRememberMeManager;
    }


    /**
     * cookie 属性设置
     */
    public SimpleCookie rememberMeCookie() {
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        cookie.setDomain("");
        cookie.setPath("/");
        //设置只允许HTTP
        cookie.setHttpOnly(true);
        //设置30天的过期时间
        cookie.setMaxAge(30 * 24 * 60 * 60);
        return cookie;
    }

    public LogoutFilter logoutFilter() {
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setLoginUrl("/");
        return logoutFilter;
    }

}

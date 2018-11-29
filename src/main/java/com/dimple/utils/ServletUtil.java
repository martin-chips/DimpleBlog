package com.dimple.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @ClassName: ServletUtil
 * @Description: 客户端工具类
 * @Auther: Owenb
 * @Date: 11/29/18 11:05
 * @Version: 1.0
 */
public class ServletUtil {
    /**
     * 获取request对象
     *
     * @return request对象
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取response对象
     *
     * @return response对象
     */
    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    /**
     * 获取Http session对象
     *
     * @return session对象
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 获取ServletRequestAttributes
     *
     * @return
     */
    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }
}

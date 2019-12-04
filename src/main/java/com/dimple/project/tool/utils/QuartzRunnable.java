package com.dimple.project.tool.utils;

import com.dimple.common.utils.StringUtils;
import com.dimple.common.utils.spring.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * @className: QuartzRunnable
 * @description:
 * @author: Dimple
 * @date: 11/07/19
 */
@Slf4j
public class QuartzRunnable implements Callable {
    private Object target;
    private Method method;
    private String param;

    public QuartzRunnable(String beanName, String methodName, String methodParams) throws NoSuchMethodException {
        this.target = SpringUtils.getBean(beanName);
        this.param = methodParams;
        if (StringUtils.isNotEmpty(param)) {
            this.method = target.getClass().getDeclaredMethod(methodName, String.class);
        } else {
            this.method = target.getClass().getDeclaredMethod(methodName);
        }
    }

    @Override
    public Object call() throws Exception {
        ReflectionUtils.makeAccessible(method);
        Object result;
        if (StringUtils.isNotEmpty(param)) {
            result = method.invoke(target, param);
        } else {
            result = method.invoke(target);
        }
        return result;
    }
}

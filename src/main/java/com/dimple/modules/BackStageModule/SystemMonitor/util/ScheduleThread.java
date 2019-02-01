package com.dimple.modules.BackStageModule.SystemMonitor.util;

import com.dimple.utils.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * @className: ScheduleThread
 * @description: 执行定时任务
 * @auther: Owenb
 * @date: 01/29/19
 * @version: 1.0
 */
@Slf4j
public class ScheduleThread extends Thread {

    private Object target;
    private Method method;
    private String params;

    public ScheduleThread(String beanName, String methodName, String params) throws NoSuchMethodException, SecurityException {
        this.target = SpringUtil.getBean(beanName);
        //设置参数
        this.params = params;
        //根据是否有参数设置对应的方法
        if (StringUtils.isNotEmpty(params)) {
            this.method = target.getClass().getDeclaredMethod(methodName, String.class);
        } else {
            this.method = target.getClass().getDeclaredMethod(methodName);
        }
    }

    @Override
    public void run() {
        try {
            ReflectionUtils.makeAccessible(method);
            if (StringUtils.isNotEmpty(params)) {
                method.invoke(target, params);
            } else {
                method.invoke(target);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            Throwable cause = e.getCause();
            throw new RuntimeException(cause.getMessage());
        }
    }
}

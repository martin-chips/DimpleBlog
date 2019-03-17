package com.dimple.project.monitor.job.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.dimple.common.utils.StringUtils;
import com.dimple.common.utils.spring.SpringUtils;
import com.dimple.project.monitor.job.domain.Job;

/**
 * @className: JobInvokeUtil
 * @description: 任务执行工具
 * @auther: Dimple
 * @Date: 2019/3/13
 * @Version: 1.1
 */
public class JobInvokeUtil {
    /**
     * 执行方法
     *
     * @param sysJob 系统任务
     */
    public static void invokeMethod(Job job) throws Exception {
        Object bean = SpringUtils.getBean(job.getJobName());
        String methodName = job.getMethodName();
        String methodParams = job.getMethodParams();

        invokeSpringBean(bean, methodName, methodParams);
    }

    /**
     * 调用任务方法
     *
     * @param bean         目标对象
     * @param methodName   方法名称
     * @param methodParams 方法参数
     */
    private static void invokeSpringBean(Object bean, String methodName, String methodParams)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        if (StringUtils.isNotEmpty(methodParams)) {
            Method method = bean.getClass().getDeclaredMethod(methodName, String.class);
            method.invoke(bean, methodParams);
        } else {
            Method method = bean.getClass().getDeclaredMethod(methodName);
            method.invoke(bean);
        }
    }
}

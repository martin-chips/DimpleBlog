package com.dimple.common.log.aspect;

import com.alibaba.fastjson2.JSON;
import com.dimple.common.core.utils.ServletUtils;
import com.dimple.common.core.utils.StringUtils;
import com.dimple.common.log.filter.PropertyPreExcludeFilter;
import org.aspectj.lang.JoinPoint;
import org.springframework.http.HttpMethod;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Map;

/**
 * LogAspectUtils
 *
 * @author Dimple
 * @date 3/6/2023 7:22 PM
 */
public class LogAspectUtils {
    public static boolean isFilterObject(final Object o) {
        Class<?> clazz = o.getClass();
        if (clazz.isArray()) {
            return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
        } else if (Collection.class.isAssignableFrom(clazz)) {
            Collection collection = (Collection) o;
            for (Object value : collection) {
                return value instanceof MultipartFile;
            }
        } else if (Map.class.isAssignableFrom(clazz)) {
            Map map = (Map) o;
            for (Object value : map.entrySet()) {
                Map.Entry entry = (Map.Entry) value;
                return entry.getValue() instanceof MultipartFile;
            }
        }
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse || o instanceof BindingResult;
    }


    public static String getRequestParams(JoinPoint joinPoint, String requestMethod, String[] excludeProperties) throws Exception {
        if (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod)) {
            String params = argsArrayToString(joinPoint.getArgs(), excludeProperties);
            return StringUtils.substring(params, 0, 2000);
        } else {
            Map<?, ?> paramsMap = ServletUtils.getParamMap(ServletUtils.getRequest());
            return StringUtils.substring(JSON.toJSONString(paramsMap, excludePropertyPreFilter(excludeProperties)), 0, 2000);
        }
    }


    /**
     * 忽略敏感属性
     */
    public static PropertyPreExcludeFilter excludePropertyPreFilter(String[] excludeProperties) {
        return new PropertyPreExcludeFilter().addExcludes(excludeProperties);
    }


    /**
     * 参数拼装
     */
    private static String argsArrayToString(Object[] paramsArray, String[] excludeProperties) {
        String params = "";
        if (paramsArray != null && paramsArray.length > 0) {
            for (Object o : paramsArray) {
                if (StringUtils.isNotNull(o) && !isFilterObject(o)) {
                    try {
                        String jsonObj = JSON.toJSONString(o, excludePropertyPreFilter(excludeProperties));
                        params += jsonObj.toString() + " ";
                    } catch (Exception e) {
                    }
                }
            }
        }
        return params.trim();
    }


}

package com.dimple.common.enums;

import java.util.HashMap;
import java.util.Map;

import org.springframework.lang.Nullable;

/**
 * @className: HttpMethod
 * @description: 请求方式
 * @author: Dimple
 * @date: 10/22/19
 */
public enum HttpMethod {
    GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE;

    private static final Map<String, HttpMethod> mappings = new HashMap<>(16);

    static {
        for (HttpMethod httpMethod : values()) {
            mappings.put(httpMethod.name(), httpMethod);
        }
    }

    @Nullable
    public static HttpMethod resolve(@Nullable String method) {
        return (method != null ? mappings.get(method) : null);
    }

    public boolean matches(String method) {
        return (this == resolve(method));
    }
}

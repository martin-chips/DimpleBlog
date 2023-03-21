package com.dimple.system.api.factory;

import com.dimple.system.api.RemoteDictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * 文件服务降级处理
 *
 * @author Dimple
 */
@Component
@Slf4j
public class RemoteDictFallbackFactory implements FallbackFactory<RemoteDictService> {

    @Override
    public RemoteDictService create(Throwable cause) {
        log.error("字典服务调用失败:{}", cause.getMessage());
        return (type, source) -> new ArrayList<>();
    }
}

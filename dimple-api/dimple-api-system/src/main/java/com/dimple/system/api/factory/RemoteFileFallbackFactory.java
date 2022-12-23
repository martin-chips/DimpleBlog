package com.dimple.system.api.factory;

import com.dimple.common.core.utils.response.ResponseEntityUtils;
import com.dimple.system.api.RemoteFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 文件服务降级处理
 *
 * @author Dimple
 */
@Component
@Slf4j
public class RemoteFileFallbackFactory implements FallbackFactory<RemoteFileService> {

    @Override
    public RemoteFileService create(Throwable throwable) {
        log.error("文件服务调用失败:{}", throwable.getMessage());
        return file -> ResponseEntityUtils.fail("上传文件失败:" + throwable.getMessage());
    }
}

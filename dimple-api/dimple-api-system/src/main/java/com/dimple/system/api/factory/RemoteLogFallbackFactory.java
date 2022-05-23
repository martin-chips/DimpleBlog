package com.dimple.system.api.factory;

import com.dimple.system.api.RemoteLogService;
import com.dimple.system.api.domain.SysLogininfor;
import com.dimple.common.core.domain.R;
import com.dimple.system.api.domain.SysOperLog;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 日志服务降级处理
 *
 * @author Dimple
 */
@Component
@Slf4j
public class RemoteLogFallbackFactory implements FallbackFactory<RemoteLogService> {
    @Override
    public RemoteLogService create(Throwable throwable) {
        log.error("日志服务调用失败:{}", throwable.getMessage());
        return new RemoteLogService() {
            @Override
            public R<Boolean> saveLog(SysOperLog sysOperLog, String source) {
                return null;
            }

            @Override
            public R<Boolean> saveLogininfor(SysLogininfor sysLogininfor, String source) {
                return null;
            }
        };

    }
}

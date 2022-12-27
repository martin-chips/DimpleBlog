package com.dimple.system.api;

import com.dimple.common.core.constant.SecurityConstants;
import com.dimple.common.core.constant.ServiceNameConstants;
import com.dimple.common.core.domain.ResponseEntity;
import com.dimple.system.api.factory.RemoteLogFallbackFactory;
import com.dimple.system.api.model.SysLogininforBO;
import com.dimple.system.api.model.SysOperLogBO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * 日志服务
 *
 * @author Dimple
 */
@FeignClient(contextId = "remoteLogService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteLogFallbackFactory.class)
public interface RemoteLogService {
    /**
     * 保存系统日志
     *
     * @param sysOperLog 日志实体
     * @param source     请求来源
     * @return 结果
     */
    @PostMapping("/operlog")
    ResponseEntity<Boolean> saveLog(@RequestBody SysOperLogBO sysOperLog, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 保存访问记录
     *
     * @param sysLogininfor 访问实体
     * @param source        请求来源
     * @return 结果
     */
    @PostMapping("/logininfor")
    ResponseEntity<Boolean> saveLogininfor(@RequestBody SysLogininforBO sysLogininfor, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}

package com.dimple.system.api.factory;

import com.dimple.common.core.domain.ResponseEntity;
import com.dimple.common.core.utils.response.ResponseEntityUtils;
import com.dimple.system.api.RemoteUserService;
import com.dimple.system.api.domain.SysUser;
import com.dimple.system.api.model.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 用户服务降级处理
 *
 * @author Dimple
 */
@Component
@Slf4j
public class RemoteUserFallbackFactory implements FallbackFactory<RemoteUserService> {

    @Override
    public RemoteUserService create(Throwable throwable) {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteUserService() {
            @Override
            public ResponseEntity<LoginUser> getUserInfo(String username, String source) {
                return ResponseEntityUtils.fail("获取用户失败:" + throwable.getMessage());
            }

            @Override
            public ResponseEntity<Boolean> registerUserInfo(SysUser sysUser, String source) {
                return ResponseEntityUtils.fail("注册用户失败:" + throwable.getMessage());
            }
        };
    }
}

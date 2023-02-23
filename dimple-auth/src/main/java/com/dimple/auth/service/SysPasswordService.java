package com.dimple.auth.service;

import com.dimple.common.redis.constants.CacheConstants;
import com.dimple.common.core.constant.Constants;
import com.dimple.common.core.exception.ServiceException;
import com.dimple.common.redis.core.RedisKeyDefine;
import com.dimple.common.redis.service.RedisService;
import com.dimple.common.security.utils.SecurityUtils;
import com.dimple.system.api.model.SysUserBO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 登录密码方法
 *
 * @author Dimple
 */
@Component
@RequiredArgsConstructor
public class SysPasswordService {
    private final RedisService redisService;
    private final SysRecordLogService recordLogService;
    private int maxRetryCount = CacheConstants.PASSWORD_MAX_RETRY_COUNT;
    private RedisKeyDefine redisKeyDefine = CacheConstants.PWD_ERR_CNT_KEY_DEFINE;


    public void validate(SysUserBO user, String password) {
        String username = user.getUserName();
        Integer retryCount = redisService.getCacheObject(redisKeyDefine.formatKey(username));

        if (retryCount == null) {
            retryCount = 0;
        }

        if (retryCount >= maxRetryCount) {
            String errMsg = String.format("密码输入错误%s次，帐户锁定%s分钟", maxRetryCount, redisKeyDefine.getTimeout().toMinutes());
            recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, errMsg);
            throw new ServiceException(errMsg);
        }

        if (!matches(user, password)) {
            retryCount = retryCount + 1;
            recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, String.format("密码输入错误%s次", retryCount));
            redisService.setCacheObject(redisKeyDefine.formatKey(username), retryCount, redisKeyDefine.getTimeout());
            throw new ServiceException("用户不存在/密码错误");
        } else {
            clearLoginRecordCache(username);
        }
    }

    public boolean matches(SysUserBO user, String rawPassword) {
        return SecurityUtils.matchesPassword(rawPassword, user.getPassword());
    }

    public void clearLoginRecordCache(String loginName) {
        if (Boolean.TRUE.equals(redisService.hasKey(redisKeyDefine.formatKey(loginName)))) {
            redisService.deleteObject(redisKeyDefine.formatKey(loginName));
        }
    }
}

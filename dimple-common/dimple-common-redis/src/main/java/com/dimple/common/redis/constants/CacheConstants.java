package com.dimple.common.redis.constants;

import com.dimple.common.redis.core.RedisKeyDefine;
import com.dimple.common.redis.core.RedisKeyTypeEnum;
import com.dimple.common.redis.core.RedisTimeoutTypeEnum;

import java.time.Duration;

/**
 * 缓存常量信息
 *
 * @author Dimple
 */
public class CacheConstants {
    private CacheConstants() {

    }

    /**
     * 缓存刷新时间，默认120（分钟）
     */
    public static final long REFRESH_TIME = 120;

    /**
     * 密码最大错误次数
     */
    public static final int PASSWORD_MAX_RETRY_COUNT = 5;

    /**
     * 缓存有效期，默认720（分钟）
     */
    private static final long EXPIRATION = 720;
    /**
     * 权限缓存前缀
     */
    public static final RedisKeyDefine LOGIN_TOKEN_KEY_DEFINE
            = new RedisKeyDefine("权限缓存", "login_tokens:%s", RedisKeyTypeEnum.STRING, String.class, Duration.ofMinutes(EXPIRATION));

    /**
     * 验证码 redis key
     */
    public static final RedisKeyDefine CAPTCHA_CODE_KEY_DEFINE
            = new RedisKeyDefine("验证码 redis key", "captcha_codes:%s", RedisKeyTypeEnum.STRING, String.class, Duration.ofMinutes(2));

    /**
     * 参数管理 cache key
     */
    public static final RedisKeyDefine SYS_CONFIG_KEY_DEFINE
            = new RedisKeyDefine("参数管理 cache key", "sys_config:%s", RedisKeyTypeEnum.STRING, String.class, RedisTimeoutTypeEnum.FOREVER);

    /**
     * 字典管理 cache key
     */
    public static final RedisKeyDefine SYS_DICT_KEY_DEFINE
            = new RedisKeyDefine("字典管理 cache key", "sys_dict:%s", RedisKeyTypeEnum.STRING, String.class, RedisTimeoutTypeEnum.FOREVER);

    /**
     * 密码锁定时间，默认10（分钟）
     */
    private static final long PASSWORD_LOCK_TIME = 10;
    public static final RedisKeyDefine PWD_ERR_CNT_KEY_DEFINE
            = new RedisKeyDefine("登录账户密码错误次数 redis key", "pwd_err_cnt:%s", RedisKeyTypeEnum.STRING, String.class, Duration.ofMinutes(PASSWORD_LOCK_TIME));

}

package com.dimple.common.constant;

/**
 * @className: Constants
 * @description: the constants
 * @author: Dimple
 * @date: 10/22/19
 */
public class Constants {
    private Constants() {
    }

    /**
     * login success
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * logout
     */
    public static final String LOGOUT = "Logout";

    /**
     * login failed
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * captcha redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * logine user redis key
     */
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";

    /**
     * expire time of captcha (minute)
     */
    public static final Integer CAPTCHA_EXPIRATION = 2;

    /**
     * token
     */
    public static final String TOKEN = "token";

    /**
     * the prefix of token
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * the prefix of token
     */
    public static final String LOGIN_USER_KEY = "login_user_key";

    /**
     * common constant for success
     */
    public static final Boolean SUCCESS = true;
    /**
     * common constant for failed
     */
    public static final Boolean FAILED = false;
}

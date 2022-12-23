package com.dimple.common.core.utils.response;

import com.dimple.common.core.constant.Constants;
import com.dimple.common.core.domain.ResponseEntity;

import java.io.Serializable;

/**
 * 响应信息主体
 *
 * @author Dimple
 */
public class ResponseEntityUtils<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public static <T> ResponseEntity<T> ok() {
        return restResult(null, Constants.SUCCESS, null);
    }

    public static <T> ResponseEntity<T> ok(T data) {
        return restResult(data, Constants.SUCCESS, null);
    }

    public static <T> ResponseEntity<T> ok(T data, String msg) {
        return restResult(data, Constants.SUCCESS, msg);
    }

    public static <T> ResponseEntity<T> fail() {
        return restResult(null, Constants.FAIL, null);
    }

    public static <T> ResponseEntity<T> fail(String msg) {
        return restResult(null, Constants.FAIL, msg);
    }

    public static <T> ResponseEntity<T> fail(T data) {
        return restResult(data, Constants.FAIL, null);
    }

    public static <T> ResponseEntity<T> fail(T data, String msg) {
        return restResult(data, Constants.FAIL, msg);
    }

    public static <T> ResponseEntity<T> fail(int code, String msg) {
        return restResult(null, code, msg);
    }

    private static <T> ResponseEntity<T> restResult(T data, int code, String msg) {
        ResponseEntity<T> apiResult = new ResponseEntity<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    public static <T> Boolean isError(ResponseEntity<T> ret) {
        return !isSuccess(ret);
    }

    public static <T> Boolean isSuccess(ResponseEntity<T> ret) {
        return Constants.SUCCESS == ret.getCode();
    }
}

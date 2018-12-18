package com.dimple.framework.message;

/**
 * @ClassName: Result
 * @Description:
 * @Auther: Owenb
 * @Date: 11/19/18 17:24
 * @Version: 1.0
 */
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    public Result() {
        super();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}

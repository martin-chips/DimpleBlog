package com.dimple.exception;

import com.dimple.utils.MessageUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import sun.misc.MessageUtils;

/**
 * @ClassName: BaseException
 * @Description: 基本的异常类
 * @Auther: Owenb
 * @Date: 12/06/18 14:05
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseException extends RuntimeException {

    /**
     * 出异常的模块名称
     */
    private String module;
    /**
     * 错误码
     */
    private String code;
    /**
     * 错误码对应的参数
     */
    private Object[] args;
    /**
     * 错误消息
     */
    private String defaultMessage;

    @Override
    public String getMessage() {
        String message = "";
        if (StringUtils.isNotBlank(code)) {
            message = MessageUtil.getMessage(code, args);
        }
        if (message == null) {
            message = defaultMessage;
        }
        return message;
    }
}

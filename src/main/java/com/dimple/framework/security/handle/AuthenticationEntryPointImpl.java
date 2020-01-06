package com.dimple.framework.security.handle;

import com.alibaba.fastjson.JSON;
import com.dimple.common.utils.ServletUtils;
import com.dimple.common.utils.StringUtils;
import com.dimple.common.utils.ip.IpUtils;
import com.dimple.framework.web.domain.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * @className: AuthenticationEntryPointImpl
 * @description: Authentication failure processing class returned unauthorized
 * @author: Dimple
 * @date: 10/22/19
 */
@Component
@Slf4j
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        log.error("Unauthorized access: \ncurrent IP:{}\nrequest URI :{}\n", IpUtils.getIpAddr(request), request.getRequestURI());
        String msg = StringUtils.format("Unauthorized access：{}，Authentication failed, unable to access system resources ", request.getRequestURI());
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error(HttpStatus.UNAUTHORIZED, msg)));
    }
}

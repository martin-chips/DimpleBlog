package com.dimple.framework.security.handle;

import com.alibaba.fastjson.JSON;
import com.dimple.common.constant.HttpStatus;
import com.dimple.common.utils.ServletUtils;
import com.dimple.common.utils.StringUtils;
import com.dimple.common.utils.ip.IpUtils;
import com.dimple.framework.web.domain.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @className: AuthenticationEntryPointImpl
 * @description: 认证失败处理类 返回未授权
 * @author: Dimple
 * @date: 10/22/19
 */
@Component
@Slf4j
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {
    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException {
        int code = HttpStatus.UNAUTHORIZED;
        log.error("未经授权访问: \n当前用户IP:{}\n访问地址:{}\n", IpUtils.getIpAddr(request), request.getRequestURI());
        String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", request.getRequestURI());
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error(code, msg)));
    }
}

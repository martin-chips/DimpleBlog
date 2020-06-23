package com.dimple.modules.security.security;

import com.dimple.utils.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @className: JwtAccessDeniedHandler
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Component
@Slf4j
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        log.error("forbidden access: \ncurrent IP:{}\nrequest URI :{}\n", IpUtil.getIp(request), request.getRequestURI());
        //当用户在没有授权的情况下访问受保护的REST资源时，将调用此方法发送403 Forbidden响应
        response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
    }
}

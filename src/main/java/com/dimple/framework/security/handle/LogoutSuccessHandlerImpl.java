package com.dimple.framework.security.handle;

import com.alibaba.fastjson.JSON;
import com.dimple.common.constant.Constants;
import com.dimple.common.constant.HttpStatus;
import com.dimple.common.utils.ServletUtils;
import com.dimple.common.utils.StringUtils;
import com.dimple.framework.manager.AsyncManager;
import com.dimple.framework.manager.factory.AsyncFactory;
import com.dimple.framework.security.LoginUser;
import com.dimple.framework.security.service.TokenService;
import com.dimple.framework.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @className: LogoutSuccessHandlerImpl
 * @description: 自定义退出处理类 返回成功
 * @author: Dimple
 * @date: 10/22/19
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    @Autowired
    private TokenService tokenService;

    /**
     * 退出处理
     *
     * @return
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser)) {
            String userName = loginUser.getUsername();
            // 删除用户缓存记录
            tokenService.delLoginUser(loginUser.getToken());
            // 记录用户退出日志
            AsyncManager.me().execute(AsyncFactory.recordLoginLog(userName, Constants.LOGOUT, "退出成功"));
        }
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error(HttpStatus.SUCCESS, "退出成功")));
    }
}

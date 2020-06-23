package com.dimple.service.impl;

import com.dimple.domain.LoginLog;
import com.dimple.repository.LoginLogRepository;
import com.dimple.service.LoginLogService;
import com.dimple.utils.IpUtil;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @className: LoginLogServiceImpl
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class LoginLogServiceImpl implements LoginLogService {

    private final LoginLogRepository loginLogRepository;

    @Override
    public void save(boolean success, String authUser, String uuid, HttpServletRequest request, String msg) {
        LoginLog loginLog = new LoginLog();
        loginLog.setStatus(success);
        loginLog.setUserName(authUser + "-" + uuid);
        String ip = IpUtil.getIp(request);
        loginLog.setRequestIp(ip);
        loginLog.setAddress(IpUtil.getHttpCityInfo(ip));
        String header = request.getHeader("User-Agent");

        final UserAgent userAgent = UserAgent.parseUserAgentString(header);
        loginLog.setOs(userAgent.getOperatingSystem().getName());
        loginLog.setBrowser(userAgent.getBrowser().getName());

        loginLogRepository.save(loginLog);
    }
}

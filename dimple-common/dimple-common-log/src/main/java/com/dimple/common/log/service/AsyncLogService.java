package com.dimple.common.log.service;

import com.dimple.common.core.constant.SecurityConstants;
import com.dimple.common.log.utils.SpiderUtils;
import com.dimple.system.api.RemoteLogService;
import com.dimple.system.api.model.BlogVisitLogBO;
import com.dimple.system.api.model.SysOperLogBO;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 异步调用日志服务
 *
 * @author Dimple
 */
@Service
@RequiredArgsConstructor
public class AsyncLogService {
    private final RemoteLogService remoteLogService;

    /**
     * 保存系统日志记录
     */
    @Async
    public void saveSysLog(SysOperLogBO sysOperLog) {
        remoteLogService.saveLog(sysOperLog, SecurityConstants.INNER);
    }

    @Async
    public void saveVisitLog(BlogVisitLogBO blogVisitLogBO) {
        // set ip ,spider
        UserAgent userAgent = UserAgent.parseUserAgentString(blogVisitLogBO.getUserAgent());
        String spider = SpiderUtils.parseUserAgent(userAgent.toString());
        blogVisitLogBO.setSpider(spider);
        blogVisitLogBO.setBrowser(userAgent.getBrowser().getName());
        blogVisitLogBO.setOs(userAgent.getOperatingSystem().getName());
        remoteLogService.saveVisitLog(blogVisitLogBO, SecurityConstants.INNER);
    }
}

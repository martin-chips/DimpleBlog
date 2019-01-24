package com.dimple.framework.filter;

import com.dimple.modules.endModule.systemManager.bean.User;
import com.dimple.framework.constant.Status;
import com.dimple.framework.log.AsyncHelper;
import com.dimple.framework.log.AsyncManager;
import com.dimple.utils.MessageUtil;
import com.dimple.utils.ShiroUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @ClassName: LogoutFilter
 * @Description:
 * @Auther: Owenb
 * @Date: 12/07/18 11:19
 * @Version: 1.0
 */
@Data
@Slf4j
public class LogoutFilter extends org.apache.shiro.web.filter.authc.LogoutFilter {

    private String loginUrl;

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        try {
            //获取当前的对象
            Subject subject = getSubject(request, response);
            User user = ShiroUtil.getUser();
            //获取重定向的URL
            String redirectUrl = getRedirectUrl();
            try {
                if (user != null) {
                    //获取登录的id
                    String loginId = user.getUserLoginId();
                    AsyncManager.getAsyncManager().execute(AsyncHelper.recordLoginLog(loginId, Status.SUCCESS, MessageUtil.getMessage("user.logout.success")));
//                    asyncLog.recordLoginLog(loginId, Status.LOGOUT_SUCCESS, MessageUtil.getMessage("user.logout.success"));
                }
                subject.logout();
            } catch (SessionException e) {
                log.error("logout failure", e);
            }
            issueRedirect(request, response, redirectUrl);
        } catch (Exception e) {
            log.error("Encountered session exception during logout.  This can generally safely be ignored.", e);
        }
        return false;
    }

    /**
     * 退出跳转URl
     *
     * @param request
     * @param response
     * @param subject
     * @return
     */
    @Override
    protected String getRedirectUrl(ServletRequest request, ServletResponse response, Subject subject) {
        String url = getLoginUrl();
        if (StringUtils.isNotBlank(url)) {
            return url;
        }
        return super.getRedirectUrl(request, response, subject);
    }
}

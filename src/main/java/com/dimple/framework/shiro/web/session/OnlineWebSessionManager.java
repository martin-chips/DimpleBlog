package com.dimple.framework.shiro.web.session;

import com.dimple.common.constant.ShiroConstants;
import com.dimple.common.utils.spring.SpringUtils;
import com.dimple.project.monitor.online.domain.OnlineSession;
import com.dimple.project.monitor.online.domain.UserOnline;
import com.dimple.project.monitor.online.service.UserOnlineServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.session.ExpiredSessionException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @className: OnlineWebSessionManager
 * @description: 主要是在此如果会话的属性修改了 就标识下其修改了 然后方便 OnlineSessionDao同步
 * @auther: Dimple
 * @Date: 2019/3/13
 * @Version: 1.1
 */
@Slf4j
public class OnlineWebSessionManager extends DefaultWebSessionManager {
    @Override
    public void setSessionListeners(Collection<SessionListener> listeners) {
        super.setSessionListeners(listeners);
    }

    @Override
    public void setAttribute(SessionKey sessionKey, Object attributeKey, Object value) throws InvalidSessionException {
        super.setAttribute(sessionKey, attributeKey, value);
        if (value != null && needMarkAttributeChanged(attributeKey)) {
            OnlineSession s = (OnlineSession) doGetSession(sessionKey);
            s.markAttributeChanged();
        }
    }

    private boolean needMarkAttributeChanged(Object attributeKey) {
        if (attributeKey == null) {
            return false;
        }
        String attributeKeyStr = attributeKey.toString();
        // 优化 flash属性没必要持久化
        if (attributeKeyStr.startsWith("org.springframework")) {
            return false;
        }
        if (attributeKeyStr.startsWith("javax.servlet")) {
            return false;
        }
        if (attributeKeyStr.equals(ShiroConstants.CURRENT_USERNAME)) {
            return false;
        }
        return true;
    }

    @Override
    public Object removeAttribute(SessionKey sessionKey, Object attributeKey) throws InvalidSessionException {
        Object removed = super.removeAttribute(sessionKey, attributeKey);
        if (removed != null) {
            OnlineSession s = (OnlineSession) doGetSession(sessionKey);
            s.markAttributeChanged();
        }

        return removed;
    }

    /**
     * 验证session是否有效 用于删除过期session
     */
    @Override
    public void validateSessions() {
        if (log.isInfoEnabled()) {
            log.info("invalidation sessions...");
        }

        int invalidCount = 0;

        int timeout = (int) this.getGlobalSessionTimeout();
        Date expiredDate = DateUtils.addMilliseconds(new Date(), 0 - timeout);
        UserOnlineServiceImpl userOnlineService = SpringUtils.getBean(UserOnlineServiceImpl.class);
        List<UserOnline> userOnlineList = userOnlineService.selectOnlineByExpired(expiredDate);
        // 批量过期删除
        List<String> needOfflineIdList = new ArrayList<String>();
        for (UserOnline userOnline : userOnlineList) {
            try {
                SessionKey key = new DefaultSessionKey(userOnline.getSessionId());
                Session session = retrieveSession(key);
                if (session != null) {
                    throw new InvalidSessionException();
                }
            } catch (InvalidSessionException e) {
                if (log.isDebugEnabled()) {
                    boolean expired = (e instanceof ExpiredSessionException);
                    String msg = "Invalidated session with id [" + userOnline.getSessionId() + "]"
                            + (expired ? " (expired)" : " (stopped)");
                    log.debug(msg);
                }
                invalidCount++;
                needOfflineIdList.add(userOnline.getSessionId());
            }

        }
        if (needOfflineIdList.size() > 0) {
            try {
                userOnlineService.batchDeleteOnline(needOfflineIdList);
            } catch (Exception e) {
                log.error("batch delete db session error.", e);
            }
        }

        if (log.isInfoEnabled()) {
            String msg = "Finished invalidation session.";
            if (invalidCount > 0) {
                msg += " [" + invalidCount + "] sessions were stopped.";
            } else {
                msg += " No sessions were stopped.";
            }
            log.info(msg);
        }

    }

    /**
     * 获取在线用户信息，包括访客信息和后台登录用户的信息
     */
    @Override
    protected Collection<Session> getActiveSessions() {
        return sessionDAO.getActiveSessions();
    }
}

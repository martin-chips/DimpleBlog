package com.dimple.service.impl;

import com.dimple.bean.User;
import com.dimple.bean.UserOnline;
import com.dimple.service.SessionService;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : SessionServiceImpl
 * @description :
 * @date : 01/09/19 20:54
 */
@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    SessionDAO sessionDAO;


    @Override
    public List<UserOnline> getList() {
        List<UserOnline> list = new ArrayList<>();
        //我们可以获取所有有效的Session，通过该Session我们还可以获取到当前用户的Principal信息
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        for (Session session : sessions) {
            UserOnline userOnline = new UserOnline();
            User user;
            SimplePrincipalCollection principalCollection;

            if (session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) == null) {
                continue;
            } else {
                principalCollection = (SimplePrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                user = (User) principalCollection.getPrimaryPrincipal();
                userOnline.setUsername(user.getUserName());
                userOnline.setUserId(user.getUserLoginId());
            }

            userOnline.setLastAccessTime(session.getLastAccessTime());
            userOnline.setStartTime(session.getStartTimestamp());
            Long timeout = session.getTimeout();
            if (timeout == 01) {
                userOnline.setStatus("离线");
            } else {
                userOnline.setStatus("在线");
            }
            userOnline.setTimeout(timeout);
            list.add(userOnline);
        }
        return list;
    }

    @Override
    public boolean forceLogout(String sessionId) {
        Session session = sessionDAO.readSession(sessionId);
        sessionDAO.delete(session);
        return true;
    }
}

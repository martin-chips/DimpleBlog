package com.dimple.framework.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @className: ShiroSessionListener
 * @description: Shiro Listener
 * @auther: Dimple
 * @date: 04/03/19
 * @version: 1.0
 */
public class ShiroSessionListener implements SessionListener {

    private final AtomicInteger sessionCount = new AtomicInteger(0);

    @Override
    public void onStart(Session session) {
        sessionCount.incrementAndGet();
    }

    @Override
    public void onStop(Session session) {
        sessionCount.decrementAndGet();
    }

    @Override
    public void onExpiration(Session session) {
        sessionCount.decrementAndGet();
    }

    public AtomicInteger getSessionCount() {
        return sessionCount;
    }
}

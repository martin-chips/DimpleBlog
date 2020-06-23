package com.dimple.service;

import org.springframework.scheduling.annotation.Async;

import javax.servlet.http.HttpServletRequest;

/**
 * @className: LoginLogService
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
public interface LoginLogService {

    @Async
    void save(boolean success, String authUser, String uuid, HttpServletRequest request, String msg);
}

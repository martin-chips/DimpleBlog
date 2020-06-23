package com.dimple.service;

import com.dimple.domain.VisitLog;
import com.dimple.service.dto.VisitLogQueryCriteria;
import org.aspectj.lang.JoinPoint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @className: VisitLogService
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
public interface VisitLogService {

    @Async
    void save(VisitLog visitLog, JoinPoint joinPoint, HttpServletRequest httpServletRequest);

    List<VisitLog> queryAll(VisitLogQueryCriteria criteria);

    void download(Object queryAll, HttpServletResponse response);

    Page<VisitLog> queryAll(VisitLogQueryCriteria criteria, Pageable pageable);

    void clear();

}

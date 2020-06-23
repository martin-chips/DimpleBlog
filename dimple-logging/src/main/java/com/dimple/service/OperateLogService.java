package com.dimple.service;

import com.dimple.domain.OperateLog;
import com.dimple.service.dto.LogQueryCriteria;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * @className: LogService
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
public interface OperateLogService {

    /**
     * 分页查询
     *
     * @param criteria 查询条件
     * @param pageable 分页参数
     * @return /
     */
    Object queryAll(LogQueryCriteria criteria, Pageable pageable);

    /**
     * 查询全部数据
     *
     * @param criteria 查询条件
     * @return /
     */
    List<OperateLog> queryAll(LogQueryCriteria criteria);

    /**
     * 查询用户日志
     *
     * @param criteria 查询条件
     * @param pageable 分页参数
     * @return -
     */
    Object queryAllByUser(LogQueryCriteria criteria, Pageable pageable);

    /**
     * 保存日志数据
     *
     * @param username  用户
     * @param browser   浏览器
     * @param ip        请求IP
     * @param joinPoint /
     * @param operateLog       日志实体
     */
    @Async
    void save(String username, String browser, String ip, ProceedingJoinPoint joinPoint, OperateLog operateLog);

    /**
     * 查询异常详情
     *
     * @param id 日志ID
     * @return Object
     */
    Object findByErrDetail(Long id);

    /**
     * 导出日志
     *
     * @param operateLogs     待导出的数据
     * @param response /
     * @throws IOException /
     */
    void download(List<OperateLog> operateLogs, HttpServletResponse response) throws IOException;

    /**
     * 删除所有错误日志
     */
    void delAllByError();

    /**
     * 删除所有INFO日志
     */
    void delAllByInfo();
}

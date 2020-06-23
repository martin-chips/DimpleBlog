package com.dimple.rest;

import com.dimple.annotation.OLog;
import com.dimple.service.OperateLogService;
import com.dimple.service.dto.LogQueryCriteria;
import com.dimple.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @className: LogController
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/logs")
@Api(tags = "系统：日志管理")
public class OperateLogController {

    private final OperateLogService operateLogService;

    @OLog("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@ps.check()")
    public void download(HttpServletResponse response, LogQueryCriteria criteria) throws IOException {
        criteria.setLogType("INFO");
        operateLogService.download(operateLogService.queryAll(criteria), response);
    }

    @OLog("导出错误数据")
    @ApiOperation("导出错误数据")
    @GetMapping(value = "/error/download")
    @PreAuthorize("@ps.check()")
    public void downloadErrorLog(HttpServletResponse response, LogQueryCriteria criteria) throws IOException {
        criteria.setLogType("ERROR");
        operateLogService.download(operateLogService.queryAll(criteria), response);
    }

    @GetMapping
    @ApiOperation("日志查询")
    @PreAuthorize("@ps.check()")
    public ResponseEntity<Object> query(LogQueryCriteria criteria, Pageable pageable) {
        criteria.setLogType("INFO");
        return new ResponseEntity<>(operateLogService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/user")
    @ApiOperation("用户日志查询")
    public ResponseEntity<Object> queryUserLog(LogQueryCriteria criteria, Pageable pageable) {
        criteria.setLogType("INFO");
        criteria.setBlurry(SecurityUtils.getCurrentUsername());
        return new ResponseEntity<>(operateLogService.queryAllByUser(criteria, pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/error")
    @ApiOperation("错误日志查询")
    @PreAuthorize("@ps.check()")
    public ResponseEntity<Object> queryErrorLog(LogQueryCriteria criteria, Pageable pageable) {
        criteria.setLogType("ERROR");
        return new ResponseEntity<>(operateLogService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/error/{id}")
    @ApiOperation("日志异常详情查询")
    @PreAuthorize("@ps.check()")
    public ResponseEntity<Object> queryErrorLogs(@PathVariable Long id) {
        return new ResponseEntity<>(operateLogService.findByErrDetail(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/del/error")
    @OLog("删除所有ERROR日志")
    @ApiOperation("删除所有ERROR日志")
    @PreAuthorize("@ps.check()")
    public ResponseEntity<Object> delAllErrorLog() {
        operateLogService.delAllByError();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/del/info")
    @OLog("删除所有INFO日志")
    @ApiOperation("删除所有INFO日志")
    @PreAuthorize("@ps.check()")
    public ResponseEntity<Object> delAllInfoLog() {
        operateLogService.delAllByInfo();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

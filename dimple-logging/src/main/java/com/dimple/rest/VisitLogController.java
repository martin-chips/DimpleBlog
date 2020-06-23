package com.dimple.rest;

import com.dimple.annotation.OLog;
import com.dimple.service.VisitLogService;
import com.dimple.service.dto.VisitLogQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @className: VisitLogController
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/visitLog")
@Api(tags = "系统：日志管理")
public class VisitLogController {

    private final VisitLogService visitLogService;

    @OLog("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@ps.check()")
    public void download(HttpServletResponse response, VisitLogQueryCriteria criteria) throws IOException {
        visitLogService.download(visitLogService.queryAll(criteria), response);
    }

    @GetMapping
    @ApiOperation("日志查询")
    @PreAuthorize("@ps.check()")
    public ResponseEntity<Object> query(VisitLogQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(visitLogService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @DeleteMapping(value = "/del/error")
    @OLog("清空日志")
    @ApiOperation("清空日志")
    @PreAuthorize("@ps.check()")
    public ResponseEntity<Object> delAllErrorLog() {
        visitLogService.clear();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

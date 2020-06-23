package com.dimple.modules.quartz.rest;

import com.dimple.annotation.OLog;
import com.dimple.exception.BadRequestException;
import com.dimple.modules.quartz.domain.QuartzJob;
import com.dimple.modules.quartz.service.QuartzJobService;
import com.dimple.modules.quartz.service.dto.JobQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;


/**
 * @className: QuartzJobController
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jobs")
@Api(tags = "系统:定时任务管理")
public class QuartzJobController {

    private static final String ENTITY_NAME = "quartzJob";
    private final QuartzJobService quartzJobService;

    @OLog("查询定时任务")
    @ApiOperation("查询定时任务")
    @GetMapping
    @PreAuthorize("@ps.check('timing:list')")
    public ResponseEntity<Object> query(JobQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(quartzJobService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @OLog("导出任务数据")
    @ApiOperation("导出任务数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@ps.check('timing:list')")
    public void download(HttpServletResponse response, JobQueryCriteria criteria) throws IOException {
        quartzJobService.download(quartzJobService.queryAll(criteria), response);
    }

    @OLog("导出日志数据")
    @ApiOperation("导出日志数据")
    @GetMapping(value = "/logs/download")
    @PreAuthorize("@ps.check('timing:list')")
    public void downloadLog(HttpServletResponse response, JobQueryCriteria criteria) throws IOException {
        quartzJobService.downloadLog(quartzJobService.queryAllLog(criteria), response);
    }

    @ApiOperation("查询任务执行日志")
    @GetMapping(value = "/logs")
    @PreAuthorize("@ps.check('timing:list')")
    public ResponseEntity<Object> queryJobLog(JobQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(quartzJobService.queryAllLog(criteria, pageable), HttpStatus.OK);
    }

    @OLog("新增定时任务")
    @ApiOperation("新增定时任务")
    @PostMapping
    @PreAuthorize("@ps.check('timing:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody QuartzJob resources) {
        if (resources.getId() != null) {
            throw new BadRequestException("A new " + ENTITY_NAME + " cannot already have an ID");
        }
        quartzJobService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @OLog("修改定时任务")
    @ApiOperation("修改定时任务")
    @PutMapping
    @PreAuthorize("@ps.check('timing:edit')")
    public ResponseEntity<Object> update(@Validated(QuartzJob.Update.class) @RequestBody QuartzJob resources) {
        quartzJobService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @OLog("更改定时任务状态")
    @ApiOperation("更改定时任务状态")
    @PutMapping(value = "/{id}")
    @PreAuthorize("@ps.check('timing:edit')")
    public ResponseEntity<Object> update(@PathVariable Long id) {
        quartzJobService.updateIsPause(quartzJobService.findById(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @OLog("执行定时任务")
    @ApiOperation("执行定时任务")
    @PutMapping(value = "/exec/{id}")
    @PreAuthorize("@ps.check('timing:edit')")
    public ResponseEntity<Object> execution(@PathVariable Long id) {
        quartzJobService.execution(quartzJobService.findById(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @OLog("删除定时任务")
    @ApiOperation("删除定时任务")
    @DeleteMapping
    @PreAuthorize("@ps.check('timing:del')")
    public ResponseEntity<Object> delete(@RequestBody Set<Long> ids) {
        quartzJobService.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

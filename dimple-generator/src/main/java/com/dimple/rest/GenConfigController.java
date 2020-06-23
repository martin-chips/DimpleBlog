package com.dimple.rest;

import com.dimple.service.GenConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import com.dimple.domain.GenConfig;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @className: GenConfigController
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/genConfig")
@Api(tags = "系统：代码生成器配置管理")
public class GenConfigController {

    private final GenConfigService genConfigService;

    @ApiOperation("查询")
    @GetMapping(value = "/{tableName}")
    public ResponseEntity<Object> query(@PathVariable String tableName){
        return new ResponseEntity<>(genConfigService.find(tableName), HttpStatus.OK);
    }

    @ApiOperation("修改")
    @PutMapping
    public ResponseEntity<Object> update(@Validated @RequestBody GenConfig genConfig){
        return new ResponseEntity<>(genConfigService.update(genConfig.getTableName(), genConfig),HttpStatus.OK);
    }
}

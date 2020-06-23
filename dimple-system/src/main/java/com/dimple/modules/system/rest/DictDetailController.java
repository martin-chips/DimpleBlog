package com.dimple.modules.system.rest;

import com.dimple.annotation.OLog;
import com.dimple.exception.BadRequestException;
import com.dimple.modules.system.domain.DictDetail;
import com.dimple.modules.system.service.DictDetailService;
import com.dimple.modules.system.service.dto.DictDetailDTO;
import com.dimple.modules.system.service.dto.DictDetailQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @className: DictDetailController
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@RestController
@RequiredArgsConstructor
@Api(tags = "系统：字典详情管理")
@RequestMapping("/api/dictDetail")
public class DictDetailController {

    private static final String ENTITY_NAME = "dictDetail";
    private final DictDetailService dictDetailService;

    @OLog("查询字典详情")
    @ApiOperation("查询字典详情")
    @GetMapping
    public ResponseEntity<Object> query(DictDetailQueryCriteria criteria,
                                        @PageableDefault(sort = {"dictSort"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return new ResponseEntity<>(dictDetailService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @OLog("查询多个字典详情")
    @ApiOperation("查询多个字典详情")
    @GetMapping(value = "/map")
    public ResponseEntity<Object> getDictDetailMaps(@RequestParam String dictName) {
        String[] names = dictName.split("[,，]");
        Map<String, List<DictDetailDTO>> dictMap = new HashMap<>(16);
        for (String name : names) {
            dictMap.put(name, dictDetailService.getDictByName(name));
        }
        return new ResponseEntity<>(dictMap, HttpStatus.OK);
    }

    @OLog("新增字典详情")
    @ApiOperation("新增字典详情")
    @PostMapping
    @PreAuthorize("@ps.check('dict:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody DictDetail resources) {
        if (resources.getId() != null) {
            throw new BadRequestException("A new " + ENTITY_NAME + " cannot already have an ID");
        }
        dictDetailService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @OLog("修改字典详情")
    @ApiOperation("修改字典详情")
    @PutMapping
    @PreAuthorize("@ps.check('dict:edit')")
    public ResponseEntity<Object> update(@Validated(DictDetail.Update.class) @RequestBody DictDetail resources) {
        dictDetailService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @OLog("删除字典详情")
    @ApiOperation("删除字典详情")
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("@ps.check('dict:del')")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        dictDetailService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package com.dimple.rest;

import com.dimple.base.BaseEntity;
import com.dimple.domain.Category;
import com.dimple.service.CategoryService;
import com.dimple.service.Dto.CategoryCriteria;
import com.dimple.exception.BadRequestException;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

/**
 * @className: CategoryController
 * @description:
 * @author: Dimple
 * @date: 06/18/20
 */
@RestController
@RequiredArgsConstructor
@Api(tags = "博客：博客管理")
@RequestMapping("/api/category")
public class CategoryController {
    private static final String ENTITY_NAME = "category";

    private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<Map<String, Object>> queryAll(CategoryCriteria criteria,
                                                        @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(categoryService.queryAll(criteria, pageable));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity queryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @PostMapping
    public ResponseEntity create(@Validated @RequestBody Category category) {
        if (category.getId() != null) {
            throw new BadRequestException("A new " + ENTITY_NAME + " cannot already have an ID");
        }
        categoryService.create(category);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity update(@Validated(BaseEntity.Update.class) @RequestBody Category category) {
        categoryService.update(category);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestBody Set<Long> ids) {
        Assert.notEmpty(ids, "需要删除的分类Id不能为空");
        categoryService.delete(ids);
        return ResponseEntity.ok().build();
    }

}

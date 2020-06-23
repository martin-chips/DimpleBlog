package com.dimple.rest;

import com.dimple.base.BaseEntity;
import com.dimple.domain.Comment;
import com.dimple.service.CommentService;
import com.dimple.service.Dto.CommentCriteria;
import com.dimple.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
 * @className: CommentController
 * @description:
 * @author: Dimple
 * @date: 06/19/20
 */
@RestController
@RequestMapping("api/comment")
@Slf4j
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private static final String ENTITY_NAME = "comment";

    @GetMapping()
    public ResponseEntity<Map<String, Object>> queryAll(CommentCriteria criteria,
                                                        @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(commentService.queryAll(criteria, pageable));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity queryById(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.findById(id));
    }

    @PostMapping
    public ResponseEntity create(@Validated(BaseEntity.Create.class) @RequestBody Comment comment) {
        if (comment.getId() != null) {
            throw new BadRequestException("A new " + ENTITY_NAME + " cannot already have an ID");
        }
        commentService.create(comment);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity update(@Validated(BaseEntity.Update.class) @RequestBody Comment comment) {
        commentService.update(comment);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestBody Set<Long> ids) {
        Assert.notEmpty(ids, "需要删除的分类Id不能为空");
        commentService.delete(ids);
        return ResponseEntity.ok().build();
    }

}

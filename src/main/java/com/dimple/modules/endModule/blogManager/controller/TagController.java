package com.dimple.modules.endModule.blogManager.controller;

import com.dimple.framework.enums.OperateType;
import com.dimple.framework.log.annotation.Log;
import com.dimple.framework.message.Result;
import com.dimple.framework.message.ResultUtil;
import com.dimple.modules.endModule.blogManager.bean.Tag;
import com.dimple.modules.endModule.blogManager.service.TagService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : TagsController
 * @description :
 * @date : 12/19/18 19:19
 */
@Controller
@Api("Tag接口")
public class TagController {

    @Autowired
    TagService tagService;

    @GetMapping("/page/tags.html")
    @ApiIgnore
    public String tagsPage() {
        return "blog/tags/tags";
    }


    @PutMapping("/api/tag")
    @ResponseBody
    @Log(title = "标签管理", operateType = OperateType.UPDATE)
    public Result updateTag(Tag tag) {
        tagService.updateTag(tag);
        return ResultUtil.success();
    }

    @GetMapping("/api/tag")
    @Log(title = "标签管理", operateType = OperateType.SELECT)
    @ResponseBody
    public Result selectTag(@RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                            @RequestParam(value = "title", defaultValue = "", required = false) String title,
                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "startTime", required = false) Date startTime,
                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "endTime", required = false) Date endTime) {
        Pageable pageable = PageRequest.of(pageNum < 0 ? 0 : pageNum, pageSize, Sort.Direction.DESC, "createTime");
        return ResultUtil.success(tagService.getTag(pageable, title, startTime, endTime));
    }

    @DeleteMapping("/api/tag/{ids}")
    @Log(title = "标签管理", operateType = OperateType.DELETE)
    public Result deleteTag(@PathVariable(value = "ids") Integer[] ids) {
        return ResultUtil.success(tagService.deleteTagById(ids));
    }

    @GetMapping("/api/tag/{id}")
    public Result getTagById(@PathVariable Integer id) {
        return ResultUtil.success(tagService.getTagById(id));
    }

    @PostMapping("/api/tag")
    @Log(title = "标签管理", operateType = OperateType.INSERT)
    public Result insertTag(Tag tag) {
        return ResultUtil.success(tagService.insertTag(tag));
    }
}

package com.dimple.modules.BackStageModule.VisitorManager.controller;

import com.dimple.framework.message.Result;
import com.dimple.framework.message.ResultUtil;
import com.dimple.modules.BackStageModule.VisitorManager.service.BlackListService;
import com.dimple.modules.BackStageModule.VisitorManager.bean.Blacklist;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @ClassName: BlackListController
 * @Description: 黑名单Controller
 * @Auther: Dimple
 * @Date: 2019/1/19 23:17
 * @Version: 1.0
 */
@Controller
@Api("黑名单处理Controller")
public class BlackListController {
    @Autowired
    BlackListService blackListService;

    @GetMapping("/page/blacklist.html")
    public String blacklistPage() {
        return "visitor/blacklist/list";
    }


    @ApiOperation("将IP地址添加到黑名单")
    @PostMapping("/api/visitor/blacklist/{ips}")
    @ResponseBody
    public Result visitBlacklist(@PathVariable String ips[]) {
        blackListService.insertBlackList(ips);
        return ResultUtil.success();
    }

    @DeleteMapping("/api/visitor/blacklist/{ids}")
    @ResponseBody
    public Result deleteBlacklist(@PathVariable Integer[] ids) {
        blackListService.deleteBlackList(ids);
        return ResultUtil.success();
    }

    @GetMapping("/api/visitor/blacklist")
    @ResponseBody
    public Result blacklistList(@RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "startTime", required = false) Date startTime,
                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "endTime", required = false) Date endTime) {
        Pageable pageable = PageRequest.of(pageNum < 0 ? 0 : pageNum, pageSize, Sort.Direction.DESC, "createTime");
        Page<Blacklist> allBlacklist = blackListService.getAllBlacklist(pageable, startTime, endTime);
        return ResultUtil.success(allBlacklist);
    }
}

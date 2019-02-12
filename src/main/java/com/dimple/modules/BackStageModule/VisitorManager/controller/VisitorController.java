package com.dimple.modules.BackStageModule.VisitorManager.controller;

import com.dimple.framework.enums.OperateType;
import com.dimple.framework.log.annotation.Log;
import com.dimple.framework.message.Result;
import com.dimple.framework.message.ResultUtil;
import com.dimple.modules.BackStageModule.VisitorManager.bean.Visitor;
import com.dimple.modules.BackStageModule.VisitorManager.service.VisitorService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @className: VisitorController
 * @description: 访客管理的Controller
 * @auther: Dimple
 * @date: 01/17/19 14:28
 * @version: 1.0
 */
@Controller
public class VisitorController {
    @Autowired
    VisitorService visitorService;

    @GetMapping("/page/visitor/record/list.html")
    @ApiIgnore
    @RequiresPermissions("page:visitorRecord:view")
    public String visitorListPage() {
        return "visitor/record/visitorList";
    }

    @GetMapping("/page/visitor/record/{id}")
    @ApiIgnore
    @RequiresPermissions("page:visitorRecordDetail:view")
    public String getVisitorLogInfo(@PathVariable Integer id, Model model) {
        Visitor visitorById = visitorService.getVisitorLogById(id);
        model.addAttribute("visitor", visitorById);
        return "visitor/record/visitorDetails";
    }

    @GetMapping("/page/visitor/count/list.html")
    @ApiIgnore
    @RequiresPermissions("page:visitorCount:view")
    public String visitorCountListPage() {
        return "visitor/count/list";
    }


    @GetMapping("/api/visitor/record")
    @Log(title = "访客管理", operateType = OperateType.SELECT)
    @RequiresPermissions("page:visitorRecord:query")
    @ResponseBody
    public Result visitorList(@RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
                              @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                              @RequestParam(value = "location", defaultValue = "") String location,
                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "startTime", required = false) Date startTime,
                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "endTime", required = false) Date endTime) {
        Pageable pageable = PageRequest.of(pageNum < 0 ? 0 : pageNum, pageSize, Sort.Direction.DESC, "visitTime");
        Page<Visitor> allVisitorLog = visitorService.getAllVisitorLog(pageable, location, startTime, endTime);
        return ResultUtil.success(allVisitorLog);
    }

    @DeleteMapping("/api/visitor/record/{ids}")
    @Log(title = "访客管理", operateType = OperateType.DELETE)
    @RequiresPermissions("page:visitorRecord:delete")
    @ResponseBody
    public Result deleteVisitorLog(@PathVariable Integer[] ids) {
        visitorService.deleteVisitorLogById(ids);
        return ResultUtil.success();
    }

    @DeleteMapping("/api/visitor/record")
    @Log(title = "访客管理", operateType = OperateType.DELETE)
    @RequiresPermissions("page:visitorRecord:delete")
    @ResponseBody
    public Result cleanVisitorLog() {
        visitorService.cleanVisitorLog();
        return ResultUtil.success();
    }

    @GetMapping("/api/visitor/count")
    @Log(title = "访客管理", operateType = OperateType.SELECT)
    @RequiresPermissions("page:visitorCount:query")
    @ResponseBody
    public Result getVisitorCount(@RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                  @RequestParam(value = "location", defaultValue = "") String location) {
        Pageable pageable = PageRequest.of(pageNum < 0 ? 0 : pageNum, pageSize);
        Page<List<Map<String, String>>> allVisitorCount = visitorService.getAllVisitorCount(pageable, location);
        return ResultUtil.success(allVisitorCount);
    }


}

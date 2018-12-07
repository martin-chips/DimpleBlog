package com.dimple.controller;

import com.dimple.bean.OperateLog;
import com.dimple.enums.OperateType;
import com.dimple.service.OperateLogService;
import com.dimple.utils.message.Result;
import com.dimple.utils.message.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: OperatorLogController
 * @Description: 操作log controller
 * @Auther: Dimple
 * @Date: 2018/12/1 16:40
 * @Version: 1.0
 */
@Controller
public class OperateLogController {

    @Autowired
    OperateLogService operatorLogService;

    @RequestMapping("/log/operatorLog.html")
    public String operatorList() {
        return "log/operatorLog-list";
    }


    @GetMapping("/log/operateLog.json")
    @ResponseBody
    public Result getAllOperateLog(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                   @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                   @RequestParam(value = "title", required = false) String title,//操作模块
                                   @RequestParam(value = "operatorName", required = false) String operatorName,//操作人员
                                   @RequestParam(value = "operateType", required = false) OperateType operateType,//操作类型
                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "startTime", required = false) Date startTime,
                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "endTime", required = false) Date endTime) {
        PageHelper.startPage(pageNum, pageSize, "operate_time desc");
        List<OperateLog> operateLogs = operatorLogService.getAllOperateLog(title, operatorName, operateType, startTime, endTime);
        PageInfo pageInfo = new PageInfo(operateLogs);
        return ResultUtil.success(pageInfo);
    }

    @DeleteMapping("/log/operateLog/{ids}")
    @ResponseBody
    public Result deleteLoginLog(@PathVariable Integer ids[]) {
        Integer integer = operatorLogService.deleteOperateLog(ids);
        return ResultUtil.success(integer);
    }

    @DeleteMapping("/log/operateLog")
    public Result cleanAllOperateLog() {
        Integer count = operatorLogService.cleanOperateLog();
        return ResultUtil.success(count);
    }

    @GetMapping("/log/operateLog/{id}")
    public String getDetailsOperateLog(@PathVariable("id") Integer id, Model model) {
        OperateLog operateLog = operatorLogService.getDetailsOperateLog(id);
        model.addAttribute("operateLog", operateLog);
        return "log/operateLog-details";
    }

    @GetMapping("/log/operateLog/operateType")
    @ResponseBody
    public Result getAllOperateType() {
        Map<Integer, String> operateType = operatorLogService.getOperateType();
        return ResultUtil.success(operateType);
    }


}

package com.dimple.controller;

import com.dimple.bean.OperateLog;
import com.dimple.framework.enums.OperateType;
import com.dimple.framework.log.annotation.Log;
import com.dimple.framework.message.Result;
import com.dimple.framework.message.ResultUtil;
import com.dimple.service.OperateLogService;
import io.swagger.annotations.Api;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;
import java.util.Map;

/**
 * @ClassName: OperatorLogController
 * @Description: 操作log controller
 * @Auther: Dimple
 * @Date: 2018/12/1 16:40
 * @Version: 1.0
 */
@Controller
@Api("操作日志处理Controller")
public class OperateLogController {

    @Autowired
    OperateLogService operatorLogService;

    @RequestMapping("/page/operatorLog.html")
    @ApiIgnore
    public String operatorList() {
        return "log/operatorLog-list";
    }

    @GetMapping("/page/operateLog/{id}")
    @ApiIgnore
    public String getDetailsOperateLog(@PathVariable("id") Integer id, Model model) {
        OperateLog operateLog = operatorLogService.getDetailsOperateLog(id);
        model.addAttribute("operateLog", operateLog);
        return "log/operateLog-details";
    }

    @GetMapping("/api/operateLog")
    @ResponseBody
    @Log(title = "操作日志", operateType = OperateType.SELECT)
    public Result getAllOperateLog(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                   @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                   @RequestParam(value = "title", required = false) String title,//操作模块
                                   @RequestParam(value = "operatorName", required = false) String operatorName,//操作人员
                                   @RequestParam(value = "operateType", required = false) OperateType operateType,//操作类型
                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "startTime", required = false) Date startTime,
                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "endTime", required = false) Date endTime) {
        Pageable pageable = PageRequest.of(pageNum < 0 ? 0 : pageNum, pageSize, Sort.Direction.DESC, "operateTime");
        Page<OperateLog> allOperateLog = operatorLogService.getAllOperateLog(title, operatorName, operateType, startTime, endTime, pageable);
        return ResultUtil.success(allOperateLog);

    }

    @DeleteMapping("/api/operateLog/{ids}")
    @Log(title = "操作日志", operateType = OperateType.DELETE)
    @ResponseBody
    public Result deleteLoginLog(@PathVariable Integer ids[]) {
        Integer integer = operatorLogService.deleteOperateLog(ids);
        return ResultUtil.success(integer);
    }

    @Log(title = "操作日志", operateType = OperateType.CLEAN)
    @DeleteMapping("/api/operateLog")
    public Result cleanAllOperateLog() {
        operatorLogService.cleanOperateLog();
        return ResultUtil.success();
    }


}

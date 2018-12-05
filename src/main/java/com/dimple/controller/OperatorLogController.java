package com.dimple.controller;

import com.dimple.bean.OperatorLog;
import com.dimple.framework.annotation.Log;
import com.dimple.service.OperatorLogService;
import com.dimple.utils.message.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: OperatorLogController
 * @Description: 操作log controller
 * @Auther: Dimple
 * @Date: 2018/12/1 16:40
 * @Version: 1.0
 */
@Controller
public class OperatorLogController {

    @Autowired
    OperatorLogService operatorLogService;

    @RequestMapping("/log/operatorLog.html")
    public String operatorList() {
        return "log/operatorLog-list";
    }


    @GetMapping("/log/operatorLog.json")
    public Result getAllOperatorLog(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                    @RequestParam(value = "address", required = false) String address,//操作地点
                                    @RequestParam(value = "title", required = false) String title,//操作模块
                                    @RequestParam(value = "operatorName", required = false) String LoginName,//操作人员
                                    @RequestParam(value = "action", required = false) Byte action,//操作类型
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "startTime", required = false) Date startTime,
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "endTime", required = false) Date endTime) {
//        List<OperatorLog> logs = operatorLogService.getAllOpteratorLog(address,title, LoginName);
        return null;
    }
}

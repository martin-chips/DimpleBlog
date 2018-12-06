package com.dimple.controller;

import com.dimple.bean.LoginLog;
import com.dimple.service.LoginLogService;
import com.dimple.utils.message.Result;
import com.dimple.utils.message.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: LoginLogController
 * @Description: 登录日志处理Controller
 * @Auther: Dimple
 * @Date: 2018/12/1 12:42
 * @Version: 1.0
 */
@Controller
public class LoginLogController {

    @Autowired
    LoginLogService loginLogService;

    @RequestMapping("/log/loginLog.html")
    public String loginLog(Model model) {
        Map<String, Integer> map = loginLogService.getDetails();
        model.addAttribute("details", map);
        return "log/loginLog-list";
    }

    @GetMapping("/log/loginLog.json")
    @ResponseBody
    public Result getLoginLog(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                              @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                              @RequestParam(value = "address", required = false) String address,
                              @RequestParam(value = "loginName", required = false) String LoginName,
                              @RequestParam(value = "status", required = false) Boolean status,
                              @RequestParam(value = "osType", required = false) String osType,
                              @RequestParam(value = "browserType", required = false) String browserType,
                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "startTime", required = false) Date startTime,
                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "endTime", required = false) Date endTime) {

        PageHelper.startPage(pageNum, pageSize, "login_time desc");
        List<LoginLog> loginLogs = loginLogService.getAllLoginLog(address, LoginName, status, startTime, endTime, osType, browserType);
        PageInfo pageInfo = new PageInfo(loginLogs);
        return ResultUtil.success(pageInfo);
    }

    @DeleteMapping("/log/loginLog")
    @ResponseBody
    public Result cleanLoginLog() {
        Integer integer = loginLogService.cleanLoginLog();
        return ResultUtil.success(integer);
    }

    @DeleteMapping("/log/loginLog/{ids}")
    @ResponseBody
    public Result deleteLoginLog(@PathVariable Integer ids[]) {
        Integer integer = loginLogService.deleteLoginLog(ids);
        return ResultUtil.success(integer);
    }

}

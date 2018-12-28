package com.dimple.controller;

import com.dimple.bean.LoginLog;
import com.dimple.framework.message.Result;
import com.dimple.framework.message.ResultUtil;
import com.dimple.service.LoginLogService;
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

import java.util.Date;
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

    @RequestMapping("/page/loginLog.html")
    public String loginLog(Model model) {
        Map<String, Integer> map = loginLogService.getDetails();
        model.addAttribute("details", map);
        return "log/loginLog-list";
    }

    @GetMapping("/api/loginLog")
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

        Pageable pageable = PageRequest.of(pageNum < 0 ? 0 : pageNum, pageSize, Sort.Direction.DESC, "loginTime");
        Page<LoginLog> allLoginLog = loginLogService.getAllLoginLog(address, LoginName, status, startTime, endTime, osType, browserType, pageable);

        return ResultUtil.success(allLoginLog);
    }

    @DeleteMapping("/api/loginLog")
    @ResponseBody
    public Result cleanLoginLog() {
        loginLogService.cleanLoginLog();
        return ResultUtil.success();
    }

    @DeleteMapping("/api/loginLog/{ids}")
    @ResponseBody
    public Result deleteLoginLog(@PathVariable Integer ids[]) {
        Integer integer = loginLogService.deleteLoginLog(ids);
        return ResultUtil.success(integer);
    }

}

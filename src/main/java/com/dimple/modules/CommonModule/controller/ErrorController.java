package com.dimple.modules.CommonModule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: ErrorController
 * @Description: 异常处理Controller
 * @Auther: Dimple
 * @Date: 2019/1/27 0:40
 * @Version: 1.0
 */
@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    public String errorPage(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == 404) {
            return "common/404";
        } else if (statusCode == 403) {
            return "common/403";
        }
        return "common/500";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}

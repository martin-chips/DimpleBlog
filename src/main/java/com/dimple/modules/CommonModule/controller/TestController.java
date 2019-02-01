package com.dimple.modules.CommonModule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @className: TestController
 * @description: 方便测试开的Controller，跳转到测试界面
 * @auther: Owenb
 * @date: 01/29/19
 * @version: 1.0
 */
@Controller
public class TestController {

    @GetMapping("/test/page/{pageName}")
    public String toTestPage(@PathVariable String pageName) {
        return "test/" + pageName;
    }

}

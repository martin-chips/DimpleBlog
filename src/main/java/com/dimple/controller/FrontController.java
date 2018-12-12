package com.dimple.controller;

import com.dimple.utils.message.Result;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: FrontController
 * @Description:前端请求的Controller
 * @Auther: Owenb
 * @Date: 12/12/18 17:00
 * @Version: 1.0
 */
@Controller
public class FrontController {
    /**
     * 跳转到首页
     *
     * @return
     */
    @RequestMapping("/")
    public String index() {
        return "front/index";
    }

    @RequestMapping("/{url}")
    public String toPage(@PathVariable String url) {
        return "";
    }

    /**
     * 获取个人名片信息
     * todo 做一个网站资源管理
     *
     * @return
     */
    @GetMapping("/front/businessCard")
    @ResponseBody
    public Result getBusinessCard() {
        return null;
    }
}

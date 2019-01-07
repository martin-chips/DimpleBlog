package com.dimple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : TestController
 * @description :
 * @date : 01/07/19 16:13
 */
@Controller
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "blog/mdUpload";
    }


}

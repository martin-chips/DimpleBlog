package com.dimple.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @ClassName: FrontSettingController
 * @Description: 前台界面设置项
 * @Auther: Owenb
 * @Date: 12/17/18 15:01
 * @Version: 1.0
 */
@Controller
@Api("前台界面的设置项")
public class FrontSettingController {

    @ApiIgnore
    @ApiOperation("转到设置界面")
    @GetMapping("/page/frontSetting.html")
    public String toFrontSettingPage() {
        return "frontSetting/setting";
    }



}

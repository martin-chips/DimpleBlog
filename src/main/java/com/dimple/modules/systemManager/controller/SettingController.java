package com.dimple.modules.systemManager.controller;

import com.dimple.modules.frontSetting.bean.Setting;
import com.dimple.modules.frontSetting.bean.Signature;
import com.dimple.framework.message.Result;
import com.dimple.framework.message.ResultUtil;
import com.dimple.modules.common.service.SettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import java.util.LinkedList;
import java.util.List;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : FrontSettingController
 * @description : 前端设置Controller
 * @date : 01/02/19 19:53
 */
@Controller
@Api("前台界面的设置项")
public class SettingController {

    @Autowired
    SettingService settingService;

    @ApiIgnore
    @ApiOperation("签名档设置界面")
    @GetMapping("/page/signature.html")
    public String signaturePage(Model model) {
        //获取软件分享签名档
        model.addAttribute("resourceSignature", settingService.getSignatureByKey("resourceSignature"));
        model.addAttribute("blogSignature", settingService.getSignatureByKey("blogSignature"));
        //获取博客分享签名档
        return "frontSetting/signature";
    }

    /**
     * String nickName,
     * String profession,
     * String location,
     * String email,
     * String website,
     * String myEmail,
     * String weChart
     *
     * @param model
     * @return
     */
    @ApiIgnore
    @GetMapping("/page/personCard.html")
    public String toPersonCardPage(Model model) {
        settingService.getSettingValueBySettingKey("11");
        model.addAttribute("nickName", settingService.getSettingValueBySettingKey("nickName"));
        model.addAttribute("profession", settingService.getSettingValueBySettingKey("profession"));
        model.addAttribute("location", settingService.getSettingValueBySettingKey("location"));
        model.addAttribute("email", settingService.getSettingValueBySettingKey("email"));
        model.addAttribute("website", settingService.getSettingValueBySettingKey("website"));
        model.addAttribute("weChart", settingService.getSettingValueBySettingKey("weChart"));
        model.addAttribute("myEmail", settingService.getSettingValueBySettingKey("myEmail"));
        model.addAttribute("qq", settingService.getSettingValueBySettingKey("qq"));
        return "frontSetting/personCard";
    }

    @ApiOperation("新增或者更新signature")
    @PutMapping("/api/setting/signature")
    @ResponseBody
    public Result insertOrUpdateSignature(Signature signature) {
        settingService.insertOrUpdateSignature(signature);
        return ResultUtil.success();
    }


    @ApiOperation("新增或者更新Setting")
    @PutMapping("/api/setting")
    @ResponseBody
    public Result doSetting(Setting setting) {
        //如果id为null,说明是第一次，新增
        if (setting.getId() == null) {
            settingService.createSetting(setting);
        } else {
//            settingService.updateSetting(setting);
        }
        return ResultUtil.success();
    }

    @ApiOperation("根据Setting 的key获取value")
    @GetMapping("/api/setting/{settingKey}")
    @ResponseBody
    public Result getSettingValueBySettingKey(String settingKey) {
        Setting settingBySettingKey = settingService.getSettingBySettingKey(settingKey);
        return ResultUtil.success(settingBySettingKey);
    }

    @ApiOperation("个人名片更新")
    @ResponseBody
    @PutMapping("/api/setting/personCard")
    public Result setPersonCard(
            String nickName,
            String profession,
            String location,
            String email,
            String website,
            String myEmail,
            String weChart,
            String qq) {
        List<Setting> settings = new LinkedList<>();
        settings.add(new Setting("nickName", nickName));
        settings.add(new Setting("profession", profession));
        settings.add(new Setting("location", location));
        settings.add(new Setting("email", email));
        settings.add(new Setting("website", website));
        settings.add(new Setting("myEmail", myEmail));
        settings.add(new Setting("weChart", weChart));
        settings.add(new Setting("qq", qq));
        settingService.updateSetting(settings);
        return ResultUtil.success();
    }

}


package com.dimple.controller.system;

import com.dimple.bean.Rotation;
import com.dimple.framework.enums.OperateType;
import com.dimple.framework.log.annotation.Log;
import com.dimple.framework.message.Result;
import com.dimple.framework.message.ResultUtil;
import com.dimple.service.RotationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : RotationController
 * @description :
 * @date : 01/03/19 20:57
 */
@Controller
@Api("前台界面的设置项-轮播图")
public class RotationController {

    @Autowired
    RotationService rotationService;

    @GetMapping("/page/rotation.html")
    public String indexRotationChartPage(Model model) {
        return "frontSetting/rotation";
    }

    @GetMapping("/page/rotationUpdate.html")
    public String rotationUpdatePage() {
        return "frontSetting/rotationUpdate";
    }

    @GetMapping("/page/rotationAdd.html")
    public String rotationAddPage() {
        return "frontSetting/rotationAdd";
    }



    @ApiOperation("获取所有的数据")
    @GetMapping("/api/rotation")
    @Log(title = "轮播图设置", operateType = OperateType.SELECT)
    @ResponseBody
    public Result roleList(
            @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "display", required = false) Boolean display,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "startTime", required = false) Date startTime,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "endTime", required = false) Date endTime) {
        Pageable pageable = PageRequest.of(pageNum < 0 ? 0 : pageNum, pageSize, Sort.Direction.DESC, "createTime");
        Page<Rotation> allRoles = rotationService.getRotations(title, display, startTime, endTime, pageable);
        return ResultUtil.success(allRoles);
    }

}

package com.dimple.project.chart.business.controller;

import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.project.blog.blog.service.BlogService;
import com.dimple.project.dashboard.domain.BusinessCommonData;
import com.dimple.project.log.visitorLog.service.VisitLogService;
import com.dimple.project.system.carouselMap.service.CarouselMapService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @className: BusinessController
 * @description: 业务图表Controller
 * @auther: Dimple
 * @date: 04/08/19
 * @version: 1.0
 */
@Controller
@RequestMapping("/chart/business")
public class BusinessController extends BaseController {

    @Autowired
    CarouselMapService carouselMapService;

    @Autowired
    VisitLogService visitLogService;

    @Autowired
    BlogService blogService;


    @RequiresPermissions("chart:business:view")
    @GetMapping
    public String business() {
        return "chart/business/business";
    }


    @GetMapping("/carouselMapData")
    @ResponseBody
    public AjaxResult carouselMapData() {
        return AjaxResult.success().put("data", carouselMapService.selectCarouselMapData());
    }

    @GetMapping("/spiderData")
    @ResponseBody
    public AjaxResult spiderData() {
        List<BusinessCommonData> businessCommonData = visitLogService.selectSpiderData();
        return AjaxResult.success().put("data", businessCommonData);
    }

    @GetMapping("/blogClickData")
    @ResponseBody
    public AjaxResult blogClickData(String startTime, String endTime) {
        List<BusinessCommonData> businessCommonData = blogService.selectBlogClickData(startTime, endTime);
        return AjaxResult.success().put("data", businessCommonData);
    }

    @GetMapping("/visitData")
    @ResponseBody
    public AjaxResult visitData(String startTime, String endTime) {
        List<BusinessCommonData> businessCommonData = visitLogService.selectVisitLogData(startTime, endTime);
        return AjaxResult.success().put("data", businessCommonData);
    }

}

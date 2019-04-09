package com.dimple.project.chart.business.controller;

import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.project.system.carouselMap.service.CarouselMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping
    public String business() {
        return "chart/business/business";
    }


    @GetMapping("/carouselMapData")
    @ResponseBody
    public AjaxResult carouselMapData() {
        return new AjaxResult().success().put("data", carouselMapService.selectCarouselMapData());
    }

}

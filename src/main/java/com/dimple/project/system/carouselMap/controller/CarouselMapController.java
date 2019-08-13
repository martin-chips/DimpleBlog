package com.dimple.project.system.carouselMap.controller;

import com.dimple.framework.aspectj.lang.annotation.Log;
import com.dimple.framework.aspectj.lang.enums.BusinessType;
import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.framework.web.page.TableDataInfo;
import com.dimple.project.system.carouselMap.entity.CarouselMap;
import com.dimple.project.system.carouselMap.service.CarouselMapService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @className: BgCarouselMapController
 * @description: 轮播图设置(CarouselMap)表控制层
 * @auther: Dimple
 * @Date: 2019/4/8
 * @Version: 1.1
 */
@Controller
@RequestMapping("/system/carouselMap")
public class CarouselMapController extends BaseController {

    @Resource
    private CarouselMapService carouselMapService;

    @RequiresPermissions("system:carouselMap:view")
    @GetMapping
    public String carouselMap() {
        return "system/carouselMap/carouselMap";
    }

    @GetMapping("/list")
    @RequiresPermissions("system:carouselMap:list")
    @ResponseBody
    public TableDataInfo list(CarouselMap carouselMap) {
        startPage();
        List<CarouselMap> list = carouselMapService.selectCarouselMapList(carouselMap);
        return getDataTable(list);
    }

    @GetMapping("/add")
    public String add(Model model) {
        return "system/carouselMap/add";
    }

    @Log(title = "系统轮播图", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:carouselMap:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CarouselMap carouselMap) {
        return toAjax(carouselMapService.insertCarouselMap(carouselMap));
    }

    @GetMapping("/edit/{carouselMapId}")
    public String edit(@PathVariable Integer carouselMapId, Model model) {
        model.addAttribute("carouselMap", carouselMapService.selectCarouselMapById(carouselMapId));
        return "system/carouselMap/edit";
    }

    @PutMapping("/edit")
    @RequiresPermissions("system:carouselMap:edit")
    @Log(title = "系统轮播图", businessType = BusinessType.UPDATE)
    @ResponseBody
    public AjaxResult editSave(CarouselMap carouselMap) {
        return toAjax(carouselMapService.updateCarouselMap(carouselMap));
    }

    @PutMapping("/changeDisplay/{display}")
    @RequiresPermissions("system:carouselMap:edit")
    @Log(title = "系统轮播图", businessType = BusinessType.UPDATE)
    @ResponseBody
    public AjaxResult editSave(@PathVariable String display, String carouselId) {
        return toAjax(carouselMapService.changeCarouselDisplay(carouselId,display));
    }


    @DeleteMapping("/remove")
    @Log(title = "系统轮播图", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:carouselMap:remove")
    @ResponseBody
    public AjaxResult remove(Integer[] ids) {
        return toAjax(carouselMapService.deleteCarouselMapByIds(ids));
    }
}
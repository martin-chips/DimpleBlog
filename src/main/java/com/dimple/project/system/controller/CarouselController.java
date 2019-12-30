package com.dimple.project.system.controller;

import com.dimple.framework.aspectj.lang.annotation.Log;
import com.dimple.framework.aspectj.lang.enums.BusinessType;
import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.framework.web.domain.BaseEntity;
import com.dimple.framework.web.page.TableDataInfo;
import com.dimple.project.system.domain.Carousel;
import com.dimple.project.system.service.CarouselService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @className: CarouselController
 * @description:
 * @author: Dimple
 * @date: 10/22/19
 */
@RestController
@RequestMapping("system/carousel")
public class CarouselController extends BaseController {

    final CarouselService carouselService;

    public CarouselController(CarouselService carouselService) {
        this.carouselService = carouselService;
    }

    @PreAuthorize("@permissionService.hasPermission('system:carousel:list')")
    @GetMapping("/list")
    public TableDataInfo list(Carousel carousel) {
        startPage();
        List<Carousel> list = carouselService.selectCarouselList(carousel);
        return getDataTable(list);
    }

    @PreAuthorize("@permissionService.hasPermission('system:carousel:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return AjaxResult.success(carouselService.selectCarouselById(id));
    }

    @PreAuthorize("@permissionService.hasPermission('system:carousel:add')")
    @Log(title = "轮播图管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody @Validated Carousel carousel) {
        return toAjax(carouselService.insertCarousel(carousel));
    }

    @PreAuthorize("@permissionService.hasPermission('system:carousel:edit')")
    @Log(title = "轮播图管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody @Validated(BaseEntity.Update.class) Carousel carousel) {
        return toAjax(carouselService.updateCarousel(carousel));
    }

    @PreAuthorize("@permissionService.hasPermission('system:carousel:edit')")
    @Log(title = "轮播图管理", businessType = BusinessType.UPDATE)
    @PutMapping("/{id}/display/{display}")
    public AjaxResult changeDisplay(@PathVariable Long id, @PathVariable Boolean display) {
        Carousel carousel = new Carousel();
        carousel.setDisplay(display);
        carousel.setId(id);
        return toAjax(carouselService.updateCarousel(carousel));
    }

    @PreAuthorize("@permissionService.hasPermission('system:carousel:edit')")
    @Log(title = "轮播图管理", businessType = BusinessType.UPDATE)
    @PutMapping("/{id}/target/{target}")
    public AjaxResult changeTarget(@PathVariable Long id, @PathVariable Boolean target) {
        Carousel carousel = new Carousel();
        carousel.setTarget(target);
        carousel.setId(id);
        return toAjax(carouselService.updateCarousel(carousel));
    }

    @PreAuthorize("@permissionService.hasPermission('system:carousel:remove')")
    @Log(title = "轮播图管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id) {
        return toAjax(carouselService.deleteCarouselById(id));
    }

}

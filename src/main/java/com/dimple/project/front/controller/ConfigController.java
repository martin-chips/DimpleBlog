package com.dimple.project.front.controller;

import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.project.blog.service.CategoryService;
import com.dimple.project.common.domain.Category;
import com.dimple.project.front.domain.FrontMenu;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className: ConfigController
 * @description: 网站配置Controller
 * @author: Dimple
 * @date: 11/18/19
 */
@RestController("frontConfigController")
@RequestMapping("/f")
public class ConfigController extends BaseController {

    final CategoryService categoryService;

    public ConfigController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * 网站信息
     */
    @GetMapping("siteInfo")
    public AjaxResult siteInfo() {
        Map<String, String> site = new HashMap<>();
        //网站图标地址
        site.put("icon", "");
        //网站名称
        site.put("name", "Dimple");
        //网站描述
        site.put("desc", "Dimple B desc");
        site.put("icp", "beianhao");
        site.put("copyright", "版权所有 © 2008 - 2018");
        site.put("copyright_desc", "以商业目的使用本网站内容需获许可，非商业目的使用授权遵循CC BY-NC 4.0。");
        site.put("copyright_desc_en", "lalalalalalalalalalalalal");
        return AjaxResult.success(site);
    }

    /**
     * 网站菜单
     */
    @GetMapping("/menus")
    public AjaxResult menu() {
        FrontMenu frontMenu = new FrontMenu("时光轴", 1, false, "/archive");
        FrontMenu frontMenu1 = new FrontMenu("友链", 2, false, "/link");
        FrontMenu frontMenu2 = new FrontMenu("留言", 3, false, "/leaveComment");
        FrontMenu frontMenu3 = new FrontMenu("关于", 4, true, "/about");
        List<FrontMenu> menuList = new ArrayList<>();
        menuList.add(frontMenu);
        menuList.add(frontMenu1);
        menuList.add(frontMenu2);
        menuList.add(frontMenu3);
        return AjaxResult.success(menuList);
    }

    /**
     * 博主信息
     */
    @GetMapping("bloggerInfo")
    public AjaxResult bloggerInfo() {
        Map<String, Object> bloggerInfo = new HashMap<>();
        bloggerInfo.put("name", "bianxiaofeng");
        bloggerInfo.put("desc ", "this is a desc");
        return AjaxResult.success(bloggerInfo);
    }

    @GetMapping("/categories")
    public AjaxResult categories() {
        List<Category> categories = categoryService.selectCategoryList(new Category());
        return AjaxResult.success(categories);
    }
}

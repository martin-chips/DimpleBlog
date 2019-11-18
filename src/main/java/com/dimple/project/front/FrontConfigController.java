package com.dimple.project.front;

import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.project.blog.domain.Category;
import com.dimple.project.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className: FrontConfigController
 * @description:
 * @author: Dimple
 * @date: 11/18/19
 */
@RestController
@RequestMapping("/f")
public class FrontConfigController {

    @Autowired
    CategoryService categoryService;

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

    @GetMapping("/category")
    public AjaxResult category() {
        List<Category> categories = categoryService.selectSupportCategory();
        return AjaxResult.success(categories);
    }
}

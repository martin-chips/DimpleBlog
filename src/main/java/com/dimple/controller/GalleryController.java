package com.dimple.controller;

import com.dimple.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.Map;

/**
 * @ClassName: GalleryController
 * @Description: 图片展示Controller
 * @Auther: Owenb
 * @Date: 11/21/18 11:45
 * @Version: 1.0
 */
@Controller
public class GalleryController {

    @Autowired
    GalleryService galleryService;

    @RequestMapping("/page/localGallery.html")
    public String toLocalGallery(Model model) {
        Map<String, Date> result = galleryService.selectImagesNameAndModifyTime();
        model.addAttribute("images", result);
        return "gallery/localGallery";
    }
}

package com.dimple.modules.endModule.gallery.controller;

import com.dimple.modules.endModule.gallery.service.GalleryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

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
@Api("图库处理Controller")
public class GalleryController {

    @Autowired
    GalleryService galleryService;

    @RequestMapping("/page/localGallery.html")
    @ApiIgnore
    public String toLocalGallery(Model model) {
        Map<String, Date> result = galleryService.selectImagesNameAndModifyTime();
        model.addAttribute("images", result);
        return "gallery/localGallery";
    }

    @GetMapping("/page/qiniuyunGallery.html")
    @ApiIgnore
    public String toQiniuyunGallery() {
        return "gallery/qiniuyunGallery";
    }
}

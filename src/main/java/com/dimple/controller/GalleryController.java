package com.dimple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: GalleryController
 * @Description:
 * @Auther: Owenb
 * @Date: 11/21/18 11:45
 * @Version: 1.0
 */
@Controller
@RequestMapping("/gallery")
public class GalleryController {
    @RequestMapping("/localGallery")
    public String toLocalGallery() {
        return "gallery/localGallery";
    }
}

package com.dimple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName: LinksController
 * @Description:
 * @Auther: Owenb
 * @Date: 11/21/18 12:32
 * @Version: 1.0
 */
@Controller
@RequestMapping("/links")
public class LinksController {

    @RequestMapping("/linksList")
    public ModelAndView linkedList(ModelAndView modelAndView) {

        modelAndView.setViewName("linksList1");

        return modelAndView;
    }


}

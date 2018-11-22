package com.dimple.controller;

import com.alibaba.fastjson.JSONObject;
import com.dimple.bean.Links;
import com.dimple.service.LinksService;
import com.dimple.utils.message.Result;
import com.dimple.utils.message.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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

    @Autowired
    LinksService linksService;

    @RequestMapping("/linksListView")
    public ModelAndView linkedListView(ModelAndView modelAndView) {
        modelAndView.setViewName("/links/linksList");
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping("/linksList")
    public Result linksList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Links> allLinks = linksService.getAllLinks();
        PageInfo pageInfo = new PageInfo(allLinks);
        return ResultUtil.success(pageInfo);
    }

}

package com.dimple.controller;

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

    /**
     * 返回友链页面
     *
     * @param modelAndView
     * @return
     */
    @RequestMapping("/linksListView")
    public ModelAndView linkedListView(ModelAndView modelAndView) {
        modelAndView.setViewName("/links/linksList");
        return modelAndView;
    }

    @RequestMapping("/modify")
    public ModelAndView modify(Integer linkId, ModelAndView modelAndView) {
        modelAndView.setViewName("/links/modify");
        Result linkInfo = linksService.getLinkInfo(linkId);
        modelAndView.addObject("linkInfo",linkInfo);
        return modelAndView;
    }

    /**
     * 友链列表
     *
     * @param pageNum  当前第几页
     * @param pageSize 每页显示多少条
     * @param search   搜索
     * @return
     */
    @ResponseBody
    @RequestMapping("/linksList")
    public Result linksList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                            @RequestParam(value = "search", defaultValue = "") String search) {
        PageHelper.startPage(pageNum, pageSize);
        List<Links> allLinks = linksService.getAllLinksHandled(search);
        PageInfo pageInfo = new PageInfo(allLinks);
        return ResultUtil.success(pageInfo);
    }

    /**
     * 友链的状态的切换
     *
     * @param status 状态（当前的状态）
     * @param linkId id
     * @return
     */
    @RequestMapping("/linkSwitch")
    @ResponseBody
    public Result linkSwitch(@RequestParam("status") Boolean status, @RequestParam("linkId") Integer linkId) {
        Result result = linksService.switchLinkStatus(linkId, status);
        return result;
    }

    /**
     * 删除友链
     *
     * @param linkId 友链的id
     * @return
     */
    @RequestMapping("/linkDelete")
    @ResponseBody
    public Result linkDelete(@RequestParam(value = "linkId") Integer linkId) {
        Result result = linksService.deleteLink(linkId);
        return result;
    }


    /**
     * 添加友链（管理员端添加）
     *
     * @param links 友链的信息
     * @return
     */
    @RequestMapping("/addLink")
    public Result addLink(Links links) {
        Result result = linksService.addLink(links);
        return result;
    }
}

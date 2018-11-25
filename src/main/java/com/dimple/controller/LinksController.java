package com.dimple.controller;

import com.dimple.bean.Links;
import com.dimple.service.LinksService;
import com.dimple.utils.message.Result;
import com.dimple.utils.message.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
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

    /**
     * 通用的无页面中转
     *
     * @param url 请求的uri
     * @return
     */
    @RequestMapping("/{url}")
    public String exchange(@PathVariable("url") String url) {
        return "/links/" + url;
    }

    /**
     * 返回修改友链界面
     *
     * @param linkId       要修改的友链的id
     * @param modelAndView 填充友链数据
     * @return
     */
    @RequestMapping("/modify")
    public ModelAndView modify(Integer linkId, ModelAndView modelAndView) {
        modelAndView.setViewName("/links/modify");
        Result linkInfo = linksService.getLinkInfo(linkId);
        modelAndView.addObject("linkInfo", linkInfo);
        return modelAndView;
    }

    /**
     * 友链列表
     *
     * @param pageNum   当前第几页
     * @param pageSize  每一页的大小
     * @param title     友链的title
     * @param startTime 查询开始的时间
     * @param endTime   查询结束的时间
     * @param display   状态是否为显示
     * @return Result
     */
    @ResponseBody
    @RequestMapping("/linksList")
    public Result linksList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                            @RequestParam(value = "links_title", defaultValue = "") String title,
                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "startTime", required = false) Date startTime,
                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "endTime", required = false) Date endTime,
                            @RequestParam(value = "links_display", defaultValue = "1", required = false) boolean display) {
        PageHelper.startPage(pageNum, pageSize, "create_time desc");
        List<Links> allLinks = linksService.getAllLinksHandled(title, startTime, endTime, display);
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
     * @param linkIds 友链的id,可以为多个
     * @return 处理结果
     */
    @RequestMapping("/deleteLinks")
    @ResponseBody
    public Result linkDelete(@RequestBody Integer[] linkIds) {
        Result result = linksService.deleteLinks(linkIds);
        return result;
    }


    /**
     * 添加友链（管理员端添加）
     *
     * @param links 友链的信息
     * @return
     */
    @RequestMapping("/addLink")
    @ResponseBody
    public Result addLink(Links links) {
        Result result = linksService.addLink(links);
        return result;
    }

}

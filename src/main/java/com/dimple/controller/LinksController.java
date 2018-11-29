package com.dimple.controller;

import com.dimple.bean.Links;
import com.dimple.bean.LinksDetails;
import com.dimple.constant.Status;
import com.dimple.framework.annotation.Log;
import com.dimple.service.LinksService;
import com.dimple.utils.message.Result;
import com.dimple.utils.message.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

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
@Api(tags = "友链API接口")
public class LinksController {
    /**
     * 定义的返回视图的前缀
     */
    private static String PREFIX = "/links/";

    @Autowired
    LinksService linksService;

    /**
     * 统一页面返回
     *
     * @param uri 请求的URI的地址
     * @return 视图地址
     */
    @GetMapping("links/to/{uri}.html")
    @ApiIgnore
    public String returnPage(@PathVariable("uri") String uri) {
        return PREFIX + uri;
    }

    /**
     * 转到LinksList界面
     *
     * @param modelAndView
     * @return
     */
    @GetMapping("/links/list.html")
    @ApiIgnore
    public ModelAndView linkedListView(ModelAndView modelAndView) {
        modelAndView.setViewName("/links/links-list");
        LinksDetails details = linksService.getDetails();
        modelAndView.addObject("linksDetails", details);
        return modelAndView;
    }

    @ApiIgnore
    @GetMapping("/links/{linkId}.html")
    public ModelAndView toUpdateLinksView(@PathVariable Integer linkId, ModelAndView modelAndView) {
        modelAndView.setViewName("/links/links-update");
        Result linkInfo = linksService.getLinkInfo(linkId);
        modelAndView.addObject("linkInfo", linkInfo);
        return modelAndView;
    }


    @ResponseBody
    @ApiOperation(value = "查询显示友链列表数据", notes = "返回数据类型JSON")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", defaultValue = "1", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "当前页码", defaultValue = "10", dataType = "Integer"),
            @ApiImplicitParam(name = "links_title", value = "查询友链的标题", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "友链添加开始的时间", dataType = "Date"),
            @ApiImplicitParam(name = "endTime", value = "友链添加结束的时间", dataType = "Date"),
            @ApiImplicitParam(name = "links_display", value = "友链是否显示", dataType = "Boolean"),
    })
    @GetMapping("/links.json")
    public Result linksList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                            @RequestParam(value = "links_title", defaultValue = "") String title,
                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "startTime", required = false) Date startTime,
                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "endTime", required = false) Date endTime,
                            @RequestParam(value = "links_display", required = false) Boolean display) {
        PageHelper.startPage(pageNum, pageSize, "create_time desc");
        List<Links> allLinks = linksService.getAllLinksHandled(title, startTime, endTime, display);
        PageInfo pageInfo = new PageInfo(allLinks);
        return ResultUtil.success(pageInfo);
    }

    @ApiOperation(value = "切换友链的状态", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", value = "当前该友链的状态", dataType = "Boolean"),
            @ApiImplicitParam(name = "linkId", value = "需要切换友链状态的友链ID", dataType = "Integer")
    })
    @PutMapping("/links/{linkId}/{status}")
    @ResponseBody
    public Result linkSwitch(@PathVariable("status") Boolean status, @PathVariable("linkId") Integer linkId) {
        Result result = linksService.switchLinkStatus(linkId, status);
        return result;
    }

    @ApiOperation(value = "删除友链", notes = "需要传入的为一个数组")
    @ApiImplicitParam(name = "linkIds", value = "友链的ID数组，格式为：1,2,3···,也可以传入单个参数如1")
    @DeleteMapping("links/{linkIds}")
    @ResponseBody
    public Result linkDelete(@PathVariable Integer[] linkIds) {
        Result result = linksService.deleteLinks(linkIds);
        return result;
    }

    @PutMapping("/links")
    @ResponseBody
    public Result updateLinksInfo(Links links) {
        Result result = linksService.updateLinkInfo(links);
        return result;
    }

    @Log(title = "新增友链", action = Status.INSERT)
    @ApiOperation(value = "新增友链")
    @ApiImplicitParam(name = "links", value = "传入的links的信息，除id以外，其他的必填")
    @PostMapping("/links")
    @ResponseBody
    public Result addLink(Links links) {
        Result result = linksService.addLink(links);
        return result;
    }

    /**
     * =================================================未处理友链Controller=======================================
     */

    @GetMapping("/links/unhandled/list.html")
    @ApiOperation(value = "返回未处理友链列表界面", notes = "不可操作")
    @ApiImplicitParam(name = "modelAndView", value = "ModelAndView对象", readOnly = true)
    public ModelAndView unHandledLinkedListView(ModelAndView modelAndView) {
        modelAndView.setViewName("/links/links-list-unhandled");
        LinksDetails details = linksService.getDetails();
        modelAndView.addObject("linksDetails", details);
        return modelAndView;
    }

    @ResponseBody
    @ApiOperation(value = "查询未处理友链列表数据", notes = "返回数据类型JSON")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", defaultValue = "1", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "当前页码", defaultValue = "10", dataType = "Integer"),
            @ApiImplicitParam(name = "links_title", value = "查询友链的标题", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "友链添加开始的时间", dataType = "Date"),
            @ApiImplicitParam(name = "endTime", value = "友链添加结束的时间", dataType = "Date"),
    })
    @GetMapping("/links/unhandled/list.json")
    public Result getUnHandledLinks(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                    @RequestParam(value = "links_title", defaultValue = "") String title,
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "startTime", required = false) Date startTime,
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "endTime", required = false) Date endTime) {
        PageHelper.startPage(pageNum, pageSize, "create_time desc");
        List<Links> links = linksService.getAllLinksUnHandled(startTime, endTime, title);
        PageInfo pageInfo = new PageInfo(links);
        return ResultUtil.success(pageInfo);
    }


    @ApiOperation(value = "通过友链的申请", notes = "")
    @ApiImplicitParam(name = "linkId", value = "需要通过友链申请的友链的ID", required = true, dataType = "Integer")
    @PutMapping("/links/unhandled/{linkId}")
    @ResponseBody
    public Result passLinksApply(@PathVariable Integer linkId) {
        Result result = linksService.passLinksApply(linkId);
        return result;
    }
}

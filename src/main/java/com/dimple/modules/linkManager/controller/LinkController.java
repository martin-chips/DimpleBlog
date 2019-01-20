package com.dimple.modules.linkManager.controller;

import com.dimple.modules.linkManager.bean.Link;
import com.dimple.framework.enums.LinksSearchCode;
import com.dimple.framework.enums.OperateType;
import com.dimple.framework.log.annotation.Log;
import com.dimple.framework.message.Result;
import com.dimple.framework.message.ResultUtil;
import com.dimple.modules.linkManager.service.LinksService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;

/**
 * @ClassName: LinksController
 * @Description:
 * @Auther: Owenb
 * @Date: 11/21/18 12:32
 * @Version: 1.0
 */
@Controller
@Api(tags = "友链API接口")
public class LinkController {

    @Autowired
    LinksService linksService;

    @GetMapping("/page/linkList.html")
    @ApiIgnore
    public String linkedListView(Model model) {
        model.addAttribute("linksDetails", linksService.countStatusDetails());
        return "link/list";
    }

    @GetMapping("/page/linkAdd.html")
    public String toLinksAdd() {
        return "link/add";
    }

    @ApiIgnore
    @GetMapping("/page/link/{linkId}.html")
    public ModelAndView toUpdateLinksView(@PathVariable Integer linkId, ModelAndView modelAndView) {
        modelAndView.setViewName("link/update");
        Result linkInfo = linksService.getLinkInfo(linkId);
        modelAndView.addObject("linkInfo", linkInfo);
        return modelAndView;
    }


    @ResponseBody
    @ApiOperation(value = "查询显示友链列表数据", notes = "返回数据类型JSON")
    @GetMapping("/api/link")
    @Log(title = "友情链接", operateType = OperateType.SELECT)
    public Result linksList(@RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                            @RequestParam(value = "links_title", defaultValue = "") String title,
                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "startTime", required = false) Date startTime,
                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "endTime", required = false) Date endTime,
                            @RequestParam(value = "searchCode", required = false) LinksSearchCode linksSearchCode,
                            @RequestParam(value = "links_display", required = false) Boolean display) {
        Pageable pageable = PageRequest.of(pageNum < 0 ? 0 : pageNum, pageSize, Sort.Direction.DESC, "createTime");
        Page<Link> linksCondition = linksService.getLinksCondition(title, startTime, endTime, display, linksSearchCode, pageable);
        return ResultUtil.success(linksCondition);
    }

    @ApiOperation(value = "切换友链的状态", notes = "")
    @PutMapping("/api/link/{linkId}/{status}")
    @ResponseBody
    @Log(title = "友情链接", operateType = OperateType.CHANGE_STATUS)
    public Result linkSwitch(@PathVariable("status") Boolean status, @PathVariable("linkId") Integer linkId) {
        Result result = linksService.switchLinkStatus(linkId, status);
        return result;
    }

    @ApiOperation(value = "删除友链", notes = "需要传入的为一个数组")
    @DeleteMapping("/api/link/{ids}")
    @Log(title = "友情链接", operateType = OperateType.DELETE)
    @ResponseBody
    public Result linkDelete(@PathVariable Integer[] ids) {
        Result result = linksService.deleteLinks(ids);
        return result;
    }

    @PutMapping("/api/link")
    @ResponseBody
    @ApiOperation(value = "更新友链")
    @Log(title = "友情链接", operateType = OperateType.UPDATE)
    public Result updateLinksInfo(Link links) {
        Result result = linksService.updateLinkInfo(links);
        return result;
    }

    @Log(title = "友情链接", operateType = OperateType.INSERT)
    @ApiOperation(value = "新增友链")
    @ApiImplicitParam(name = "link", value = "传入的links的信息，除id以外，其他的必填")
    @PostMapping("/api/link")
    @ResponseBody
    public Result addLink(Link links) {
        Result result = linksService.addLink(links);
        return result;
    }

    /**
     * =================================================未处理友链Controller=======================================
     */

    @GetMapping("/page/linkUnhandled.html")
    @ApiOperation(value = "返回未处理友链列表界面", notes = "不可操作")
    @ApiIgnore
    @ApiImplicitParam(name = "modelAndView", value = "ModelAndView对象", readOnly = true)
    public String unHandledLinkedListView(Model model) {
        model.addAttribute("linksDetails", linksService.countStatusDetails());
        return "link/list-unhandled";
    }

    @ResponseBody
    @ApiOperation(value = "查询未处理友链列表数据", notes = "返回数据类型JSON")
    @Log(title = "友情链接", operateType = OperateType.SELECT)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", defaultValue = "0", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "当前页码", defaultValue = "10", dataType = "Integer"),
            @ApiImplicitParam(name = "links_title", value = "查询友链的标题", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "友链添加开始的时间", dataType = "Date"),
            @ApiImplicitParam(name = "endTime", value = "友链添加结束的时间", dataType = "Date"),
    })
    @GetMapping("/api/linkUnhandled")
    public Result getUnHandledLinks(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                    @RequestParam(value = "links_title", defaultValue = "") String title,
                                    @RequestParam(value = "searchCode", required = false) LinksSearchCode linksSearchCode,
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "startTime", required = false) Date startTime,
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "endTime", required = false) Date endTime) {
        Pageable pageable = PageRequest.of(pageNum < 0 ? 0 : pageNum, pageSize, Sort.Direction.DESC, "createTime");
        Page<Link> linksCondition = linksService.getLinksCondition(title, startTime, endTime, null, linksSearchCode == null ? LinksSearchCode.SEARCH_CODE_UNHANDLED : linksSearchCode, pageable);
        return ResultUtil.success(linksCondition);
    }


    @ApiOperation(value = "通过友链的申请", notes = "")
    @Log(title = "友情链接", operateType = OperateType.OTHER)
    @PutMapping("/api/linkUnhandled/{linkId}")
    @ResponseBody
    public Result passLinksApply(@PathVariable Integer linkId) {
        Result result = linksService.passLinksApply(linkId);
        return result;
    }
}

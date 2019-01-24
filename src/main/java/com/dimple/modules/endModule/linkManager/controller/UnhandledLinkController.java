package com.dimple.modules.endModule.linkManager.controller;

import com.dimple.framework.enums.LinksSearchCode;
import com.dimple.framework.enums.OperateType;
import com.dimple.framework.log.annotation.Log;
import com.dimple.framework.message.Result;
import com.dimple.framework.message.ResultUtil;
import com.dimple.modules.endModule.linkManager.bean.Link;
import com.dimple.modules.endModule.linkManager.service.LinksService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;

/**
 * @className: UnhandledLinkController
 * @description: 未处理Link的Controller
 * @auther: Owenb
 * @date: 01/21/19 18:03
 * @version: 1.0
 */
@Controller
public class UnhandledLinkController {

    @Autowired
    LinksService linksService;

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

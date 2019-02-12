package com.dimple.modules.BackStageModule.LinkManager.controller;

import com.dimple.framework.enums.LinksSearchCode;
import com.dimple.framework.enums.OperateType;
import com.dimple.framework.log.annotation.Log;
import com.dimple.framework.message.Result;
import com.dimple.framework.message.ResultUtil;
import com.dimple.modules.BackStageModule.LinkManager.service.LinksService;
import com.dimple.modules.BackStageModule.LinkManager.bean.Link;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
    @RequiresPermissions("page:linkList:view")
    @ApiIgnore
    public String linkedListView(Model model) {
        model.addAttribute("linksDetails", linksService.countStatusDetails());
        return "link/list";
    }

    @GetMapping("/page/linkAdd.html")
    @ApiIgnore
    @RequiresPermissions("page:linkListInsert:view")
    public String toLinksAdd() {
        return "link/add";
    }

    @ApiIgnore
    @RequiresPermissions("page:linkListUpdate:view")
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
    @RequiresPermissions("LinkManager:linkListUpdate:query")
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
    @RequiresPermissions("LinkManager:linkList:query")
    @ResponseBody
    @Log(title = "友情链接", operateType = OperateType.CHANGE_STATUS)
    public Result linkSwitch(@PathVariable("status") Boolean status, @PathVariable("linkId") Integer linkId) {
        Result result = linksService.switchLinkStatus(linkId, status);
        return result;
    }

    @ApiOperation(value = "删除友链", notes = "需要传入的为一个数组")
    @RequiresPermissions("LinkManager:linkList:delete")
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
    @RequiresPermissions("LinkManager:linkList:update")
    @Log(title = "友情链接", operateType = OperateType.UPDATE)
    public Result updateLinksInfo(Link links) {
        Result result = linksService.updateLinkInfo(links);
        return result;
    }

    @Log(title = "友情链接", operateType = OperateType.INSERT)
    @ApiOperation(value = "新增友链")
    @RequiresPermissions("LinkManager:linkList:insert")
    @ApiImplicitParam(name = "link", value = "传入的links的信息，除id以外，其他的必填")
    @PostMapping("/api/link")
    @ResponseBody
    public Result addLink(Link links) {
        Result result = linksService.addLink(links);
        return result;
    }

}

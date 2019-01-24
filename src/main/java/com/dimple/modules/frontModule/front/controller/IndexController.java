package com.dimple.modules.frontModule.front.controller;

import com.dimple.framework.log.annotation.VLog;
import com.dimple.framework.message.Result;
import com.dimple.framework.message.ResultUtil;
import com.dimple.modules.endModule.blogManager.service.BlogService;
import com.dimple.modules.frontModule.front.service.IndexService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IndexController
 * @Description: 前端请求的Controller
 * @Auther: Owenb
 * @Date: 12/12/18 17:00
 * @Version: 1.0
 */
@Controller("frontIndexController")
@Api("前台请求处理Controller")
public class IndexController {

    @Autowired
    IndexService indexService;

    @Autowired
    BlogService blogService;

    /**
     * 跳转到首页
     *
     * @return
     */
    @RequestMapping("/")
    @VLog(title = "首页")
    public String index(Model model) {
        //以下两个设置轮播图下方的展示
        model.addAttribute("categories", indexService.getCategorySupportName());
        model.addAttribute("blogs", indexService.getBlogsInfo());
        //获取轮播图
        model.addAttribute("rotations", indexService.getRotationByPlace(1));
        //获取时间线
        model.addAttribute("rotationLines", indexService.getRotationByPlace(2));
        //获取推荐博文
        model.addAttribute("supportBlogs", blogService.getSupportBlog());
        return "front/index";
    }

    @GetMapping("/public/api/front/newestBlog")
    @ResponseBody
    public Result getNewestBlog(@RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum < 0 ? 0 : pageNum, pageSize, Sort.Direction.DESC, "createTime");
        List<Map<String, Object>> newestBlog = indexService.getNewestBlog(pageable);
        return ResultUtil.success(newestBlog);
    }

}

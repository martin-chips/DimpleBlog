package com.dimple.modules.frontModule.front.controller;

import com.dimple.framework.message.Result;
import com.dimple.framework.message.ResultUtil;
import com.dimple.modules.endModule.blogManager.bean.Blog;
import com.dimple.modules.endModule.blogManager.service.BlogService;
import com.dimple.modules.endModule.linkManager.bean.Link;
import com.dimple.modules.endModule.linkManager.service.LinksService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @className: SideController
 * @description: 侧边栏的配置, 主要包括以下几个地方，
 * 1. 名片
 * 2. 最近更新
 * 3. 点击排行
 * @auther: Owenb
 * @date: 01/23/19 17:33
 * @version: 1.0
 */
@Controller
public class SideController {

    @Autowired
    BlogService blogService;

    @Autowired
    LinksService linksService;

    @ApiOperation("获取最近更新的博文的信息")
    @GetMapping("/public/api/newestUpdateBlog")
    @ResponseBody
    public Result newestUpdateBlog() {
        Pageable pageable = PageRequest.of(0, 8, Sort.Direction.DESC, "updateTime");
        List<Blog> newestUpdateBlog = blogService.getAllBlogByPageable(pageable);
        return ResultUtil.success(newestUpdateBlog);
    }

    @ApiOperation("获取点击排行的博客的信息")
    @GetMapping("/public/api/clickRankingBlog")
    @ResponseBody
    public Result getClickRanking() {
        Pageable pageable = PageRequest.of(0, 8, Sort.Direction.DESC, "click");
        List<Blog> clickRankingBlogs = blogService.getAllBlogByPageable(pageable);
        return ResultUtil.success(clickRankingBlogs);
    }

    @ApiOperation("获取猜你喜欢的博客的信息")
    @GetMapping("/public/api/youMayLike")
    @ResponseBody
    public Result getYouMayLike() {
        List<Blog> randomBlogLimit = blogService.getRandomBlogLimit(10);
        return ResultUtil.success(randomBlogLimit);
    }

    @ApiOperation("获取友链信息")
    @GetMapping("/public/api/linkSide")
    @ResponseBody
    public Result getLink() {
        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "weight");
        List<Link> links = linksService.getLinksCondition(null, null, null, null, null, pageable).getContent();
        return ResultUtil.success(links);
    }

}

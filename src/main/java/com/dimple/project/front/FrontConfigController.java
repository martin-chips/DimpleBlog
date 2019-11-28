package com.dimple.project.front;

import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.framework.web.page.TableDataInfo;
import com.dimple.project.blog.domain.Blog;
import com.dimple.project.blog.domain.Category;
import com.dimple.project.blog.domain.Tag;
import com.dimple.project.blog.service.BlogService;
import com.dimple.project.blog.service.CategoryService;
import com.dimple.project.blog.service.TagService;
import com.dimple.project.front.domain.BlogQuery;
import com.dimple.project.front.domain.FrontMenu;
import com.dimple.project.system.domain.Link;
import com.dimple.project.system.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className: FrontConfigController
 * @description:
 * @author: Dimple
 * @date: 11/18/19
 */
@RestController
@RequestMapping("/f")
public class FrontConfigController extends BaseController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    TagService tagService;
    @Autowired
    BlogService blogService;

    @GetMapping("siteInfo")
    public AjaxResult siteInfo() {
        Map<String, String> site = new HashMap<>();
        //网站图标地址
        site.put("icon", "");
        //网站名称
        site.put("name", "Dimple");
        //网站描述
        site.put("desc", "Dimple B desc");
        site.put("icp", "beianhao");
        site.put("copyright", "版权所有 © 2008 - 2018");
        site.put("copyright_desc", "以商业目的使用本网站内容需获许可，非商业目的使用授权遵循CC BY-NC 4.0。");
        site.put("copyright_desc_en", "lalalalalalalalalalalalal");
        return AjaxResult.success(site);
    }

    @GetMapping("/categories")
    public AjaxResult categories() {
        List<Category> categories = categoryService.selectSupportCategory();
        return AjaxResult.success(categories);
    }

    @GetMapping("/menus")
    public AjaxResult menu() {
        FrontMenu frontMenu = new FrontMenu("时光轴", 1, false, "/archive");
        FrontMenu frontMenu1 = new FrontMenu("友链", 2, true, "/link");
        FrontMenu frontMenu2 = new FrontMenu("留言", 3, true, "/leaveComment");
        FrontMenu frontMenu3 = new FrontMenu("关于", 4, true, "/about");
        List<FrontMenu> menuList = new ArrayList<>();
        menuList.add(frontMenu);
        menuList.add(frontMenu1);
        menuList.add(frontMenu2);
        menuList.add(frontMenu3);
        return AjaxResult.success(menuList);
    }

    @GetMapping("bloggerInfo")
    public AjaxResult bloggerInfo() {
        Map<String, Object> bloggerInfo = new HashMap<>();
        bloggerInfo.put("name", "bianxiaofeng");
        bloggerInfo.put("desc ", "this is a desc");
        return AjaxResult.success(bloggerInfo);
    }


    @GetMapping("/support")
    public TableDataInfo support(BlogQuery blogQuery) {
        startPage();
        List<Blog> blogList = blogService.selectBlogList(blogQuery);
        return getDataTable(blogList);
    }

    @GetMapping("/hot")
    public TableDataInfo hot(BlogQuery blogQuery) {
        startPage();
        List<Blog> blogList = blogService.selectBlogList(blogQuery);
        return getDataTable(blogList);
    }

    @GetMapping("/blog")
    public TableDataInfo blog(BlogQuery blogQuery) {
        startPage();
        List<Blog> blogList = blogService.selectBlogList(blogQuery);
        return getDataTable(blogList);
    }

    @GetMapping("/blog/{id}")
    public AjaxResult blogDetail(@PathVariable Long id) {
        return AjaxResult.success(blogService.selectBlogDetailById(id));
    }

    @Autowired
    LinkService linkService;

    @GetMapping("/link")
    public AjaxResult link() {
        Link link = new Link();
        link.setStatus(true);
        List<Link> links = linkService.selectLinkList(link);
        return AjaxResult.success(links);
    }


    @GetMapping("/tag")
    public TableDataInfo tag() {
        startPage();
        List<Tag> tagList = tagService.selectTagList(new Tag());
        return getDataTable(tagList);
    }

    /**
     * 点赞博客
     */
    @PutMapping("/blog/like/{id}")
    public AjaxResult likeBlog(@PathVariable Long id) {
        return AjaxResult.success(blogService.incrementBlogLike(id));
    }

    /**
     * 获取blog comment
     */
    @GetMapping("/blog/comment/{id}")
    public AjaxResult commentBlog(@PathVariable Long id) {
        return AjaxResult.success(blogService.selectBlogCommentListByBlogId(id));
    }
}

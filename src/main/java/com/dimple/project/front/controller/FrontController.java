package com.dimple.project.front.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dimple.common.utils.StringUtils;
import com.dimple.common.utils.http.HttpUtils;
import com.dimple.framework.aspectj.lang.annotation.VLog;
import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.framework.web.page.TableDataInfo;
import com.dimple.project.blog.domain.Blog;
import com.dimple.project.blog.domain.Comment;
import com.dimple.project.front.domain.BlogQuery;
import com.dimple.project.front.domain.FrontMenu;
import com.dimple.project.front.service.FrontService;
import com.dimple.project.system.domain.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className: FrontController
 * @description:
 * @author: Dimple
 * @date: 2020/1/1
 */
@RestController
@RequestMapping("f")
public class FrontController extends BaseController {
    private static final String QQ_QUERY_URL = "https://r.qzone.qq.com/fcg-bin/cgi_get_portrait.fcg";

    @Autowired
    FrontService frontService;

    @GetMapping("/link")
    @VLog(title = "查看友链")
    public AjaxResult link() {
        return AjaxResult.success(frontService.selectLinkList());
    }

    @GetMapping("/link/support")
    public AjaxResult supportLink() {
        return AjaxResult.success(frontService.selectSupportLinkList());
    }

    @PutMapping("/link/{id}")
    @VLog(title = "友链跳转")
    public AjaxResult linkRedirect(@PathVariable Integer id) {
        return AjaxResult.success(frontService.incrementLinkClick(id));
    }

    /**
     * 申请link
     */
    @PostMapping("/link")
    @VLog(title = "申请友链")
    public AjaxResult insertLink(@RequestBody Link link) {
        return toAjax(frontService.insertLink(link));
    }

    /**
     * 网站菜单
     */
    @GetMapping("/menus")
    public AjaxResult menu() {
        List<FrontMenu> menuList = new ArrayList<>();
        menuList.add(new FrontMenu("时光轴", 1, false, "/archive"));
        menuList.add(new FrontMenu("友链", 2, false, "/link"));
        menuList.add(new FrontMenu("留言", 3, false, "/leaveComment"));
        menuList.add(new FrontMenu("关于", 4, false, "/about"));
        return AjaxResult.success(menuList);
    }

    /**
     * get category
     */
    @GetMapping("/categories")
    public AjaxResult categories() {
        return AjaxResult.success(frontService.selectCategoryList());
    }

    /**
     * get support blog limit 5
     */
    @GetMapping("/support")
    public AjaxResult support() {
        return AjaxResult.success(frontService.selectSupportBlogList());
    }

    /**
     * get hot blog limit 5
     */
    @GetMapping("/hot")
    public AjaxResult hot() {
        return AjaxResult.success(frontService.selectHotBlogList());
    }

    /**
     * get tag cloud
     */
    @GetMapping("/tag")
    public AjaxResult tag() {
        return AjaxResult.success(frontService.selectTagList());
    }

    /**
     * get carousel list
     */
    @GetMapping("carousel")
    public AjaxResult getCarousel() {
        return AjaxResult.success(frontService.selectCarouselList());
    }

    /**
     * get notice
     */
    @GetMapping("notice")
    public AjaxResult getNotice() {
        return AjaxResult.success(frontService.selectNoticeList());
    }

    /**
     * get comment use info by QQNum
     */
    @GetMapping("comment/qqNum/{qqNum}")
    public AjaxResult getByQQNum(@PathVariable Long qqNum) {
        String json = HttpUtils.sendGet(QQ_QUERY_URL, "uins=" + qqNum, "GBK");
        Map<String, String> qqInfo = new HashMap<>();
        if (!StringUtils.isEmpty(json)) {
            json = json.replaceAll("portraitCallBack|\\\\s*|\\t|\\r|\\n", "");
            json = json.substring(1, json.length() - 1);
            JSONObject object = JSON.parseObject(json);
            JSONArray array = object.getJSONArray(String.valueOf(qqNum));
            qqInfo.put("avatar", "https://q1.qlogo.cn/g?b=qq&nk=" + qqNum + "&s=40");
            qqInfo.put("email", qqNum + "@qq.com");
            qqInfo.put("nickName", array.getString(6));
        }
        return AjaxResult.success(qqInfo);
    }

    /**
     * insert new comment
     * todo validate comment content by Baidu analysis
     */
    @PostMapping("comment")
    @VLog(title = "发表评论", pageId = "#comment.getPageId()")
    public AjaxResult comment(@RequestBody Comment comment) {
        return toAjax(frontService.insertComment(comment));
    }

    /**
     * get comment list by page Id
     */
    @GetMapping("/comment/{id}")
    public AjaxResult commentBlog(@PathVariable Long id) {
        List<Comment> commentList = frontService.selectCommentListByPageId(id);
        return AjaxResult.success(commentList);
    }

    @PutMapping("/comment/good/{id}")
    @VLog(title = "点赞评论")
    public AjaxResult goodComment(@PathVariable Long id) {
        return toAjax(frontService.incrementCommentGood(id));
    }

    @PutMapping("/comment/bad/{id}")
    @VLog(title = "踩评论")
    public AjaxResult badComment(@PathVariable Long id) {
        return toAjax(frontService.incrementCommentBad(id));
    }

    @PutMapping("/blog/like/{id}")
    @VLog(title = "点赞博客", pageId = "#id")
    public AjaxResult likeBlog(@PathVariable Long id) {
        return AjaxResult.success(frontService.incrementBlogLike(id));
    }

    @GetMapping("/blog/{id}")
    @VLog(title = "查看博客", pageId = "#id")
    public AjaxResult blogDetail(@PathVariable Long id) {
        Blog blog = frontService.selectBlogDetailById(id);
        frontService.incrementBlogClick(id);
        return AjaxResult.success(blog);
    }

    @GetMapping("/blog")
    @VLog(title = "首页")
    public TableDataInfo blog(BlogQuery blogQuery) {
        startPage();
        List<Blog> blogList = frontService.selectBlogList(blogQuery);
        return getDataTable(blogList);
    }

    @GetMapping("/frontBlog")
    @VLog(title = "首页")
    public TableDataInfo frontBlog(BlogQuery blogQuery) {
        startPage();
        List<Blog> blogList = frontService.selectBlogList(blogQuery);
        return getDataTable(blogList);
    }

    @GetMapping("/about")
    @VLog(title = "关于我")
    public AjaxResult about() {
        return AjaxResult.success(frontService.selectAbout());
    }

    @GetMapping("/archive")
    @VLog(title = "时光轴")
    public TableDataInfo archive(BlogQuery blogQuery) {
        startPage();
        List<Blog> blogList = frontService.selectBlogArchive(blogQuery);
        return getDataTable(blogList);
    }
}

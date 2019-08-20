package com.dimple.project.front.controller;

import com.dimple.common.constant.CommonConstant;
import com.dimple.common.utils.AddressUtils;
import com.dimple.common.utils.QQUtil;
import com.dimple.common.utils.ServletUtils;
import com.dimple.common.utils.StringUtils;
import com.dimple.common.utils.baidu.BaiduUtils;
import com.dimple.common.utils.security.ShiroUtils;
import com.dimple.framework.aspectj.lang.annotation.VLog;
import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.project.blog.blog.domain.Blog;
import com.dimple.project.blog.blog.service.BlogService;
import com.dimple.project.blog.category.service.CategoryService;
import com.dimple.project.blog.comment.domain.Comment;
import com.dimple.project.blog.comment.service.CommentService;
import com.dimple.project.blog.tag.domain.Tag;
import com.dimple.project.blog.tag.service.TagService;
import com.dimple.project.front.service.HomeService;
import com.dimple.project.link.domain.Link;
import com.dimple.project.link.service.LinkService;
import com.dimple.project.system.carouselMap.service.CarouselMapService;
import com.dimple.project.system.notice.service.INoticeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Objects;

/**
 * @className: HomeController
 * @description: 前台首页Controller
 * @auther: Dimple
 * @date: 03/27/19
 * @version: 1.0
 */
@Controller
public class HomeController extends BaseController {

    @Autowired
    HomeService homeService;
    @Autowired
    BlogService blogService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    TagService tagService;
    @Autowired
    LinkService linkService;
    @Autowired
    INoticeService noticeService;
    @Autowired
    CarouselMapService carouselMapService;
    @Autowired
    CommentService commentService;

    /**
     * 设置前台页面公用的部分代码
     * 均设置Redis缓存
     */
    private void setCommonMessage(Model model) {
        //获取分类下拉项中的分类
        model.addAttribute("categories", categoryService.selectSupportCategoryList());
        //查询所有的标签
        model.addAttribute("tags", tagService.selectTagList(new Tag()));
        //查询最近更新的文章
        model.addAttribute("newestUpdateBlog", blogService.selectNewestUpdateBlog());
        //查询文章排行
        model.addAttribute("blogRanking", blogService.selectBlogRanking());
        //查询推荐博文
        model.addAttribute("supportBlog", blogService.selectSupportBlog());
        //查询通知
        model.addAttribute("notices", noticeService.selectNoticeListDisplay());
        //获取友链信息
        model.addAttribute("links", linkService.selectLinkListFront());
    }

    /**
     * 前台首页
     */
    @GetMapping("/")
    @VLog(title = "首页")
    public String index(Integer pageNum, Model model) {
        setCommonMessage(model);
        PageHelper.startPage(pageNum == null ? 1 : pageNum, 12, "create_time desc");
        model.addAttribute("blogs", new PageInfo<>(homeService.selectFrontBlogList(new Blog())));
        //放置轮播图
        model.addAttribute("carouselMaps", carouselMapService.selectCarouselMapListFront());
        return "front/index";
    }

    @GetMapping("/f/carouselMap/{carouselId}")
    public String carouselMapI(@PathVariable Integer carouselId, String url) {
        //增加点击量
        carouselMapService.incrementCarouselClickById(carouselId);
        return redirect(url);
    }

    /**
     * 关于我
     */
    @VLog(title = "关于我")
    @GetMapping("/f/about.html")
    public String about(Model model) {
        setCommonMessage(model);
        return "front/about";
    }

    /**
     * 归档
     */
    @VLog(title = "归档")
    @GetMapping("/f/archives.html")
    public String archives(Model model) {
        setCommonMessage(model);
        model.addAttribute("archives", homeService.selectArchives());
        return "front/archives";
    }

    @VLog(title = "博客")
    @GetMapping("/f/article/{blogId}.html")
    public String article(@PathVariable Integer blogId, Model model) {
        setCommonMessage(model);
        Blog blog = blogService.selectBlogWithTextAndTagsAndCategoryByBlogId(blogId);
        //只能访问是已经发表的文章
        if (!CommonConstant.one.equals(blog.getStatus())) {
            return "error/404";
        }
        //增加点击量
        blogService.incrementBlogClick(blogId);
        model.addAttribute("blog", blog);
        model.addAttribute("nextBlog", blogService.selectNextBlogById(blogId));
        model.addAttribute("previousBlog", blogService.selectPreviousBlogById(blogId));
        model.addAttribute("randBlogList", blogService.selectRandBlogList());
        Comment comment = new Comment();
        comment.setPageId(blogId);
        comment.setDisplay(true);
        model.addAttribute("comments", commentService.selectCommentListForFront(comment));
        return "front/article";
    }

    @VLog(title = "分类")
    @GetMapping({"/f/category/{categoryId}.html"})
    public String category(@PathVariable Integer categoryId, Integer pageNum, Model model) {
        setCommonMessage(model);
        model.addAttribute("category", categoryService.selectCategoryById(categoryId));
        Blog blog = new Blog();
        blog.setCategoryId(categoryId);
        PageHelper.startPage(pageNum == null ? 1 : pageNum, 10, "create_time desc");
        model.addAttribute("blogs", new PageInfo<>(homeService.selectFrontBlogList(blog)));
        return "front/category";
    }

    @VLog(title = "标签")
    @GetMapping("/f/tag/{tagId}.html")
    public String tag(@PathVariable Integer tagId, Integer pageNum, Model model) {
        setCommonMessage(model);
        PageHelper.startPage(pageNum == null ? 1 : pageNum, 10, "b.create_time desc");
        List<Blog> blogs = blogService.selectBlogListByTagId(tagId);
        model.addAttribute("blogs", new PageInfo(blogs));
        model.addAttribute("tag", tagService.selectTagById(tagId));
        return "front/tag";
    }

    @VLog(title = "搜索")
    @GetMapping("/f/search/{keyWord}.html")
    public String search(@PathVariable String keyWord, Integer pageNum, Model model) {
        setCommonMessage(model);
        PageHelper.startPage(pageNum == null ? 1 : pageNum, 10, "create_time desc");
        Blog blog = new Blog();
        blog.setTitle(keyWord);
        List<Blog> blogs = blogService.selectBlogList(blog);
        model.addAttribute("blogs", new PageInfo<>(blogs));
        model.addAttribute("keyWord", keyWord);
        return "front/search";
    }

    /**
     * 留言
     */
    @VLog(title = "留言页")
    @GetMapping("/f/leaveComment.html")
    public String leaveComment(Model model) {
        setCommonMessage(model);
        Comment comment = new Comment();
        comment.setPageId(-1000);
        model.addAttribute("comments", commentService.selectCommentListForFront(comment));
        model.addAttribute("pageId", -1000);
        return "front/leaveComment";
    }

    /**
     * 版权声明
     */
    @VLog(title = "版权声明")
    @GetMapping("/f/copyright.html")
    public String copyright(Model model) {
        setCommonMessage(model);
        Comment comment = new Comment();
        comment.setPageId(-2000);
        model.addAttribute("comments", commentService.selectCommentListForFront(comment));
        return "front/other/copyright";
    }

    /**
     * 侵删联系
     */
    @VLog(title = "侵删联系")
    @GetMapping("/f/delete.html")
    public String delete(Model model) {
        setCommonMessage(model);
        Comment comment = new Comment();
        comment.setPageId(-3000);
        model.addAttribute("comments", commentService.selectCommentListForFront(comment));
        return "front/other/delete";
    }

    /**
     * 友链显示
     */
    @VLog(title = "友链显示")
    @GetMapping("/f/link.html")
    public String link(Model model) {
        setCommonMessage(model);
        model.addAttribute("links", linkService.selectLinkListFrontWithSummary());
        return "front/link";
    }

    /**
     * 友链跳转
     */
    @VLog(title = "友链跳转")
    @GetMapping("/f/linkRedirect")
    public String redirectTo(String ref, Integer id) {
        //增加点击量
        linkService.incrementLinkClickById(id);
        return redirect(ref);
    }

    /**
     * 申请友链
     */
    @PostMapping("/f/applyLink")
    @ResponseBody
    @VLog(title = "申请友链")
    public AjaxResult applyLink(Link link) {
        linkService.applyLink(link);
        return AjaxResult.success();
    }


    /**
     * 添加评论
     */
    @PostMapping("/f/comment")
    @ResponseBody
    @VLog(title = "评论")
    public AjaxResult comment(Comment comment) {
        comment.setIp(ShiroUtils.getIp());
        comment.setDisplay(true);
        comment.setUrl(ServletUtils.getRequest().getRequestURI());
        comment.setCreateBy(ServletUtils.getRequest().getRequestedSessionId());
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        // 获取客户端操作系统
        String os = userAgent.getOperatingSystem().getName();
        // 获取客户端浏览器
        String browser = userAgent.getBrowser().getName();
        comment.setOs(os);
        comment.setBrowser(browser);
        comment.setLocation(AddressUtils.getRealAddressByIP(comment.getIp()));
        String reviewMsg = "";
        try {
            reviewMsg = BaiduUtils.checkText(comment.getContent());
        } catch (Exception e) {
            //ignore
        }
        comment.setReviewMsg(reviewMsg);
        comment.setDisplay(StringUtils.isEmpty(reviewMsg) ? true : false);
        commentService.insertComment(comment);
        return StringUtils.isEmpty(reviewMsg) ? AjaxResult.success() : AjaxResult.error(reviewMsg + ",请文明发言！");
    }


    @GetMapping("/f/qqInfo")
    @ResponseBody
    public AjaxResult qqInfo(Long qqNum) {
        QQUtil.QQInfo qqByQQNum = QQUtil.getQQByQQNum(qqNum);
        if (Objects.nonNull(qqByQQNum)) {
            return AjaxResult.success().put("qqInfo", qqByQQNum);
        }
        return AjaxResult.error();
    }

    /**
     * 刷新当前评论框
     */
    @GetMapping("/f/comment")
    public String commentSync(Integer pageId, Model model) {
        Comment comment = new Comment();
        comment.setPageId(pageId);
        List<Comment> comments = commentService.selectCommentListForFront(comment);
        model.addAttribute("comments", comments);
        return "front/article::comment";
    }

    @PutMapping("/f/comment/good")
    @ResponseBody
    public AjaxResult commentGood(Integer pageId, Integer commentId) {
        commentService.increaseGoodCount(pageId, commentId);
        return AjaxResult.success();
    }

    @PutMapping("/f/comment/bad")
    @ResponseBody
    public AjaxResult commentBad(Integer pageId, Integer commentId) {
        commentService.increaseBadCount(pageId, commentId);
        return AjaxResult.success();
    }

}

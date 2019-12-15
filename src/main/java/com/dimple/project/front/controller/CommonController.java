package com.dimple.project.front.controller;

import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.framework.web.page.TableDataInfo;
import com.dimple.project.blog.domain.Blog;
import com.dimple.project.blog.domain.Tag;
import com.dimple.project.blog.service.BlogService;
import com.dimple.project.blog.service.CategoryService;
import com.dimple.project.blog.service.CommentService;
import com.dimple.project.blog.service.TagService;
import com.dimple.project.front.domain.BlogQuery;
import com.dimple.project.system.domain.Carousel;
import com.dimple.project.system.domain.Notice;
import com.dimple.project.system.service.CarouselService;
import com.dimple.project.system.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @className: HomeController
 * @description:
 * @author: Dimple
 * @date: 2019/12/15
 */
@RestController
@RequestMapping("/f")
public class CommonController extends BaseController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    TagService tagService;
    @Autowired
    BlogService blogService;
    @Autowired
    CommentService commentService;
    @Autowired
    CarouselService carouselService;

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


    @GetMapping("/tag")
    public TableDataInfo tag() {
        startPage();
        List<Tag> tagList = tagService.selectTagList(new Tag());
        return getDataTable(tagList);
    }


    @GetMapping("carousel")
    public AjaxResult getCarousel() {
        Carousel carousel = new Carousel();
        carousel.setDisplay(true);
        List<Carousel> carouselList = carouselService.selectCarouselList(carousel);
        return AjaxResult.success(carouselList);
    }

    @Autowired
    NoticeService noticeService;

    @GetMapping("notice")
    public AjaxResult getNotice() {
        Notice notice = new Notice();
        notice.setStatus(true);
        List<Notice> notices = noticeService.selectNoticeList(notice);
        return AjaxResult.success(notices);
    }
}

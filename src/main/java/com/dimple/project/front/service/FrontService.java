package com.dimple.project.front.service;

import com.dimple.project.blog.domain.Blog;
import com.dimple.project.blog.domain.Comment;
import com.dimple.project.common.domain.Category;
import com.dimple.project.common.domain.Tag;
import com.dimple.project.front.domain.BlogQuery;
import com.dimple.project.system.domain.Carousel;
import com.dimple.project.system.domain.Link;
import com.dimple.project.system.domain.Notice;

import java.util.List;

/**
 * @className: FrontService
 * @description:
 * @author: Dimple
 * @date: 2020/1/1
 */
public interface FrontService {
    /**
     * get all link which display is true and delete time is null
     *
     * @return link list
     */
    List<Link> selectLinkList();

    /**
     * insert link from user apply
     *
     * @param link link
     * @return the count has been modified
     */
    int insertLink(Link link);

    /**
     * get category which type = Blog
     *
     * @return category list
     */
    List<Category> selectCategoryList();

    /**
     * get support blog list
     *
     * @return support blog list
     */
    List<Blog> selectSupportBlogList();

    /**
     * get hot blog.
     * todo need get data from visit log and computed the timely data
     *
     * @return hot blog list
     */
    List<Blog> selectHotBlogList();

    /**
     * get tag list
     *
     * @return tag list
     */
    List<Tag> selectTagList();

    /**
     * get carousel list
     *
     * @return list
     */
    List<Carousel> selectCarouselList();

    /**
     * get notice contain type =1/2
     *
     * @return list of notice
     */
    List<Notice> selectNoticeList();

    /**
     * insert comment
     *
     * @param comment comment
     * @return the count which has been modified
     */
    int insertComment(Comment comment);

    /**
     * get comment list by page id
     *
     * @param id page id
     * @return comment list
     */
    List<Comment> selectCommentListByPageId(Long id);

    /**
     * increment comment good count
     *
     * @param id comment id
     * @return the count that data has been modified
     */
    int incrementCommentGood(Long id);

    /**
     * increment comment bad count
     *
     * @param id comment id
     * @return the count that data has been modified
     */
    int incrementCommentBad(Long id);

    /**
     * increment blog good count
     *
     * @param id blog id
     * @return the count that data has been modified
     */
    int incrementBlogLike(Long id);

    /**
     * get blog detail by blog id
     *
     * @param id id
     * @return blog
     */
    Blog selectBlogDetailById(Long id);

    /**
     * get blog list by condition
     *
     * @param blogQuery condition
     * @return blog list
     */
    List<Blog> selectBlogList(BlogQuery blogQuery);

    /**
     * update link click
     *
     * @param id link id
     * @return update count
     */
    int incrementLinkClick(Integer id);

    /**
     * update blog click
     *
     * @param id blog id
     * @return update count
     */
    int incrementBlogClick(Long id);

    /**
     * get about from config
     *
     * @return about html
     */
    String selectAbout();

    List<Blog> selectBlogArchive(BlogQuery blogQuery);
}

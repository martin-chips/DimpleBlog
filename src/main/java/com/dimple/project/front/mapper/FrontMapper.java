package com.dimple.project.front.mapper;

import com.dimple.project.blog.domain.Blog;
import com.dimple.project.blog.domain.Comment;
import com.dimple.project.common.domain.Category;
import com.dimple.project.common.domain.Tag;
import com.dimple.project.front.domain.BlogQuery;
import com.dimple.project.system.domain.Carousel;
import com.dimple.project.system.domain.Config;
import com.dimple.project.system.domain.Link;
import com.dimple.project.system.domain.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @className: FrontMapper
 * @description:
 * @author: Dimple
 * @date: 2020/1/1
 */
public interface FrontMapper {
    /**
     * get all link
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
     * select category list and order by support
     *
     * @return category list
     */
    List<Category> selectCategoryList();

    /**
     * get support blog list
     *
     * @return support blog
     */
    List<Blog> selectSupportBlogList();

    /**
     * get hot blog list
     *
     * @return hot blog list
     */
    @Deprecated
    List<Blog> selectHotBlogList();

    /**
     * select tag list contain the count of this tag use
     *
     * @return tag list
     */
    List<Tag> selectTagList();

    /**
     * carousel list
     *
     * @return carousel list
     */
    List<Carousel> selectCarouselList();

    /**
     * select notice list
     *
     * @return notice list
     */
    List<Notice> selectNoticeList();

    /**
     * insert Comment
     *
     * @param comment
     * @return the count which data has been modified
     */
    int insertComment(Comment comment);

    /**
     * select comment list by page id
     *
     * @param id page id
     * @return comment list
     */
    List<Comment> selectCommentListByPageId(Long id);

    int incrementCommentGood(Long id);

    int incrementCommentBad(Long id);

    int incrementBlogLike(Long id);

    /**
     * blog detail
     *
     * @param id blog id
     * @return blog
     */
    Blog selectBlogDetailById(Long id);

    /**
     * increment Blog Click count
     *
     * @param id blog id
     * @return count
     */
    int incrementBlogClick(Long id);

    List<Blog> selectBlogList(BlogQuery blogQuery);

    List<Tag> selectTagListByTypeAndId(@Param("type") Integer type, @Param("id") Long id);

    int incrementLinkClick(Integer id);

    Config selectAbout();

    Comment selectCommentById(Long id);

    List<Link> selectSupportLinkList();

    String selectBlogTitleById(Long id);
}

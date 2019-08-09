package com.dimple.project.blog.blog.service;

import com.dimple.project.blog.blog.domain.Blog;
import com.dimple.project.dashboard.domain.BusinessCommonData;

import java.util.List;

/**
 * @className: BlogService
 * @description:
 * @auther: Dimple
 * @Date: 2019/3/16
 * @Version: 1.0
 */
public interface BlogService {
    /**
     * 根据条件查询所有的blog
     *
     * @param blog 带有条件信息的blog
     * @return 博客集合
     */
    List<Blog> selectBlogList(Blog blog);

    /**
     * 新增博客
     *
     * @param blog 需要新增的博客的实体
     * @return 受影响的行数
     */
    int insertBlog(Blog blog);

    /**
     * 根据id修改blog
     *
     * @param blogId 需要修改的blog的id
     * @return blog实体
     */
    Blog selectBlogById(Integer blogId);

    /**
     * 修改blog信息
     *
     * @param blog 需要修改的blog实体
     * @return 受影响的行数
     */
    int updateBlog(Blog blog);

    /**
     * 切换support状态
     *
     * @param blogId  需要切换状态的blog的id
     * @param support 需要切换的support状态
     * @return 受影响的行数
     */
    int updateBlogSupportById(Integer blogId, String support);

    /**
     * 切换博客的状态
     *
     * @param blogIds 博客的id
     * @param status  需要切换的博客的状态
     * @return 受影响的行数
     */
    int updateBlogStatusById(String blogIds, String status);

    /**
     * 删除博客
     *
     * @param ids 需要删除的blog的id
     * @return 受影响的行数
     */
    int deleteBlogById(Integer[] ids);

    /**
     * 根据Blog的状态统计数量
     *
     * @param status 需要统计的blog的状态
     * @return 该状态下的blog数量
     */
    int selectBlogCountByStatus(int status);

    /**
     * 根据Blog的id获取blog信息，包括tag
     *
     * @param blogId 需要获取信息的blog的id
     * @return Blog实体类
     */
    Blog selectBlogWithTextAndTagsAndCategoryByBlogId(Integer blogId);

    /**
     * 获取最新更新的blog
     *
     * @return 最近更新的blog的列表
     */
    List<Blog> selectNewestUpdateBlog();

    /**
     * 获取文章排行
     *
     * @return 文章排行列表
     */
    List<Blog> selectBlogRanking();

    /**
     * 获取推荐博文
     *
     * @return 推荐博文集合
     */
    List<Blog> selectSupportBlog();

    /**
     * 获取当前Blog的上一篇Blog
     *
     * @param blogId id
     * @return 上一篇博客
     */
    Blog selectPreviousBlogById(Integer blogId);

    /**
     * 获取下一篇博客
     *
     * @param blogId 当前博客的Id
     * @return 下一篇Blog
     */
    Blog selectNextBlogById(Integer blogId);

    /**
     * 获取随机的blog列表
     *
     * @return 随机的blog的列表
     */
    List<Blog> selectRandBlogList();

    /**
     * 根据tag的id查询blog列表
     *
     * @param tagId tag的id
     * @return list集合
     */
    List<Blog> selectBlogListByTagId(Integer tagId);

    /**
     * 图表展示博客点击量统计
     *
     * @param startTime
     * @param endTime
     * @return 博客点击量
     */
    List<BusinessCommonData> selectBlogClickData(String startTime, String endTime);

    /**
     * 根据Id设置是否允许评论
     *
     * @param allowComment 状态
     * @param blogId       blogId
     * @return 受影响的行数
     */
    int updateBlogAllowCommentByBlogId(Boolean allowComment, Integer blogId);

    /**
     * 增加Blog的点击量
     *
     * @param blogId BlogId
     * @return 受影响的行数
     */
    int incrementBlogClick(Integer blogId);

}

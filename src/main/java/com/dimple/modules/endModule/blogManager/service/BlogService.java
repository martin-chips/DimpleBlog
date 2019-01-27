package com.dimple.modules.endModule.blogManager.service;

import com.dimple.modules.endModule.blogManager.bean.Blog;
import com.dimple.framework.enums.BlogStatus;
import com.dimple.modules.frontModule.front.domain.Archive;
import com.dimple.modules.frontModule.front.domain.BlogDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: BlogService
 * @Description:
 * @Auther: Owenb
 * @Date: 12/12/18 14:56
 * @Version: 1.0
 */
public interface BlogService {
    /**
     * 获得所有的Blog信息
     *
     * @param title
     * @param startTime
     * @param endTime
     * @param status
     * @param pageable
     * @return
     */
    Page<Blog> getAllBlogs(String title, Date startTime, Date endTime, Integer status, Pageable pageable);

    /**
     * 新增博客
     *
     * @param blog
     * @return
     */
    Blog insertBlog(Blog blog);

    /**
     * 删除Blog
     *
     * @param blogId
     * @return
     */
    void deleteBlog(Integer blogId);

    /**
     * 更新博客的信息
     *
     * @param blog
     * @return
     */
    Blog updateBlog(Blog blog);

    /**
     * 更换博客的状态
     *
     * @param id     博客的id
     * @param status 将要切换的状态（1表示发布，2表示草稿箱，3表示回收站）
     * @return 受影响的行数
     */
    int changeBlogStatus(Integer[] id, Integer status);

    /**
     * 根据Blog的Id获取Blog信息
     *
     * @param id Blog的ID
     * @return 该Id对应的博客
     */
    Blog selectBlogById(Integer id);

    /**
     * 获取博客状态的统计（有多少是已经发布了的，有多少是已经在草稿箱，有多少是在垃圾箱）
     *
     * @return Map
     */
    Map<String, Integer> selectCountOfBlogStatus();

    /**
     * 根据Blog的Id获取博客的详细信息（包括content）
     *
     * @param id
     * @return
     */
    Blog selectBlogByIdBlobs(Integer id);

    /**
     * 是否推荐博客
     *
     * @param ids    博客的ID
     * @param status 博客的当前的状态
     * @return
     */
    int supportBlog(Integer[] ids, Boolean status);

    /**
     * 获取博客的状态及数量详情页展示
     *
     * @param i
     * @return
     */
    int selectBlogCountByStatus(BlogStatus i);

    List<Blog> getAllBlogByCategoryId(int id);

    /**
     * 根据博客的Id获取博客(不含正文内容)
     *
     * @param id
     * @return
     */
    Blog getBlogById(Integer id);

    Blog getBlogAndInfoById(Integer id);

    /**
     * 获取点击排行的数据
     *
     * @return
     */
    List<Blog> getClickRanking();

    /**
     * 获取当前Id的前后两条博文的信息
     *
     * @param id
     * @return
     */
    Map<String, Blog> getNextAndPreviousBlogById(Integer id);

    /**
     * 获取相同类别的Id的其他文章，至多5个
     *
     * @param categoryId
     * @return
     */
    List<Blog> getRelatedBlogByCategoryId(Integer categoryId);

    List<Blog> getSupportBlog();

    /**
     * 获取最近更新的博文的信息
     *
     * @param pageable
     */
    List<Blog> getAllBlogByPageable(Pageable pageable);

    /**
     * 获取随机的博文信息
     *
     * @param i
     * @return
     */
    List<Blog> getRandomBlogLimit(int i);

    /**
     * 获取归档信息
     */
    List<Archive> getArchive();

    Page<BlogDomain> getALlBlogByCategoryId(Pageable pageable, int categoryId);
}

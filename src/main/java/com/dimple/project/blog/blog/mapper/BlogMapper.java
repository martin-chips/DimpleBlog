package com.dimple.project.blog.blog.mapper;

import com.dimple.project.blog.blog.domain.Blog;
import com.dimple.project.dashboard.domain.BusinessCommonData;
import com.dimple.project.front.domain.Archives;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @className: BlogMapper
 * @description: 博客处理dao层
 * @auther: Dimple
 * @Date: 2019/3/16
 * @Version: 1.0
 */
public interface BlogMapper {
    /**
     * 根据条件查询博客信息
     *
     * @return 符合条件博客集合
     */
    List<Blog> selectBlogList(Blog blog);

    /**
     * 根据blogId删除bolg
     *
     * @param id 需要删除的blogid
     * @return 受影响的条数
     */
    int deleteBlogById(Integer id);

    /**
     * 批量删除
     *
     * @param ids 需要批量删除的blog的id
     * @return 受影响的行数
     */
    int deleteBlogByIds(Integer[] ids);

    /**
     * 更新blog
     *
     * @param blog 需要更新的blog的实体类的id
     * @return 受影响的行数
     */
    int updateBlog(Blog blog);

    /**
     * 新增blog
     *
     * @param blog blog实体类
     * @return 受影响的行数
     */
    int insertBlog(Blog blog);

    /**
     * 根据blogid获取blog的全部信息（包括内容）
     *
     * @param id blog 的id
     * @return blog实体类
     */
    Blog selectBlogById(Integer id);

    /**
     * 根据blog的id切换博客的状态
     *
     * @param blogIds 需要切换状态的blog的id
     * @param status  需要切换到的状态
     * @return 受影响的额行数
     */
    int updateBlogStatusByIds(@Param("blogIds") Integer[] blogIds, @Param("status") String status);

    /**
     * 切换blog的support状态
     *
     * @param blogId  需要切换状态的id
     * @param support 需要切换的状态
     * @return 受影响的行数
     */
    int updateBlogSupportById(@Param("blogId") Integer blogId, @Param("support") String support);

    /**
     * 根据Blog的状态统计数量
     *
     * @param status 需要查询的blog的状态
     * @return 该状态下的数量
     */
    int selectBlogCountByStatus(int status);

    /**
     * 获取博客的所有数据
     *
     * @param blogId
     * @return
     */
    Blog selectBlogWithTextById(Integer blogId);

    /**
     * 获取前台展示的Blog的列表，包含category 信息
     *
     * @param blog
     * @return
     */
    List<Blog> selectFrontBlogList(Blog blog);

    /**
     * 获取最近更新的blog,限制i条
     *
     * @return
     */
    List<Blog> selectNewestUpdateBlog(int i);

    /**
     * 获取最近i条排行blog
     *
     * @param i 限制条数
     * @return list
     */
    List<Blog> selectBlogRankingList(int i);

    /**
     * 获取推荐博文
     *
     * @return 推荐博文集合
     */
    List<Blog> selectSupportBlogList(int i);

    /**
     * 获取上一篇博文
     */
    Blog selectPreviousBlogById(Integer blogId);

    /**
     * 获取下一篇博文
     */
    Blog selectNextBlogById(Integer blogId);

    /**
     * 随机获取i篇blog
     *
     * @param i blog的数量
     * @return 随机blog集合
     */
    List<Blog> selectRandBlogListLimit(int i);

    /**
     * 根据tag的id查询出blog
     *
     * @param tagId tag的id
     * @return 集合
     */
    List<Blog> selectBlogsByTagId(@Param("tagId") Integer tagId);

    /**
     * 获取归档的Date和count
     *
     * @return list集合
     */
    List<Archives> selectArchivesDateAndCount();

    /**
     * 根据createTime获取blog信息
     *
     * @param date 创建的时间
     * @return blog集合
     */
    List<Blog> selectBlogByCreateTime(String date);

    /**
     * 增加blog的访问量
     *
     * @param blogId 需要增加访问量的blog的id
     * @return 受影响的行
     */
    int incrementBlogClick(Integer blogId);

    /**
     * 获取博文展示数据
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 博文数据
     */
    List<BusinessCommonData> selectBlogClickData(@Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 根据Blog的Id获取title
     *
     * @param blogId blogId
     * @return BlogTitle
     */
    String selectBlogTitleByBlogId(Integer blogId);

    /**
     * 更新是否允许评论状态
     *
     * @param allowComment 是否允许
     * @param blogId       BlogId
     * @return 受影响的函数
     */
    int updateBlogAllowCommentByBlogId(@Param("allowComment") Boolean allowComment, @Param("blogId") Integer blogId);
}

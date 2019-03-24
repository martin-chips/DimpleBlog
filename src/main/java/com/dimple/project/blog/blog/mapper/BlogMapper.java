package com.dimple.project.blog.blog.mapper;

import com.dimple.project.blog.blog.domain.Blog;
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
}

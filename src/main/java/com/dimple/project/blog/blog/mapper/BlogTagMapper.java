package com.dimple.project.blog.blog.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @className: BlogTagMapper
 * @description:
 * @auther: Dimple
 * @Date: 2019/3/23
 * @Version: 1.0
 */
public interface BlogTagMapper {
    /**
     * 新增blog和tag的关联
     *
     * @param tagId  需要新增的tag的id
     * @param blogId 需要新增的blog的id
     * @return 受影响的行数
     */
    int insertBlogTag(@Param("tagId") Integer tagId, @Param("blogId") int blogId);

    /**
     * 根据blog的id删除原有的blog和tag的关联关系
     *
     * @param blogId
     */
    void deleteBlogTagByBlogId(Integer blogId);

    /**
     * 根据blog的id获取tag的id集合
     *
     * @param blogId
     * @return tag的id list
     */
    List<Integer> selectTagIdsByBlogId(Integer blogId);

    /**
     * 根据tag的id删除tag
     *
     * @param ids 需要删除的tag的id
     * @return 受影响的行
     */
    int deleteBlogTagByTagIds(Integer[] ids);

    /**
     * 根据tag的id查找与之相关的blog的数量
     *
     * @param tagId 需要查找的tag的id
     * @return 数量
     */
    int selectBlogTagCountByTagId(Integer tagId);

}

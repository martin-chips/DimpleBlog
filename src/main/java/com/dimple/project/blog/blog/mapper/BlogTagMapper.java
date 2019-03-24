package com.dimple.project.blog.blog.mapper;

import org.apache.ibatis.annotations.Param;

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
}

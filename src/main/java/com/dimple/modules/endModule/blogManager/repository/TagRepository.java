package com.dimple.modules.endModule.blogManager.repository;

import com.dimple.modules.endModule.blogManager.bean.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @ClassName: TagRepository
 * @Description:
 * @Auther: Owenb
 * @Date: 12/28/18 20:21
 * @Version: 1.0
 */
public interface TagRepository extends JpaRepository<Tag, Integer>, JpaSpecificationExecutor<Tag> {

    Tag findTagById(Integer id);

    Tag getTagByTitle(String title);

    @Query(value = "select title from tag t,blog_tag b where b.blog_id=:blogId and b.tag_id=t.id", nativeQuery = true)
   String[] getTagByBlogId(@Param("blogId") Integer blogId);


}

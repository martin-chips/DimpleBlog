package com.dimple.modules.BackStageModule.BlogManager.repository;

import com.dimple.modules.BackStageModule.BlogManager.bean.BlogTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @className: BlogTagRepository
 * @description:
 * @auther: Owenb
 * @date: 01/28/19
 * @version: 1.0
 */
@Repository
public interface BlogTagRepository extends JpaRepository<BlogTag, Integer> {

    void deleteByBlogId(Integer blogId);

}

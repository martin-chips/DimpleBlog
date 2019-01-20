package com.dimple.modules.blogManager.repository;

import com.dimple.modules.blogManager.bean.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @ClassName: TagRepository
 * @Description:
 * @Auther: Owenb
 * @Date: 12/28/18 20:21
 * @Version: 1.0
 */
public interface TagRepository extends JpaRepository<Tag, Integer>, JpaSpecificationExecutor<Tag> {

    Tag findTagById(Integer id);

}

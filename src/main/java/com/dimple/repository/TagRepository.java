package com.dimple.repository;

import com.dimple.bean.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName: TagRepository
 * @Description:
 * @Auther: Owenb
 * @Date: 12/28/18 20:21
 * @Version: 1.0
 */
public interface TagRepository extends JpaRepository<Tag, Integer> {
}

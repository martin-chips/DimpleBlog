package com.dimple.repository;

import com.dimple.bean.Blog;
import com.dimple.bean.Category;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : CategoryRepository
 * @description :
 * @date : 12/26/18 19:09
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<Category> {

    List<Category> findAllBySupportEquals(Boolean support);

    Category findByCategoryId(Integer id);
}

package com.dimple.repository;

import com.dimple.bean.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    Category getByCategoryId(Integer id);


    @Query("from Category where title=:title")
    Category getByTitle(@Param("title") String title);
}

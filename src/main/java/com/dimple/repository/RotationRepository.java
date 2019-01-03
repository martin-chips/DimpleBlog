package com.dimple.repository;

import com.dimple.bean.Rotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : RotationRepository
 * @description :
 * @date : 01/03/19 20:40
 */
public interface RotationRepository extends JpaRepository<Rotation, Integer>, JpaSpecificationExecutor<Rotation> {

    @Modifying
    @Query("delete from Rotation where id=:id")
    void deleteById(@Param("id") Integer id);


    @Query("from Rotation where id=:id")
    Rotation getById(@Param("id") Integer id);

}

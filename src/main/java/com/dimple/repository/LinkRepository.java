package com.dimple.repository;

import com.dimple.bean.Link;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : LinkRepository
 * @description :
 * @date : 12/26/18 19:10
 */
@Repository
public interface LinkRepository extends JpaRepository<Link, Integer>, JpaSpecificationExecutor<Link> {

    List<Link> findAllByAvailable(Boolean available);

    @Query(value = "select count(*) from link where status = 0", nativeQuery = true)
    Integer countLinkUnhandled();

    @Query(value = "select (select count(*) from link ) as total," +
            "(select count(*) from link where available=0 )as disabled," +
            "(select count(*) from link where status=0) as unHandled," +
            "(select count(*) from link where display=0) as hide," +
            "(select count(*)from link where display=1) as display", nativeQuery = true)
    Map<String, Integer> countStatusDetails();
}

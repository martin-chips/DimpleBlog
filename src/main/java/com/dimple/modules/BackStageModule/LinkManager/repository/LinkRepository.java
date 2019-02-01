package com.dimple.modules.BackStageModule.LinkManager.repository;

import com.dimple.modules.FrontDeskModule.domain.LinkDomain;
import com.dimple.modules.BackStageModule.LinkManager.bean.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : LinkRepository
 * @description :
 * @date : 12/26/18 19:10
 */
public interface LinkRepository extends JpaRepository<Link, Integer>, JpaSpecificationExecutor<Link> {

    @Query(value = "select count(*) from link where handle = 0", nativeQuery = true)
    Integer countLinkUnhandled();

    @Query(value = "select (select count(*) from link ) as total," +
            "(select count(*) from link where available=0 )as disabled," +
            "(select count(*) from link where handle=0) as unHandled," +
            "(select count(*) from link where display=0) as hide," +
            "(select count(*)from link where display=1) as display", nativeQuery = true)
    Map<String, Integer> countStatusDetails();

    Link findByLinkId(Integer id);

    @Query(value = "select new com.dimple.modules.FrontDeskModule.domain.LinkDomain(l.linkId,l.title,l.url,l.description,l.headerUrl) from  Link as l where l.display=true ")
    List<LinkDomain> getAllLinkHanded();

    @Query("from Link ")
    List<Link> getAllLink();

    /**
     * 获取没有处理的友链
     *
     * @return
     */
    List<Link> getAllByHandleFalse();
}

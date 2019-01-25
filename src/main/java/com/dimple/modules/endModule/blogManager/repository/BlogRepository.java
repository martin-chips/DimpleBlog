package com.dimple.modules.endModule.blogManager.repository;

import com.dimple.modules.endModule.blogManager.bean.Blog;
import com.dimple.modules.frontModule.front.domain.BlogDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : BlogRepository
 * @description :
 * @date : 12/26/18 19:09
 */
@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer>, JpaSpecificationExecutor<Blog> {

    @Query(value = "select * from blog where if(:title is not null,title like concat('%',:title,'%'),1=1 )" +
            "and if(:startTime is not null,update_time >= :startTime,1=1)" +
            "and if (:endTime is not null ,update_time <= :endTime,1=1)" +
            "and if(:status is not null ,status=:status,1=1) ", nativeQuery = true)
    List<Blog> findAll(@Param("title") String title, Date startTime, Date endTime, Integer status);

    @Query(value = "select (select count(*) from blog where status=1)as  publish," +
            "(select count(*) from blog where status=2)as drafts," +
            "(select count(*) from  blog where status=3) as dustbin", nativeQuery = true)
    Map<String, Integer> findAllBlogStatusCount();

    Integer countByStatus(Integer status);

    List<Blog> findAllByCategoryId(Integer id);

    List<Blog> findAllBySupportEquals(Boolean support);

    Blog findByBlogId(Integer id);

    @Query(value = "select * from blog order by update_time limit 0,4", nativeQuery = true)
    List<Blog> getNewestUpdateBlog();

    @Query(value = "select * from blog where blog_id > :id order by blog_id desc limit 0,1;", nativeQuery = true)
    Blog getNextBlog(@Param("id") Integer id);

    @Query(value = "select * from blog where blog_id < :id order by blog_id limit 0,1;", nativeQuery = true)
    Blog getPreviousBlog(@Param("id") Integer id);

    @Modifying
    @Query("update Blog set click=click+1 where blogId=:id")
    void incrementBlogCountById(@Param("id") Integer id);

    Blog getByBlogId(Integer id);

    /**
     * 获取随机的博客
     *
     * @param i 需要获取的博客的数量
     * @return
     */
    @Query(value = "select * from blog order by rand() limit :pageSize", nativeQuery = true)
    List<Blog> getRandomBlog(@Param("pageSize") int i);



 /*   @Query(value = "select count(*) as count,ip,os,address,browser from visitor where blacklist = 0 group by ip,os,address,browser order by count desc",
            countQuery = "select count(distinct (ip))from visitor where blacklist=0", nativeQuery = true)
    Page<List<Map<String, String>>> getAllVisitorCount(Pageable pageable);
*/

    //@Query(value = "select b.blog_id, b.category_id,c.title as categoryName,b.title,b.summary, b.create_time,b.update_time,b.tags,b.click,b.header_url from blog as b left join category as c on b.category_id = c.category_id",
    //        //countQuery = "select count(*) from blog b,category c where b.category_id = c.category_id;",
    //        nativeQuery = true)

    @Query("select new com.dimple.modules.frontModule.front.domain.BlogDomain(b.blogId,b.categoryId,c.title as categoryTitle,b.title,b.summary,b.createTime,b.tags,b.click,b.updateTime,b.headerUrl) from Blog b left join Category c on Category.categoryId=Blog.categoryId")
    Page<BlogDomain> getAllBlogDomain(Pageable pageable);


}

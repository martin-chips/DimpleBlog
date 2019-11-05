package com.dimple.project.system.mapper;

import com.dimple.project.system.domain.Carousel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @className: ConfigMapper
 * @description: 参数配置 数据层
 * @author: Dimple
 * @date: 11/02/19
 */
public interface CarouselMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Carousel selectCarouselById(Long id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param carousel 实例对象
     * @return 对象列表
     */
    List<Carousel> selectCarouselList(Carousel carousel);

    /**
     * 新增数据
     *
     * @param carousel 实例对象
     * @return 影响行数
     */
    int insertCarousel(Carousel carousel);

    /**
     * 修改数据
     *
     * @param carousel 实例对象
     * @return 影响行数
     */
    int updateCarousel(Carousel carousel);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteCarouselById(@Param("id") Long id, @Param("username") String username);

}
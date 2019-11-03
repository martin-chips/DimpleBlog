package com.dimple.project.system.service;

import com.dimple.project.system.domain.Carousel;

import java.util.List;

/**
 * @className: SysCarouselService
 * @description: 轮播图
 * @author: Dimple
 * @date: 11/02/19
 */
public interface CarouselService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Carousel selectCarouselById(Long id);

    /**
     * 查询轮播图列表
     *
     * @param carousel 查询对象
     * @return List
     */
    List<Carousel> selectCarouselList(Carousel carousel);

    /**
     * 新增数据
     *
     * @param carousel 实例对象
     * @return 受影响行数
     */
    int insertCarousel(Carousel carousel);

    /**
     * 修改数据
     *
     * @param carousel 实例对象
     * @return 受影响行数
     */
    int updateCarousel(Carousel carousel);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 受影响行数
     */
    int deleteCarouselById(Long id);

}
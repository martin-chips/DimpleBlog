package com.dimple.project.system.carouselMap.service;

import com.dimple.project.chart.business.domain.Business;
import com.dimple.project.system.carouselMap.entity.CarouselMap;

import java.util.List;

/**
 * 轮播图设置(CarouselMap)表服务接口
 */
public interface CarouselMapService {

    /**
     * 通过ID查询单条数据
     *
     * @param carouselId 主键
     * @return 实例对象
     */
    CarouselMap selectCarouselMapById(Integer carouselId);

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    List<CarouselMap> selectCarouselMapList(CarouselMap carouselMap);

    /**
     * 新增数据
     *
     * @param carouselMap 实例对象
     * @return 实例对象
     */
    int insertCarouselMap(CarouselMap carouselMap);

    /**
     * 修改数据
     *
     * @param carouselMap 实例对象
     * @return 实例对象
     */
    int updateCarouselMap(CarouselMap carouselMap);

    /**
     * 通过主键删除数据
     *
     * @param carouselId 主键
     * @return 是否成功
     */
    int deleteCarouselMapByIds(Integer[] carouselId);

    /**
     * 根据id和显示状态更改其显示状态
     *
     * @param carouselId 需要修改的id
     * @param display    显示状态
     * @return 受影响的行数
     */
    int changeCarouselDisplay(String carouselId, String display);

    /**
     * 获取前端展示的CarouselMap
     *
     * @return CarouselMap的list
     */
    List<CarouselMap> selectCarouselMapListFront();

    /**
     * 增加轮播图的点击量
     *
     * @param carouselId 需要增加点击量的id
     * @return 受影响的行数
     */
    int incrementCarouselClickById(Integer carouselId);

    /**
     * 获取图表展示的数据
     *
     * @return 图表展示的list集合
     */
    List<Business> selectCarouselMapData();
}
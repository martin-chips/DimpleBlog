package com.dimple.project.system.carouselMap.mapper;

import com.dimple.project.chart.business.domain.Business;
import com.dimple.project.system.carouselMap.entity.CarouselMap;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 轮播图设置(CarouselMap)表数据库访问层
 */
public interface CarouselMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param carouselId 主键
     * @return 实例对象
     */
    CarouselMap selectCarouselMap(Integer carouselId);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param carouselMap 实例对象
     * @return 对象列表
     */
    List<CarouselMap> selectCarouselMapList(CarouselMap carouselMap);

    /**
     * 新增数据
     *
     * @param carouselMap 实例对象
     * @return 影响行数
     */
    int insertCarouselMap(CarouselMap carouselMap);

    /**
     * 修改数据
     *
     * @param carouselMap 实例对象
     * @return 影响行数
     */
    int updateCarouselMap(CarouselMap carouselMap);

    /**
     * 通过主键删除数据
     *
     * @param carouselId 主键
     * @return 影响行数
     */
    int deleteCarouselMapByIds(Integer[] carouselId);

    /**
     * 更改显示状态
     *
     * @param carouselId 轮播的id
     * @param display    显示的状态
     * @return 受影响的行数
     */
    int changeCarouselDisplay(@Param("carouselId") String carouselId, @Param("display") String display);

    /**
     * 获取前端需要扎实的轮播图
     *
     * @return 轮播图的集合
     */
    List<CarouselMap> selectCarouselMapListFront();

    /**
     * 增加轮播图的点击量
     *
     * @param carouselId 需要增加点击量的轮播图的id
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
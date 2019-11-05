package com.dimple.project.system.service.impl;

import com.dimple.common.utils.SecurityUtils;
import com.dimple.project.system.domain.Carousel;
import com.dimple.project.system.mapper.CarouselMapper;
import com.dimple.project.system.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: SysCarouselServiceImpl
 * @description: 轮播图
 * @author: Dimple
 * @date: 11/02/19
 */
@Service
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    CarouselMapper carouselMapper;

    @Override
    public Carousel selectCarouselById(Long id) {
        return carouselMapper.selectCarouselById(id);
    }

    @Override
    public List<Carousel> selectCarouselList(Carousel carousel) {
        return carouselMapper.selectCarouselList(carousel);
    }

    @Override
    public int insertCarousel(Carousel carousel) {
        carousel.setCreateBy(SecurityUtils.getUsername());
        return carouselMapper.insertCarousel(carousel);
    }

    @Override
    public int updateCarousel(Carousel carousel) {
        carousel.setUpdateBy(SecurityUtils.getUsername());
        return carouselMapper.updateCarousel(carousel);
    }

    @Override
    public int deleteCarouselById(Long id) {
        String username = SecurityUtils.getUsername();
        return carouselMapper.deleteCarouselById(id, username);
    }
}
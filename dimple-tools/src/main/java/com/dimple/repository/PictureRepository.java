package com.dimple.repository;

import com.dimple.domain.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @className: PictureRepository
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
public interface PictureRepository extends JpaRepository<Picture, Long>, JpaSpecificationExecutor<Picture> {

    /**
     * 根据 Mds 值查询文件
     *
     * @param code 值
     * @return /
     */
    Picture findByMd5Code(String code);

    /**
     * 根据连接地址查询
     *
     * @param url /
     * @return /
     */
    boolean existsByUrl(String url);
}

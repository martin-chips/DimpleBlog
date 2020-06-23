package com.dimple.repository;

import com.dimple.domain.QiniuContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @className: QiniuContentRepository
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
public interface QiniuContentRepository extends JpaRepository<QiniuContent, Long>, JpaSpecificationExecutor<QiniuContent> {

    /**
     * 根据key查询
     *
     * @param key 文件名
     * @return QiniuContent
     */
    QiniuContent findByKey(String key);
}
